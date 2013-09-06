package tz.infoshell.afishaapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import tz.infoshell.afishaapp.city.Cities;
import tz.infoshell.afishaapp.city.CityItem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

public class AsyncData extends AsyncTask<String, Void, Document> {
	
	private MainActivity activity;
	private ProgressDialog pDialog;
	
	private ArrayList<Cities> cityList;
	
	public AsyncData(MainActivity activity, ArrayList<Cities> cityList) {
		this.activity = activity;
		this.cityList = cityList;
	}
	
	@Override
	protected void onPreExecute() {
		pDialog = new ProgressDialog(this.activity);
		pDialog.setTitle("Loading....");
		pDialog.setMessage("Please wait...");
		pDialog.show();
		super.onPreExecute();
	}

	@Override
	protected Document doInBackground(String... params) {

		try {
			URL _url = new URL(params[0]);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document doc = db.parse(_url.openStream());
			
			return doc;
		} catch (ParserConfigurationException pce) {
			Log.e(MainActivity.LOG_TAG, "sax ParserConfigurationException ", pce);
		} catch (SAXException se) {
			Log.e(MainActivity.LOG_TAG, "sax SAXException ", se);
		} catch (IOException e) {
			Log.e(MainActivity.LOG_TAG, "sax IOException ", e);
			e.printStackTrace();
		}
		return null;

	}

	@Override
	protected void onPostExecute(Document result) {
		super.onPostExecute(result);
		
		int len = result.getElementsByTagName("cities").getLength();
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			for (int i = 0; i < len; i++) {
				Cities cities = new Cities();
				Element element = (Element)result.getElementsByTagName("cities").item(i);
				Element city = (Element)element.getElementsByTagName("city").item(0);
				String name = city.getElementsByTagName("name").item(0).getTextContent();
				String id = element.getAttribute("id");
				cities.setCity(name);
				cities.setId(id);
				list.add(name);
				
				NodeList items = element.getElementsByTagName("item");
				
				for (int j = 0; j < items.getLength(); j++) {
					Element item = (Element) items.item(j);
					
					if (item.getParentNode().getNodeName().equals("schedule")) { break; }
					
					CityItem cityItem = new CityItem();
					
					Element type = (Element) item.getElementsByTagName("type").item(0);
					cityItem.type = type.getTextContent();
					cityItem.url = type.getAttribute("url");
					cityItem.title = item.getElementsByTagName("title").item(0).getTextContent();
					cityItem.link = item.getElementsByTagName("link").item(0).getTextContent();
					cityItem.imageUrl = item.getElementsByTagName("imageUrl").item(0).getTextContent();
					cityItem.imageUrl170x95 = item.getElementsByTagName("imageUrl170x95").item(0).getTextContent();
					cityItem.imageUrl233x120 = item.getElementsByTagName("imageUrl233x120").item(0).getTextContent();
					cityItem.imageUrl222x124 = item.getElementsByTagName("imageUrl222x124").item(0).getTextContent();
					cityItem.description = item.getElementsByTagName("description").item(0).getTextContent();
					cityItem.pubDate = item.getElementsByTagName("pubDate").item(0).getTextContent();
					cityItem.rateCount = Integer.parseInt(item.getElementsByTagName("rateCount").item(0).getTextContent());
					cityItem.rate = Float.parseFloat((item.getElementsByTagName("rate").item(0).getTextContent()));
					cityItem.photoCount = Float.parseFloat(item.getElementsByTagName("photoCount").item(0).getTextContent());
					cityItem.photoUrl = item.getElementsByTagName("photoUrl").item(0).getTextContent();
					cityItem.trailerCount = Integer.parseInt(item.getElementsByTagName("trailerCount").item(0).getTextContent());
					cityItem.trailerUrl = item.getElementsByTagName("trailerUrl").item(0).getTextContent();
					cityItem.areTicketsAvailable = Integer.parseInt(item.getElementsByTagName("areTicketsAvailable").item(0).getTextContent());
					
					cities.getItems().add(cityItem);
				}
				cityList.add(cities);
			}
		} catch (DOMException e) {
			Log.e(MainActivity.LOG_TAG, "DOMException " + e.getMessage());
		}
		
		if (pDialog != null && pDialog.isShowing()) {
			pDialog.dismiss();
		}	
		
		activity.setListAdapter(new ArrayAdapter<String>(
				activity, android.R.layout.simple_list_item_1, list));
	}
}
