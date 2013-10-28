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
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.app.getconnected.R;
import com.app.getconnected.rest.RESTRequest;

public class BusStopDetailsActivity extends BaseActivity {

	private MapView mapView;
	private MapController mapController;
	private double latitude;
	private double longitude;
	private int id;
	private String name;
	private String city;
	private boolean shelter;
	private boolean trunk;
	private boolean seatings;
	private int number;
	
	private TextView numberView;
	private TextView nameView;
	private TextView cityView;
	
	private TextView shelterView;
	private TextView trunkView;
	private TextView seatingsView;
	
	//private String confirmYes = getResources().getString(R.string.confirm);
	//private String confirmNo = getResources().getString(R.string.deny);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_stop_details);
		initLayout(R.string.title_activity_bus_stop_details, true, true, true, false);
		
		id = getIntent().getExtras().getInt("id");
		numberView = (TextView) findViewById(R.id.busstop_number);
		nameView = (TextView) findViewById(R.id.busstop_name);
		cityView = (TextView) findViewById(R.id.busstop_city);
		shelterView = (TextView) findViewById(R.id.busstop_shelter);
		seatingsView = (TextView) findViewById(R.id.busstop_seatings);
		trunkView = (TextView) findViewById(R.id.busstop_trunk);
		
		getBusStopDetails(id);
		
		createMap();
		
		addItem();
		
		setInformation();
	}
	
	private void setInformation() {
		numberView.setText(number + "");
		nameView.setText(name);
		cityView.setText(city);
		shelterView.setText(shelter ? getResources().getString(R.string.confirm) : getResources().getString(R.string.deny));
		seatingsView.setText(seatings ? getResources().getString(R.string.confirm) : getResources().getString(R.string.deny));
		trunkView.setText(trunk ? getResources().getString(R.string.confirm) : getResources().getString(R.string.deny));
	}

	private void getBusStopDetails(int id) {
<<<<<<< HEAD
		String url = "http://145.37.88.226/yii/sites/BusStops/api/busstop/" + id;
=======
		String url = "http://145.37.92.124/yii/sites/BusStops/api/busstop/" + id;
>>>>>>> 654f202615a51a460a633fa58c6df89c8e41bce4
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
