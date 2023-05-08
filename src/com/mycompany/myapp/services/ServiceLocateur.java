/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Locateur;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.gui.SessionManagerLocateur;

import static com.mycompany.myapp.services.ServiceLocateur.instance;
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
public class ServiceLocateur {

    public ArrayList<Locateur> users;

    public static ServiceLocateur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Locateur role;

    private ServiceLocateur() {
        req = new ConnectionRequest();
    }

    public static ServiceLocateur getInstance() {
        if (instance == null) {
            instance = new ServiceLocateur();
        }
        return instance;
    }

    public boolean addLocateurJSON(Role r, Locateur c) {

        String img = c.getImg();
        int gsm = c.getGsm();
        String email = c.getEmail();
        String pass = c.getPassword();
        String nom_agence = c.getNom_agence();

        //String url = Statics.BASE_URL + "addRoleJSON/new?id_user=" + id_user + "&libelle=" + libelle;
        String url = Statics.BASE_URL + "locateur/addLocateurJSON/new" + "/" + r.getId_role() + "/" + img + "/" + gsm + "/" + nom_agence + "/" + email + "/" + pass;
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

    public void login(Locateur c) {
        // construire l'URL de la requête
        String email = c.getEmail();
        String password = c.getPassword();

        String url = Statics.BASE_URL + "locateur/login" + "/" + email + "/" + password;

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
                     
                        String nom_agence = (String) response.get("nom_agence");
                        int id_loc = ((Double) response.get("id_loc")).intValue();

                        System.out.println(id_loc);

                        int gsm = ((Double) response.get("gsm")).intValue();
                        System.out.println(gsm);

                        // float id = Float.parseFloat(client.get("id_loc").toString());
                        // SessionManager.setId((int) id);
                        SessionManagerLocateur.setId(id_loc);
                        SessionManagerLocateur.setNomAgence(nom_agence);
                        SessionManagerLocateur.setGsm(gsm);
                        SessionManagerLocateur.setPhoto(img);
                        SessionManagerLocateur.setPassowrd(password);
                        SessionManagerLocateur.setEmail(email);

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
    
       public boolean deleteLoc(int id_loc) {
        String url = Statics.BASE_URL + "locateur/deleteLocJSON" + "/" + (int) id_loc;

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

    public static void EditLoc(int id, int gsm, String nom_agence,String Email,String Password) {
        String url = Statics.BASE_URL + "locateur/editLocJson" + "/" + id + "/" + gsm + "/" + nom_agence + "/" + Email + "/" + Password;
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id_ch", String.valueOf(SessionManagerLocateur.getId()));
        req.addArgument("gsm", String.valueOf(gsm));
        req.addArgument("nom_agence", nom_agence);
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

}
