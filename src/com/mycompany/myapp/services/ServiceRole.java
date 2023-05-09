/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;

import static com.mycompany.myapp.services.ServiceRole.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author aymen
 */
public class ServiceRole {

    public ArrayList<Role> users;

    public static ServiceRole instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Role role;

    private ServiceRole() {
        req = new ConnectionRequest();
    }

    public static ServiceRole getInstance() {
        if (instance == null) {
            instance = new ServiceRole();
        }
        return instance;
    }

    public Role addRoleJSON(Role r, Utilisateur u) {

        String libelle = r.getLibelle();

        //String url = Statics.BASE_URL + "addRoleJSON/new?id_user=" + id_user + "&libelle=" + libelle;
        String url = Statics.BASE_URL + "/role/addRoleJSON/new" + "/" + u.getId_user() + "/" + libelle;
        // String url = Statics.BASE_URL + "/new/idUser<\\d+>" ;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    JSONParser jsonParser = new JSONParser();
                    try {
                        Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                        int id_role = ((Double) response.get("id_role")).intValue();
                        Role rolee = new Role(id_role, libelle);
                        role = rolee;

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return role;
    }

}
