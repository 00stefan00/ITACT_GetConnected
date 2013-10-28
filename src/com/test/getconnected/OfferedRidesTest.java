package com.test.getconnected;

import android.test.AndroidTestCase;
import com.app.getconnected.network.OfferedRides;

/**
 * Created with IntelliJ IDEA.
 * User: johan
 * Date: 10/28/13
 * Time: 12:14 PM
 */
public class OfferedRidesTest extends AndroidTestCase {
	public void testUrl() throws Exception {
		OfferedRides.setUsername("test");
		assertEquals("https://localhost:8181/OpenRideServer-RS/resources/users/test/rides/offers", OfferedRides.getUrl());
	}
}
