package com.app.getconnected.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import com.app.getconnected.R;
import com.app.getconnected.config.Config;
import com.app.getconnected.gps.GPSLocator;
import com.app.getconnected.rest.RESTRequest;
import com.app.getconnected.rest.RESTRequestEvent;
import com.app.getconnected.rest.RESTRequestListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.events.DelayedMapListener;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author getConnected 1
 */
public class MapActivity extends BaseActivity implements RESTRequestListener {

	private MapController mapController;
	private MapView mapView;
	protected GPSLocator locator;
	private MyLocationOverlay myLocationoverlay;
	private MyOwnItemizedOverlay overlay;
	private RESTRequest rR;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		initLayout(R.string.title_activity_map, true, true, true, false);

		createMap();
		addLocationOverlay();
		loadBusStops();
		mapView.setMultiTouchControls(true);
		mapView.setMapListener(new DelayedMapListener(new MapListener() {

			@Override
			public boolean onScroll(ScrollEvent arg0) {

				loadBusStops();

				return true;
			}

			@Override
			public boolean onZoom(ZoomEvent arg0) {
				
				loadBusStops();

				return true;
			}

		}, 1000));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	/**
	 * Creates the street map and shows it on screen.
	 */
	private void createMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		mapController.setZoom(15);
		locator = new GPSLocator(getApplicationContext());
		GeoPoint point2 = new GeoPoint(locator.getLatitude(),
				locator.getLongitude());
		mapController.setCenter(point2);
	}

	/**
	 * Adds an overlay to the map that shows user's current location.
	 */
	private void addLocationOverlay() {
		myLocationoverlay = new MyLocationOverlay(this, mapView);
		myLocationoverlay.disableCompass();
		myLocationoverlay.disableFollowLocation();
		myLocationoverlay.setDrawAccuracyEnabled(true);
		myLocationoverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				mapController.animateTo(myLocationoverlay.getMyLocation());
			}
		});

		myLocationoverlay.enableMyLocation();
		mapView.getOverlays().add(myLocationoverlay);
	}

	/**
	 * Creates and executes query to get the correct bus stop information.
	 */
	private void loadBusStops() {
		if (rR != null)
			rR.cancel();
		rR = new RESTRequest(Config.busStopAddress);
		IGeoPoint point = mapView.getMapCenter();
		rR.putDouble("gps_longitude", (double) point.getLongitudeE6() / 1000000);
		rR.putDouble("gps_latitude", (double) point.getLatitudeE6() / 1000000);
		rR.putDouble("range", 1000);
		rR.addEventListener(this);
		rR.execute();
	}

	/**
	 * Class that forms the overlay to display the bus stops.
	 */
	public class MyOwnItemizedOverlay extends ItemizedIconOverlay<OverlayItem> {
		protected Context mContext;

		public MyOwnItemizedOverlay(final Context context,
				final List<OverlayItem> aList) {

			super(context, aList, new OnItemGestureListener<OverlayItem>() {
				@Override
				public boolean onItemSingleTapUp(final int index,
						final OverlayItem item) {
					return false;
				}

				@Override
				public boolean onItemLongPress(final int index,
						final OverlayItem item) {
					return false;
				}
			});
			mContext = context;
		}

		@Override
		protected boolean onSingleTapUpHelper(final int index,
				final OverlayItem item, final MapView mapView) {
			Intent intent = new Intent(MapActivity.this,
					BusStopDetailsActivity.class);
			intent.putExtra("id", Integer.parseInt(item.getSnippet()));
			startActivity(intent);
			return true;
		}

	}

	@Override
	public void RESTRequestOnPreExecute(RESTRequestEvent event) {
		
	}

	@Override
	public void RESTRequestOnProgressUpdate(RESTRequestEvent event) {
	}

	@Override
	public void RESTRequestOnPostExecute(RESTRequestEvent event) {
		mapView.getOverlays().remove(overlay);

		String result = event.getResult();

		if (RESTRequest.isExceptionCode(result)) {
			return;
		}

		ArrayList<OverlayItem> overlayItemArray = new ArrayList<OverlayItem>();

		try {
			JSONObject json = new JSONObject(result);
			JSONArray array = json.getJSONArray("busstops");

			for (int i = 0; i < array.length(); i++) {
				JSONObject busstop = array.getJSONObject(i);
				GeoPoint location = new GeoPoint(
						busstop.getDouble("GPS_Latitude"),
						busstop.getDouble("GPS_Longitude"));
				OverlayItem olItem = new OverlayItem("Bus Stop", ""
						+ busstop.getInt("id"), location);
				overlayItemArray.add(olItem);
			}

			overlay = new MyOwnItemizedOverlay(this, overlayItemArray);

			mapView.getOverlays().add(overlay);
		} catch (Exception e) {
			e.printStackTrace();

			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.error_connection_failed),
					Toast.LENGTH_SHORT).show();
		}
	}
}
