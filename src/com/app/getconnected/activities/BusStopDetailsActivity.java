package com.app.getconnected.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.app.getconnected.R;
import com.app.getconnected.config.Config;
import com.app.getconnected.rest.RESTRequest;
import com.app.getconnected.rest.RESTRequestEvent;
import com.app.getconnected.rest.RESTRequestListener;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 	Jorian Plat <jorianplat@hotmail.com> - getConnected 1
 * @version 1.0			
 * @since	2013-10-28
 */
public class BusStopDetailsActivity extends BaseActivity implements
		RESTRequestListener {

	//OpenStreetMap
	private MapView mapView;
	private MapController mapController;

	// BusStop information
	private int id;
	private int number;
	private String name;
	private String city;
	private double latitude;
	private double longitude;

	// Facilities (Yes/No)
	private boolean shelter;
	private boolean trunk;
	private boolean seatings;
	private boolean bicycleParking;

	// Information views
	private TextView numberView;
	private TextView nameView;
	private TextView cityView;

	// Facility views
	private TextView shelterView;
	private TextView trunkView;
	private TextView seatingsView;
	private TextView bicycleParkingView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_stop_details);
		initLayout(R.string.title_activity_bus_stop_details, true, true, true,
				false);

		//initialize views
		id = getIntent().getExtras().getInt("id");
		numberView = (TextView) findViewById(R.id.busstop_number);
		nameView = (TextView) findViewById(R.id.busstop_name);
		cityView = (TextView) findViewById(R.id.busstop_city);
		shelterView = (TextView) findViewById(R.id.busstop_shelter);
		seatingsView = (TextView) findViewById(R.id.busstop_seatings);
		trunkView = (TextView) findViewById(R.id.busstop_trunk);
		bicycleParkingView = (TextView) findViewById(R.id.busstop_bicycle_parking);

		getBusStopDetails(id);
	}

	/**
	 * Method to push the bus stop information to the views.
	 * Sets the information of the bus stops
	 */
	private void setInformation() {
		numberView.setText(number + "");
		nameView.setText(name);
		cityView.setText(city);
		shelterView.setText(shelter ? getResources()
				.getString(R.string.confirm) : getResources().getString(
				R.string.deny));
		seatingsView.setText(seatings ? getResources().getString(
				R.string.confirm) : getResources().getString(R.string.deny));
		trunkView.setText(trunk ? getResources().getString(R.string.confirm)
				: getResources().getString(R.string.deny));
		bicycleParkingView.setText(bicycleParking ? getResources().getString(
				R.string.confirm) : getResources().getString(R.string.deny));
	}

	/**
	 * Method to receive the bus stop details from the server.
	 * @param id The id of the busStop
	 * Gets the details of a bus stop
	 * @param id
	 */
	private void getBusStopDetails(int id) {
		String url = Config.busStopAddress + id;
		RESTRequest request = new RESTRequest(url);
		request.addEventListener(this);
		request.execute();
	}

	/**
	 * Method to initialize all the variables. 
	 * After this the information will be pushed to the views and 
	 * the mini-map will be created.
	 * Sets the bus stop details
	 * @param result
	 */
	private void setBusStopDetails(String result) {
		try {
			JSONObject json = new JSONObject(result).getJSONObject("busstop");
			number = json.getInt("halteNummer_overig");
			name = json.getString("naam");
			city = json.getString("city");
			latitude = Double.parseDouble(json.getString("GPS_Latitude"));
			longitude = Double.parseDouble(json.getString("GPS_Longitude"));
			shelter = json.getInt("opt_aanwezigheidabri") == 1 ? true : false;
			seatings = json.getInt("opt_zitgelegenheid") == 1 ? true : false;
			trunk = json.getInt("opt_afvalbak") == 1 ? true : false;
			bicycleParking = json.getInt("opt_fietsparkeervoorziening") == 1 ? true
					: false;
			
			setInformation();
			createMap();
			
		} catch (JSONException e) {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.error_connection_failed),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Method to add the marker of the busstop to the mini-map.
	 * Adds an item
	 */
	private void addItem() {
		OverlayItem olItem = new OverlayItem("Haltenaam", "Adres",
				new GeoPoint(latitude, longitude));
		List<OverlayItem> list = new ArrayList<OverlayItem>();
		list.add(olItem);
		ItemizedIconOverlay<OverlayItem> iio = new ItemizedIconOverlay<OverlayItem>(
				this, list, new OnItemGestureListener<OverlayItem>() {

					@Override
					public boolean onItemLongPress(int arg0, OverlayItem arg1) {
						return false;
					}

					@Override
					public boolean onItemSingleTapUp(int arg0, OverlayItem arg1) {
						return false;
					}

				});
		mapView.getOverlays().add(iio);
	}

	/**
	 * Method to create the mini-map.
	 * Creates the map
	 */
	private void createMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		mapController.setZoom(17);
		GeoPoint point2 = new GeoPoint(latitude, longitude);
		mapController.setCenter(point2);
		
		addItem();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bus_stop_details, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.app.getconnected.rest.RESTRequestListener#RESTRequestOnPreExecute(com.app.getconnected.rest.RESTRequestEvent)
	 */
	@Override
	public void RESTRequestOnPreExecute(RESTRequestEvent event) {
		dialog = new ProgressDialog(this);
		dialog.setTitle(getResources().getString(R.string.loading));
		dialog.show();
	}

	/* (non-Javadoc)
	 * @see com.app.getconnected.rest.RESTRequestListener#RESTRequestOnProgressUpdate(com.app.getconnected.rest.RESTRequestEvent)
	 */
	@Override
	public void RESTRequestOnProgressUpdate(RESTRequestEvent event) {

	}

	/* (non-Javadoc)
	 * @see com.app.getconnected.rest.RESTRequestListener#RESTRequestOnPostExecute(com.app.getconnected.rest.RESTRequestEvent)
	 */
	@Override
	public void RESTRequestOnPostExecute(RESTRequestEvent event) {
		dialog.dismiss();

		setBusStopDetails(event.getResult());
	}

}
