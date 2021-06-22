package com.malikali.kotlinrssfeeed.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataHandler {
    static String stream = "";

    public HttpDataHandler(){}

    public String GetHttpDataHandler(String urlString){


        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line = "";

                while ((line = br.readLine()) != null){
                    sb.append(line);
                    stream = sb.toString();
                    httpURLConnection.disconnect();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
