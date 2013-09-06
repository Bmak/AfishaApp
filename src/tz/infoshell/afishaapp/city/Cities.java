package tz.infoshell.afishaapp.city;

import java.io.Serializable;
import java.util.ArrayList;


//TODO rebuild to Parcelable
public class Cities implements Serializable {
	private String _id= "no id";
	private String _city = "default city";
	private ArrayList<CityItem> _items;
	//ArrayList<OtherItem> schedule; //I didn't get this Task
	
	public Cities() {
		_items = new ArrayList<CityItem>();
	}

	public String getId() { return this._id; }
	public void setId(String id) { this._id = id; }

	public String getCity() { return _city; }
	public void setCity(String city) { this._city = city; }
	
	public ArrayList<CityItem> getItems() {
		return _items;
	}
	
	public CityItem getItem(int index) {
		return _items.get(index);
	}
}
