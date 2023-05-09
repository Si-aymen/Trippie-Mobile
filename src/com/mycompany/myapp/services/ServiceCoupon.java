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
import com.mycompany.myapp.entities.Coupon;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
/**
 *
 * @author HP
 */
public class ServiceCoupon {
    public ArrayList<Coupon> Stations;
    public static ServiceCoupon instance = null;
    public Boolean result =false;
    private ConnectionRequest req;
     private ConnectionRequest reqpost;
    private ServiceCoupon(){
        req =new ConnectionRequest();
        //reqpost =new ConnectionRequest();
    }
    public static ServiceCoupon getInstance() {
        if (instance == null) {
            instance = new ServiceCoupon();
        }
        return instance;
    }
    //bech naaytu ll crud hne eli zednhm f symfiny w yrj3ou jdonResult w nzedu alihm code ekher nmchyw taw nchufu test taae methode json  fi symfony 
    //singleton
       public ArrayList<Coupon> coupon;
    //public static ServiceCoupon instance =null;
    //inisialisation connection 
    //private ConnectionRequest req,cr;
    
    
   
 
    //ajout 
public void ajouterCoupon(Coupon coupon) {
    String url = Statics.BASE_URL + "/addcouponJson/new?date_debut=" + coupon.getDate_debut()
            + "&date_expiration=" + coupon.getDate_expiratio()+ "&taux=" + coupon.getTaux()
            + "&code_coupon=" + coupon.getCode_coupon() + "&nbr_utilisation=" + coupon.getNbr_utilisation()
            + "&type=" + coupon.getType();

    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.addResponseListener((NetworkEvent evt) -> {
        String response = new String(req.getResponseData());
        System.out.println("data==" + response);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}



 

//  public ArrayList<Coupon> getAll() {
//    ArrayList<Coupon> coupons = new ArrayList<>();
//
//    cr = new ConnectionRequest();
//    cr.setUrl(Statics.BASE_URL + "/Allcoupons");
//    System.out.println("url = " + cr);
//    cr.setHttpMethod("GET");
//
//    cr.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            if (cr.getResponseCode() == 200) {
//                coupon = getList();
//            }
//
//            cr.removeResponseListener(this);
//        }
//    });
//
//    try {
//        cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
//        NetworkManager.getInstance().addToQueueAndWait(cr);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return coupons;
//}
//
//private ArrayList<Coupon> getList() {
//    JSONParser jsonp = new JSONParser();
//    ArrayList<Coupon> coupons = new ArrayList<>();
//
//    try {
//        Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
//                new String(cr.getResponseData()).toCharArray()
//        ));
//        List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
//        for (Map<String, Object> obj : list) {
//            Coupon coupon = new Coupon();
//            
//            
//           float id = Float.parseFloat(obj.get("id").toString());
//coupon.setCode_coupon(obj.get("code_coupon").toString());
//coupon.setType(obj.get("type").toString());
//
//// Assuming date_debut and date_expiratio are in the format "yyyy-MM-dd"
//String dateDebutStr = obj.get("date_debut").toString();
//String dateExpiratioStr = obj.get("date_expiratio").toString();
//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//try {
//    Date dateDebut = dateFormat.parse(dateDebutStr);
//    Date dateExpiratio = dateFormat.parse(dateExpiratioStr);
//    coupon.setDate_debut(dateDebut);
//    coupon.setDate_expiratio(dateExpiratio);
//} catch (ParseException e) {
//    e.printStackTrace();
//}
//
//int nbrUtilisation = Integer.parseInt(obj.get("nbr_utilisation").toString());
//coupon.setNbr_utilisation(nbrUtilisation);
//
//
//
//            
//            
//
//            coupon.setId((int) id);
//
//            coupons.add(coupon);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//    return coupons;
//}
//
//
//
//
//
//public void detail(int id, Coupon coupon) {
//    String url = Statics.BASE_URL + "/coupon1?id=" + id;
//    req.setUrl(url);
//    req.addResponseListener((evt) -> {
//        JSONParser jsonp = new JSONParser();
//        try {
//            Map<String, Object> mapCoupon = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//
//            coupon.setId(Integer.parseInt(mapCoupon.get("id").toString()));
//            coupon.setTaux(Integer.parseInt(mapCoupon.get("taux").toString()));
//            coupon.setCode_coupon(mapCoupon.get("code_coupon").toString());
//            coupon.setNbr_utilisation(Integer.parseInt(mapCoupon.get("nbr_utilisation").toString()));
//            coupon.setType(mapCoupon.get("type").toString());
//
//            String dateString = mapCoupon.get("date_debut").toString();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date date = dateFormat.parse(dateString);
//                coupon.setDate_debut(date);
//            } catch (ParseException e) {
//                // Handle the parse exception for date_debut
//            }
//
//            String expirationDateString = mapCoupon.get("date_expiratio").toString();
//            try {
//                Date expirationDate = dateFormat.parse(expirationDateString);
//                coupon.setDate_expiratio(expirationDate);
//            } catch (ParseException e) {
//                // Handle the parse exception for date_expiratio
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    
//  
//}



 public ArrayList<Coupon> parseStations(String jsonText) {
        try {
            Stations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Coupon s = new Coupon();
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int) id);
                if (obj.get("date_debut") == null) {
                    s.setDate_debut("null");
                }else{
                    s.setDate_debut(obj.get("date_debut").toString());
                }
                if (obj.get("date_experatio") == null) {
                    s.setDate_expiratio("null");
                }else{
                    s.setDate_expiratio(obj.get("date_experatio").toString());
                }
                
                
                
                
                 if (obj.get("code_coupon") == null) {
                    s.setCode_coupon("null");
                } else {
                    s.setCode_coupon(obj.get("code_coupon").toString());
                }
                  if (obj.get("type") == null) {
                    s.setType("null");
                } else {
                    s.setType(obj.get("type").toString());
                }
                
                 s.setTaux(((int) Float.parseFloat(obj.get("taux").toString())));

                   s.setNbr_utilisation(((int) Float.parseFloat(obj.get("nbr_utilisation").toString())));

                
                
                
                Stations.add(s);
            }
                
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Stations;
    }  
    
    public ArrayList<Coupon> getAllStations() {
        String url = Statics.BASE_URL + "/Allcoupons";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Stations = parseStations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Stations;
    }
    
    
    
    
      public boolean deleteCoupon(int id) {
        // Create the URL for the delete request
        String url = "http://your-api-url/deletecouponJSON/" + id;

        // Create the ConnectionRequest
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("DELETE");

        // Add a response listener to handle the server's response
        request.addResponseListener(e -> {
            NetworkEvent networkEvent = (NetworkEvent) e;
            int responseCode = networkEvent.getResponseCode();

            if (responseCode == 200) {
                // Deletion successful
                System.out.println("Coupon deleted successfully");
            } else {
                // Deletion failed
                System.out.println("Failed to delete coupon");
            }
        });

        // Send the request
        NetworkManager.getInstance().addToQueueAndWait(request);

        // Check if the deletion was successful
        int responseCode = request.getResponseCode();
        return responseCode == 200;
    }
      //http://127.0.0.1:8000/updatecouponJSON/6?date_debut=2023-05-05&date_expiratio=2023-05-01&taux=10&code_coupon=rimouta&nbr_utilisation=7&type=vip
      
      public boolean modifierReservationExcursion(Coupon Reservation) {
        String url = Statics.BASE_URL +"/updatecouponJSON/"+Reservation.getId()+"?date_debut="+Reservation.getDate_debut()+"&date_expiratio="+Reservation.getDate_expiratio()
                +"&taux="+Reservation.getTaux()+"&code_coupon="+Reservation.getCode_coupon()+"&nbr_utilisation="+Reservation.getNbr_utilisation()+"&type="+Reservation.getType();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200
;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return result;
      }
      
      public void supprimer(Coupon coupon){
          req = new ConnectionRequest();
          req.setInsecure(true);
          this.req.setUrl(Statics.BASE_URL+"/deletecouponJSON/"+coupon.getId());
          this.req.setHttpMethod("DELETE");
          NetworkManager.getInstance().addToQueue(req);
          
          
      }
      

}
