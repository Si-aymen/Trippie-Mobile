/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUtilisateur {

    public ArrayList<Utilisateur> users;

    public static ServiceUtilisateur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Utilisateur user;
    private ServiceUtilisateur() {
        req = new ConnectionRequest();
    }

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public Utilisateur addUtilisateurJSON(Utilisateur u) {
        String cin = u.getCin();
        String name = u.getNom();
        String lname = u.getPrenom();
        String url = Statics.BASE_URL + "addUtilisateurJSON/new" + "/" + cin + "/" + name + "/" + lname;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    JSONParser jsonParser = new JSONParser();
                    try {
                        Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                        int id_user = ((Double) response.get("id_user")).intValue();
                        Utilisateur utilisateur = new Utilisateur(id_user, cin, name, lname);
                        user = utilisateur;

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

    public ArrayList<Utilisateur> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Utilisateur u = new Utilisateur();
                float id = Float.parseFloat(obj.get("id_user").toString());

                if (obj.get("cin") == null || obj.get("nom") == null || obj.get("prenom") == null) {
                    u.setCin("null");
                    u.setNom("null");
                    u.setPrenom("null");
                } else {
                    u.setCin(obj.get("cin").toString());
                    u.setNom(obj.get("nom").toString());
                    u.setPrenom(obj.get("prenom").toString());
                }
                users.add(u);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public ArrayList<Utilisateur> getAllUsers() {
        String url = Statics.BASE_URL + "AllUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

}
