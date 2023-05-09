/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Client;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.gui.SessionManagerClient;

import static com.mycompany.myapp.services.ServiceClient.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author aymen
 */
public class ServiceClient {

    public ArrayList<Client> users;

    public static ServiceClient instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceClient() {
        req = new ConnectionRequest();
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }

    public boolean addClientJSON(Role r, Client c) {

        String img = c.getImg();
        int gsm = c.getGsm();
        String email = c.getEmail();
        String pass = c.getPassword();

        //String url = Statics.BASE_URL + "addRoleJSON/new?id_user=" + id_user + "&libelle=" + libelle;
        String url = Statics.BASE_URL + "/client/addClientJSON/new" + "/" + r.getId_role() + "/" + img + "/" + gsm + "/" + email + "/" + pass;
        // String url = Statics.BASE_URL + "/new/idUser<\\d+>" ;

        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void login(Client c) {
        // construire l'URL de la requête
        String email = c.getEmail();
        String password = c.getPassword();

        String url = Statics.BASE_URL + "/client/login" + "/" + email + "/" + password;

        req.setUrl(url);
        req.setPost(true);

        // créer la requête
        // ajouter un écouteur pour traiter la réponse
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt
            ) {
                if (req.getResponseCode() == 200) {
                    JSONParser jsonParser = new JSONParser();
                    try {
                        Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                        String img = (String) response.get("img");

                        int id_client = ((Double) response.get("id_client")).intValue();
                        System.out.println(id_client);

                        int gsm = ((Double) response.get("gsm")).intValue();
                        System.out.println(gsm);
                        // float id = Float.parseFloat(client.get("id_client").toString());
                        // SessionManager.setId((int) id);
                        SessionManagerClient.setId(id_client);
                        SessionManagerClient.setGsm(gsm);
                        SessionManagerClient.setPhoto(img);
                        SessionManagerClient.setPassowrd(password);
                        SessionManagerClient.setEmail(email);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                req.removeResponseListener(this);
            }
        }
        );

        // envoyer la requête
        NetworkManager.getInstance()
                .addToQueueAndWait(req);
    }

    public boolean deleteCli(int id_client) {
        String url = Statics.BASE_URL + "/client/deleteCliJSON" + "/" + (int) id_client;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public static void EditCli(int id, int gsm, String Email, String Password) {
        String url = Statics.BASE_URL + "/client/editCliJson" + "/" + id + "/" + gsm + "/" + Email + "/" + Password;
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id_client", String.valueOf(SessionManagerClient.getId()));
        req.addArgument("gsm", String.valueOf(gsm));
        req.addArgument("email", Email);
        req.addArgument("password", Password);

        System.out.println(Email);
        req.addResponseListener((response) -> {

            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            //if(s.equals("success")){}
            //else {
            //Dialog.show("Erreur","Echec de modification", "Ok", null);
            //}
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void sms(TextField gsm, Resources rs) {

        String url = Statics.BASE_URL + "/client/forget" + "/" + gsm.getText();
        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance()
                .addToQueueAndWait(req);

    }

}
