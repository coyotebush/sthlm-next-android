package com.saboonchi.sthlmnext.provider;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.MatrixCursor;
import android.location.Location;

public class ResRobotApi {

	private static final String ENDPOINT = "https://api.trafiklab.se/samtrafiken/resrobot/";
	private static final String APIURL = "https://api.trafiklab.se/samtrafiken/resrobotstops/";
    private static final String CARRIER_ID = "275";

	public static MatrixCursor getDestinationList(String stationId) {
		try {
			URL url = new URL(APIURL + "GetDepartures.json"
					+ "?apiVersion=2.1&key=" + ApiKeys.RESROBOT_DEST
					+ "&coordSys=WGS84" + "&locationId=" + stationId
					+ "&timeSpan=30");

			JSONArray data = new JSONObject(Utils.fetchURL(url, "ISO-8859-1"))
					.getJSONObject("getdeparturesresult").getJSONArray(
							"departuresegment");

			MatrixCursor cursor = new MatrixCursor(new String[] { "_id",
					"direction", "number", "time", "type" }, data.length());
			for (int i = 0; i < data.length(); i++) {
				JSONObject o = data.getJSONObject(i);

				Object motObject = o.getJSONObject("segmentid").opt("mot");
				Object carrierObject = o.getJSONObject("segmentid").opt(
						"carrier");
				JSONObject departureTimeObject = o.getJSONObject("departure");

				cursor.addRow(new Object[] {
						i,
						o.getString("direction"),
						((JSONObject) carrierObject).getString("number"),
						((JSONObject) departureTimeObject)
								.getString("datetime"),
						((JSONObject) motObject).getString("@displaytype"), });
			}
			return cursor;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static MatrixCursor findStationsNear(Location location) {
		try {
			URL url = new URL(ENDPOINT + "StationsInZone.json"
					+ "?apiVersion=2.1&key=" + ApiKeys.RESROBOT_RESA
					+ "&coordSys=WGS84&radius=1000" + "&centerX="
					+ location.getLongitude() + "&centerY="
					+ location.getLatitude());

			JSONArray data = new JSONObject(Utils.fetchURL(url, "ISO-8859-1"))
					.getJSONObject("stationsinzoneresult").getJSONArray(
							"location");

			MatrixCursor cursor = new MatrixCursor(new String[] { "_id",
					"name", "location", "distance", "types" }, data.length());
			for (int i = 0; i < data.length(); i++) {
				JSONObject o = data.getJSONObject(i);
				
                Object producer = o.getJSONObject("producerlist").opt("producer");
                List<Object> producerList = Utils.jsonMaybeArrayToList(producer);
                boolean foundProducer = false;
                for (Object p : producerList) {
                    if (((JSONObject) p).getString("@id").equals(CARRIER_ID)) {
                        foundProducer = true;
                        break;
                    }
                }
                if (!foundProducer) {
                    continue;
                }


				Location l = new Location(ENDPOINT);
				l.setLongitude(o.getDouble("@x"));
				l.setLatitude(o.getDouble("@y"));
				String distance = String
						.format("%.0fm", location.distanceTo(l));

				Object transport = o.getJSONObject("transportlist").opt(
						"transport");
				List<Object> typesList = Utils.jsonMaybeArrayToList(transport);
				String types = "";
				for (int j = 0; j < typesList.size(); j++) {
					if (j != 0)
						types += ", ";
					types += ((JSONObject) typesList.get(j))
							.getString("@displaytype");
				}

				cursor.addRow(new Object[] { o.getInt("@id"),
						o.getString("name"), l, distance, types });
			}
			return cursor;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
