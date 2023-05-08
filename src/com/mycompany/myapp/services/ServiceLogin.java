/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.SessionManagerClient;
import com.mycompany.myapp.utils.Statics;
import java.util.Map;

/**
 *
 * @author aymen
 */
public class ServiceLogin {
     private ConnectionRequest req;
    
     public void signin(TextField Email,TextField Password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/signin?Email="+Email.getText().toString()+"&Password="+Password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                 //new WalkthruForm(rs).show();
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManagerClient.setId((int)id);
                
                SessionManagerClient.setPassowrd(user.get("Password").toString());
                SessionManagerClient.setUserName(user.get("UserName").toString());
                SessionManagerClient.setEmail(user.get("Email").toString());
                
               
                
                //photo 
                
                if(user.get("photo") != null)
                    SessionManagerClient.setPhoto(user.get("photo").toString());
                
                System.out.println("currnt user ==>"+SessionManagerClient.getEmail()+","+SessionManagerClient.getPassowrd()+","+SessionManagerClient.getUserName());
                
                
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
