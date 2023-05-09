/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cov;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCov {

    public ArrayList<Cov> covs;

    public static ServiceCov instance = null;
    public boolean resultOK;
    private ConnectionRequest req, cr;
    public int resultCode;

    private ServiceCov() {
        req = new ConnectionRequest();
    }

    public static ServiceCov getInstance() {
        if (instance == null) {
            instance = new ServiceCov();
        }
        return instance;
    }

    public boolean addTask(Cov v) {
        String url = Statics.BASE_URL + "/addCo_voiturageJSON/new?depart=" + v.getDepart() + "&destination=" + v.getDestination() + "&nmbr_place=" + v.getNbre_place();

        req.setUrl(url);
        req.setPost(false);

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

    public ArrayList<Cov> getAll() {
        covs = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/covmobile/list");
        System.out.println("url = " + cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    covs = (ArrayList<Cov>) getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return covs;
    }

    private ArrayList<Cov> getList() {
        JSONParser jsonp;
        jsonp = new JSONParser();

        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            for (Map<String, Object> obj : list) {
                Cov r = new Cov();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setDepart((String) obj.get("depart").toString());
                r.setDestination((String) obj.get("destination").toString());

                r.setId((int) id);
                covs.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return covs;
    }

    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deleteCovJSON/" + id);
        System.out.println("url delete : " + cr);
        cr.setHttpMethod("POST"); //
        cr.addArgument("id", String.valueOf(id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public int edit(Cov v) {
        return manage(v);
    }

    public boolean modifier(Cov v) {

        String url = Statics.BASE_URL + "/updateCovoiturageJSON/" + v.getId() + "?depart=" + v.getDepart() + "&destination=" + v.getDestination() + "&nmbr_place=" + v.getNbre_place();

        req.setUrl(url);

        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK
                        = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public int manage(Cov v) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("GET");
        cr.setUrl(Statics.BASE_URL
                + "/updateCovoiturageJSON/?id=" + (int) v.getId() + "&depart="
                + v.getDepart() + "&destination=" + v.getDestination() + "&number of seats ="
                + v.getNbre_place());

        cr.addArgument("id", String.valueOf(v.getId()));
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode
                        = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

}
