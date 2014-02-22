package com.saboonchi.sthlmnext.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    public static String fetchURL(URL url, String charset) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream stream = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
            sb.append(line);
        stream.close();
        return sb.toString();
    }
}
