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

    public static MatrixCursor findStationsNear(Location location) {
        try {
            URL url = new URL(ENDPOINT + "StationsInZone.json"
                    + "?apiVersion=2.1&key=" + ApiKeys.RESROBOT_RESA
                    + "&coordSys=WGS84&radius=1000"
                    + "&centerX=" + location.getLongitude()
                    + "&centerY=" + location.getLatitude());

            JSONArray data = new JSONObject(Utils.fetchURL(url, "ISO-8859-1"))
                    .getJSONObject("stationsinzoneresult").getJSONArray("location");

            MatrixCursor cursor = new MatrixCursor(new String[] { "_id",
                    "name", "location", "distance", "types" }, data.length());
            for (int i = 0; i < data.length(); i++) {
                JSONObject o = data.getJSONObject(i);

                Location l = new Location(ENDPOINT);
                l.setLongitude(o.getDouble("@x"));
                l.setLatitude(o.getDouble("@y"));
                String distance = String.format("%.0fm", location.distanceTo(l));

                Object transport = o.getJSONObject("transportlist").opt("transport");
                List<Object> typesList = Utils.jsonMaybeArrayToList(transport);
                String types = "";
                for (int j = 0; j < typesList.size(); j++) {
                    if (j != 0) types += ", ";
                    types += ((JSONObject) typesList.get(j)).getString("@displaytype");
                }
                
                cursor.addRow(new Object[] { o.getInt("@id"), o.getString("name"), l, distance, types });
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
