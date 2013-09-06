package tz.infoshell.afishaapp.city;

import java.io.Serializable;


public class CityItem implements Serializable {

	public String type;
	public String url;
	public String title;
	public String link;
	public String imageUrl;
	public String imageUrl170x95;
	public String imageUrl233x120;
	public String imageUrl222x124;
	public String description;
	public String pubDate; //перевод в формат даты
	public int rateCount;
	public float rate;
	public float photoCount;
	public String photoUrl;
	public int trailerCount;
	public String trailerUrl;
	public int areTicketsAvailable;
	
}
