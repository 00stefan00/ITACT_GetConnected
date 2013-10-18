package com.app.getconnected.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.getconnected.R;
import com.app.getconnected.gps.GPSLocator;
import com.app.getconnected.rest.RESTRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

/**
 * 
 * @author Nico
 * 
 * 
 */
public class MapActivity extends BaseActivity {

	private MapController mapController;
    private MapView mapView;
    protected GPSLocator locator;
    private MyLocationOverlay myLocationoverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initLayout(R.string.title_activity_map, true, true, true, false);
        
        createMap();
        addLocationOverlay();
        /*
        ArrayList<OverlayItem> overlayItemArray = new ArrayList<OverlayItem>();
        OverlayItem olItem = new OverlayItem("Bus Stop", "Groningen, Zernike", new GeoPoint(53.241, 6.534));
        OverlayItem olItem2 = new OverlayItem("niet Bus Stop", "iets anders", new GeoPoint(53.240, 6.534));
        overlayItemArray.add(olItem);
        overlayItemArray.add(olItem2);
        MyOwnItemizedOverlay overlay = new MyOwnItemizedOverlay(this, overlayItemArray);
        */
        MyOwnItemizedOverlay overlay = getBusStops();
        mapView.getOverlays().add(overlay);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
    
    private void createMap(){
    	mapView = (MapView) findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(15);
        locator = new GPSLocator(getApplicationContext());
        GeoPoint point2 = new GeoPoint(locator.getLatitude(), locator.getLongitude());
        mapController.setCenter(point2);
    }
    
    private void addLocationOverlay(){
    	myLocationoverlay = new MyLocationOverlay(this, mapView);
        myLocationoverlay.disableCompass();
        myLocationoverlay.disableFollowLocation();
        myLocationoverlay.setDrawAccuracyEnabled(true);
        myLocationoverlay.runOnFirstFix(new Runnable() {
        public void run() {
                mapController.animateTo(myLocationoverlay
                        .getMyLocation());
            }
        });
        
        myLocationoverlay.enableMyLocation();
        mapView.getOverlays().add(myLocationoverlay);
    }
    
    private MyOwnItemizedOverlay getBusStops(){
    	RESTRequest rR = new RESTRequest("http://145.37.86.205/yii/sites/BusStops/api/busstop");
    	rR.putDouble("gps_longitude", locator.getLongitude());
    	rR.putDouble("gps_latitude", locator.getLatitude());
    	rR.putDouble("range", 1000);
    	ArrayList<OverlayItem> overlayItemArray = new ArrayList<OverlayItem>();
    	try {
			JSONObject json = new JSONObject(rR.execute().get());
			JSONArray array = json.getJSONArray("busstops");
			for(int i=0;i<array.length();i++){
				JSONObject busstop = array.getJSONObject(i);
				GeoPoint location = new GeoPoint(busstop.getDouble("GPS_Latitude"),busstop.getDouble("GPS_Longitude"));
				OverlayItem olItem = new OverlayItem("Bus Stop", ""+busstop.getInt("id"), location);
				overlayItemArray.add(olItem);
			}
			MyOwnItemizedOverlay overlay = new MyOwnItemizedOverlay(this, overlayItemArray);
			return overlay;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public class MyOwnItemizedOverlay extends ItemizedIconOverlay<OverlayItem> {
        protected Context mContext;

        public MyOwnItemizedOverlay(final Context context, final List<OverlayItem> aList) {
             
        	super(context, aList, new OnItemGestureListener<OverlayItem>() {
                    @Override public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                            return false;
                    }
                    @Override public boolean onItemLongPress(final int index, final OverlayItem item) {
                            return false;
                    }
                  } );
             mContext = context;
        }
        
        @Override 
        protected boolean onSingleTapUpHelper(final int index, final OverlayItem item, final MapView mapView) {
            Intent intent = new Intent(MapActivity.this, BusStopDetailsActivity.class);
            intent.putExtra("id", Integer.parseInt(item.getSnippet()));
            startActivity(intent);
            return true;
        }
        
    }
}
