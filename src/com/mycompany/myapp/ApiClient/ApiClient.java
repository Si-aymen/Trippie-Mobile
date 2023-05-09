/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.ApiClient;

/**
 *
 * @author mtirn
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ApiClient {

    private static final String BASE_URL = "   http://127.0.0.1:8000/";

public static List<Map<String, Object>> fetchAbonnements() {
    String url = BASE_URL + "abonnements";
    ConnectionRequest request = new ConnectionRequest(url);
    NetworkManager.getInstance().addToQueueAndWait(request);
    if (request.getResponseCode() == 200) {
        try {
            JSONParser parser = new JSONParser();
            List<Map<String, Object>> abonnements = (List<Map<String, Object>>) parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            return abonnements;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return null;
}
}