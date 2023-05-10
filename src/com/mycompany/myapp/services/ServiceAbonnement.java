package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceAbonnement {

    public ArrayList<Abonnement> abonnements;

    public static ServiceAbonnement instance = null;
    public boolean resultOK;
    private ConnectionRequest req, cr;
    public int resultCode;

    private ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }

    public boolean addTask(Abonnement v) {
        String url = Statics.BASE_URL + "/addAbonnementJSON/new?type=" + v.getType()
                +  "&prix=" + v.getPrix();

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

    public ArrayList<Abonnement> getAll() {
        abonnements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/abonnementmobile/list");
        System.out.println("url = " + cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    abonnements= (ArrayList<Abonnement>) getList();
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

        return abonnements;
    }

    private ArrayList<Abonnement> getList() {
        JSONParser jsonp;
        jsonp = new JSONParser();

        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            for (Map<String, Object> obj : list) {
                Abonnement r = new Abonnement();
                //int idA = Integer.parseInt(obj.get("idA").toString());
                r.setType((String) obj.get("type").toString());
                r.setPrix((String) obj.get("prix").toString());
               
              //  r.setIdA((int) idA);

                abonnements.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abonnements;
    }

    public int delete(int idA) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deleteAbonnementJSON/" + idA);
        System.out.println("url delete : " + cr);
        cr.setHttpMethod("POST");
        cr.addArgument("idA", String.valueOf(idA));

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

    public int edit(Abonnement v) {
        return manage(v);
    }

    public boolean modifier(Abonnement v) {

        String url = Statics.BASE_URL + "/updateAbonnementJSON/" + v.getIdA() + "?type=" + v.getType() + "&Prix=" + v.getPrix() 
              ;

        req.setUrl(url);

        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public int manage(Abonnement v) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("GET");
        cr.setUrl(Statics.BASE_URL + "/updateAbonnementJSON/?idA=" + (int) v.getIdA() + "&type=" + v.getType() + "&prix=" + v.getPrix());
        cr.addArgument("id", String.valueOf(v.getIdA()));
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
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