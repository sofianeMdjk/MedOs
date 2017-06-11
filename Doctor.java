/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author sofiane
 */
public class Doctor {
    
    
    public class GoogleMap
    {
        double longitude;
        double latitude;

        public GoogleMap(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }
        
        
    }
    static String id = "1";
    String fName;
    String sName;
    String badge;
    String adress;
    String email;
    LinkedList<String> phoneNumbers;
    int gender;
    String password;
    GoogleMap local;
    String description;
    String Specialite;

    
    //finale String url;
    public Doctor(String fName, String sName, String badge, String adress, String email, LinkedList<String> phoneNumbers, int gender,String password, GoogleMap local,String description,String specialite) {
        
        this.fName = fName;
        this.sName = sName;
        this.badge = badge;
        this.adress = adress;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.gender=gender;
        this.password=password;
        this.local = local;
        this.Specialite=specialite;
        this.description=description;
    }
    
    public void modifyInfos(String fName, String sName,String password, String badge, String adress, String email, LinkedList<String> phoneNumbers, GoogleMap local,String description,String specialite)
    {
        this.fName = fName;
        this.sName = sName;
        this.badge = badge;
        this.password=password;
        this.adress = adress;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.description=description;
        this.Specialite=specialite;
    }
    
    public static String signIn(String u,String p)
    {
        
        String url=MainApp.ip_adress+"/medos/php/medecin/loginm.php";
        HashMap <String,String> data=null;
        data = new HashMap<>();
        data.put("email",u);
        data.put("password", p);
        return QueryUtils.makeHttpPostRequest(url, data);
        
    }
    
    public String signUp()
    {
        String url=MainApp.ip_adress+"/medos/php/medecin/signupm.php";
        String phone="";
        for(int i=0;i<phoneNumbers.size();i++)
        {
            phone+=phoneNumbers.get(i)+"/";
        }
        
        
        String gen=""+gender;
        
        
        System.out.println("sample.Doctor.signUp()");
        
        HashMap<String,String> data=new HashMap<>();
        data.put("lname", fName);
        data.put("fname", sName);
        data.put("telp", phone);
        data.put("plaque", badge);
        data.put("adrm", adress);
        data.put("email", email);
        data.put("password", password);
        data.put("specialite", Specialite);
        data.put("description", description);
        data.put("gender", gen);
        System.out.println("doc class" +GoogleMapsDisplayController.idLoc);
        data.put("id_l", GoogleMapsDisplayController.idLoc);
        
        
        
        System.out.println(QueryUtils.makeHttpPostRequest(url, data));
    return null;
    }
    
    public static String[] getDoc(String id)
    {
        HashMap<String,String> data=new HashMap<>();
        data.put("id", id);
         //String url="http://192.168.8.105/medos/php/medecin/listem.php";
         
         String url=MainApp.ip_adress+"/medos/php/medecin/listem.php";
         System.out.println(QueryUtils.makeHttpPostRequest(url, data));
        String []tab=QueryUtils.makeHttpPostRequest(url, data).split("#");
        
        return tab;
    }
    
    
    
    
    
    
    
}
