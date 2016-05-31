package com.neurio.tests.shared;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Robert on 5/30/2016.
 * API Class
 */
public class API {

    /**
     * Reformats the Req Body to the correct format for UTF-8
     * @param params Req Body Keys and Values
     * @return String - Req Body Keys and Values in proper format
     */
    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /**
     * Reformats the Req Body to the correct format for UTF-8
     * @param params Req Body Keys and Values
     * @return String - Req Body Keys and Values in proper format
     */
    private static String getJSONPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        JSONObject json = new JSONObject();
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            json.put(pair.getKey().toString(), pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        return json.toString();
    }

    /**
     * Send a GET request
     * @param token - Authorization token
     * @param url - URL of GET Request
     * @return String - Response from API Call
     */
    public static String getRequest(String token, String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = con.getResponseCode();

        if(responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "";
        }
    }

    /**
     * Send a POST request
     * @param post - Flag for whether this request is a post rather than patch
     * @param authorized - Flag for whether this request is authorized already (You have a token)
     * @param token - Authorization token
     * @param url - URL of GET Request
     * @param reqBody - POST Request Body
     * @return String - Response from API Call
     */
    public static String postRequest(boolean post,
                                     boolean authorized,
                                     String token,
                                     String url,
                                     HashMap<String, String> reqBody,
                                     JSONObject json) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        if(post){
            con.setRequestMethod("POST");
        } else {
            con.setRequestMethod("PATCH");
        }

        String urlParameters;
        if(authorized){
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + token);
            urlParameters = json.toString();
        } else {
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlParameters = getPostDataString(reqBody);
        }

        // Send post request
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        BufferedWriter wr = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        wr.write(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if(responseCode == 200) {
            return response.toString();
        } else {
            return "Error: \n" + response.toString();
        }
    }

    /**
     * Send an authorized PATCH request
     * @param token - Authorization token
     * @param url - URL of GET Request
     * @param json - POST Request Body
     * @return String - Response from API Call
     */
    public static String authPatchRequest(String token,
                                     String url,
                                     JSONObject json) throws Exception {
        return postRequest(false, true , token, url, new HashMap<>(), json);
    }

    public static String getAuthToken(){
        HashMap<String, String> map = new HashMap<>();
        map.put(StringRef.GRANT_TYPE, StringRef.CLIENT_CREDENTIALS);
        map.put(StringRef.CLIENT_ID, StringRef.ADMIN_CLIENT_ID);
        map.put(StringRef.CLIENT_SECRET, StringRef.ADMIN_CLIENT_SECRET);
        String authToken = "";
        try {
            String response = postRequest(true, false, "" ,
                    StringRef.API_STAGING_URL_PREFIX + "oauth2/token", map, new JSONObject());
            JSONObject obj = new JSONObject(response);
            String token = obj.getString(StringRef.ACCESS_TOKEN);
            authToken = token;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return authToken;
    }
}
