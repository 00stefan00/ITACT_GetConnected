package com.app.getconnected.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;

import com.app.getconnected.R;
import com.app.getconnected.R.layout;
import com.app.getconnected.R.menu;
import com.app.getconnected.gps.GPSLocator;
import com.app.getconnected.rest.RESTRequest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class BusStopDetailsActivity extends Activity {

	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocationoverlay;
	private double latitude;
	private double longitude;
	private int id;
	private String name;
	private String city;
	private boolean shelter;
	private boolean seatings;
	private int number;
	
	private TextView numberView;
	private TextView nameView;
	private TextView cityView;
	private boolean trunk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_stop_details);
		
		id = getIntent().getExtras().getInt("id");
		numberView = (TextView) findViewById(R.id.busstop_number);
		nameView = (TextView) findViewById(R.id.busstop_name);
		cityView = (TextView) findViewById(R.id.busstop_city);
		
		getBusStopDetails(id);
		
		createMap();
		
		addItem();
		
		setInformation();
	}
	
	private void setInformation() {
		numberView.setText(number + "");
		nameView.setText(name);
		cityView.setText(city);
	}

	private void getBusStopDetails(int id) {
		String url = "http://145.37.86.205/yii/sites/BusStops/api/busstop/" + id;
		RESTRequest request = new RESTRequest(url);
		
		try {
			String result = request.execute().get();
			
			JSONObject json = new JSONObject(result).getJSONObject("busstop");
			System.out.println(result);
			number = json.getInt("halteNummer_overig");
			name = json.getString("naam");
			city = json.getString("city");
			latitude = Double.parseDouble(json.getString("GPS_Latitude"));
			longitude = Double.parseDouble(json.getString("GPS_Longitude"));
			shelter = json.getInt("opt_aanwezigheidabri") == 1 ? true : false;
			seatings = json.getInt("opt_zitgelegenheid") == 1 ? true : false;
			trunk = json.getInt("opt_afvalbak") == 1 ? true : false;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addItem() {
		OverlayItem olItem = new OverlayItem("Haltenaam", "Adres", new GeoPoint(latitude, longitude));
		List<OverlayItem> list = new ArrayList<OverlayItem>();
		list.add(olItem);
		ItemizedIconOverlay<OverlayItem> iio = new ItemizedIconOverlay<OverlayItem>(this, list, new OnItemGestureListener<OverlayItem>() {

			@Override
			public boolean onItemLongPress(int arg0, OverlayItem arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onItemSingleTapUp(int arg0, OverlayItem arg1) {
				// TODO Auto-generated method stub
				return false;
			}

	    });
		mapView.getOverlays().add(iio);
	}

	private void createMap() {
		mapView = (MapView) findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(17);
		GeoPoint point2 = new GeoPoint(latitude, longitude);
        mapController.setCenter(point2);
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bus_stop_details, menu);
		return true;
	}

}
