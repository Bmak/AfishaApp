package tz.infoshell.afishaapp.city;

import tz.infoshell.afishaapp.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemFrame extends RelativeLayout implements View.OnClickListener {
	
	private CityItem _cityItem;
	
	private int urlMinLen = "s.afisha.ru".length();
	
	private ImageView _image;
	private TextView _pubDate;
	private TextView _title;
	private TextView _link;
	private TextView _description;
	private RatingBar _rate;
	
	public ItemFrame(Context context, CityItem cityItem) {
        super(context);
        
        _cityItem = cityItem;
        
        initComponent();
    }
	
	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		inflater.inflate(R.layout.item, this);
		
		_image = (ImageView)findViewById(R.id.item_image);
		_pubDate = (TextView) findViewById(R.id.publish_time);
		_title = (TextView) findViewById(R.id.title);
		_link = (TextView) findViewById(R.id.link);
		_description = (TextView) findViewById(R.id.description);
		_rate = (RatingBar) findViewById(R.id.ratingbar);
		
		updateFields();
	}
	
	private void updateFields() {
		//TODO change image on different screen sizes
        String url = _cityItem.imageUrl170x95;
        if (url.length() > urlMinLen) {
        	ImageManager.fetchImage(url, _image);
        }
        
        _pubDate.setText(_cityItem.pubDate);
        _title.setText(_cityItem.title);
        _link.setOnClickListener(this);
        _description.setText(_cityItem.description);
        _rate.setRating(_cityItem.rate);
    }
	
	@Override
	public void onClick(View v) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_cityItem.link));
		getContext().startActivity(browserIntent);
	}
}
