package com.saboonchi.sthlmnext.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleData {
    private static final String RIGHT_ARROW = " \u2192 ";

    public static List<? extends Map<String, ?>> sampleStations() {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Kista");
        map1.put("type", "TB");
        map1.put("distance", "0.5km");
        data.add(map1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Husby");
        map2.put("type", "T");
        map2.put("distance", "1.0km");
        data.add(map2);

        HashMap<String, String> map3 = new HashMap<String, String>();
        map3.put("name", "Helenelund");
        map3.put("type", "J");
        map3.put("distance", "1.5km");
        data.add(map3);

        return data;
    }

    public static List<? extends Map<String, ?>> sampleFavorites() {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Kista" + RIGHT_ARROW + "Akalla");
        map1.put("line", "T 11");
        map1.put("time", "3min");
        data.add(map1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Solna" + RIGHT_ARROW + "Danderyds sjukhus");
        map2.put("line", "B 509");
        map2.put("time", "10min");
        data.add(map2);

        return data;
    }
}
