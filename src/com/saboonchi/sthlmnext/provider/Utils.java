package com.saboonchi.sthlmnext.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class Utils {
	public static String fetchURL(URL url, String charset) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream stream = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				stream, charset));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);
		stream.close();
		return sb.toString();
	}

	/**
	 * Take a JSON value that can be either a JSONArray or a single other value,
	 * and return a list containing the entries of the array in the first case,
	 * or the single value in the second case.
	 * 
	 * @param maybeArray
	 *            a JSONArray or other JSON object
	 * @return a list of JSON values
	 */
	public static List<Object> jsonMaybeArrayToList(Object maybeArray)
			throws JSONException {
		List<Object> result = new LinkedList<Object>();
		if (maybeArray instanceof JSONArray) {
			JSONArray array = (JSONArray) maybeArray;
			for (int i = 0; i < array.length(); i++) {
				result.add(array.get(i));
			}
		} else {
			result.add(maybeArray);
		}
		return result;
	}

	public static String getTimeDiffInMin(String time) {

		Calendar cal = Calendar.getInstance();
		Calendar now;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			cal.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// all done

		now = Calendar.getInstance();

		int timeLeftInHour = (int) (((((cal.getTime().getTime() - now.getTime()
				.getTime()) / (1000 * 60 * 60)) % 24)));

		int timeLeftInMin = (int) ((((cal.getTime().getTime() - now.getTime()
				.getTime()) / (1000 * 60)) % 60));

		int totalTimeLeftInMin;

		if (timeLeftInHour > 0)
			totalTimeLeftInMin = (timeLeftInHour * 60) + timeLeftInMin;
		else
			totalTimeLeftInMin = timeLeftInMin;

		String totalTimeleftInMin;

		if (totalTimeLeftInMin == 0)
			totalTimeleftInMin = "Now";
		else
			totalTimeleftInMin = String.valueOf(totalTimeLeftInMin) + " min";

		return totalTimeleftInMin;

	}
}
