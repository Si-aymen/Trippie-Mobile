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
import com.mycompany.myapp.entities.Chauffeur;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.gui.SessionManagerChauffeur;

import static com.mycompany.myapp.services.ServiceChauffeur.instance;
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
public class ServiceChauffeur {

    public ArrayList<Chauffeur> users;

    public static ServiceChauffeur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Chauffeur role;

    private ServiceChauffeur() {
        req = new ConnectionRequest();
    }

    public static ServiceChauffeur getInstance() {
        if (instance == null) {
            instance = new ServiceChauffeur();
        }
        return instance;
    }

    public boolean addChauffeurJSON(Role r, Chauffeur c) {

        String img = c.getImg();
        int gsm = c.getGsm();
        String email = c.getEmail();
        String pass = c.getPassword();
        String num_permis = c.getNum_permis();

        //String url = Statics.BASE_URL + "addRoleJSON/new?id_user=" + id_user + "&libelle=" + libelle;
        String url = Statics.BASE_URL + "/chauffeur/addChauffeurJSON/new" + "/" + r.getId_role() + "/" + img + "/" + gsm + "/" + num_permis + "/" + email + "/" + pass;
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

    public void login(Chauffeur c) {
        // construire l'URL de la requête
        String email = c.getEmail();
        String password = c.getPassword();

        String url = Statics.BASE_URL + "/chauffeur/login" + "/" + email + "/" + password;

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

                        int id_ch = ((Double) response.get("id_ch")).intValue();
                        System.out.println(id_ch);

                        int gsm = ((Double) response.get("gsm")).intValue();
                        System.out.println(gsm);
                        String num_permis = (String) response.get("num_permis");
                        // float id = Float.parseFloat(client.get("id_ch").toString());
                        // SessionManager.setId((int) id);
                        SessionManagerChauffeur.setNum_permis(num_permis);
                        SessionManagerChauffeur.setId(id_ch);
                        SessionManagerChauffeur.setGsm(gsm);
                        SessionManagerChauffeur.setPhoto(img);
                        SessionManagerChauffeur.setPassowrd(password);
                        SessionManagerChauffeur.setEmail(email);

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

    public boolean deleteCh(int id_ch) {
        String url = Statics.BASE_URL + "/chauffeur/deleteChJSON" + "/" + (int) id_ch;

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

    public static void EditCh(int id, int gsm, String Num_permis,String Email,String Password) {
        String url = Statics.BASE_URL + "/chauffeur/editChJson" + "/" + id + "/" + gsm + "/" + Num_permis + "/" + Email + "/" + Password;
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id_ch", String.valueOf(SessionManagerChauffeur.getId()));
        req.addArgument("gsm", String.valueOf(gsm));
        req.addArgument("num_permis", Num_permis);
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
