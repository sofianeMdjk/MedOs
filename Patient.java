/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author sofiane
 */
public class Patient {

   int idPatient;
   String fName;
   String sName;
   String phoneNumber;
   String birthDate;
   String email;
   int gender;
   Date registrationDate;
   
   public Patient()
   {
       
   }
    public Patient(int idPatient, String fName, String sName, String birthDate,String phoneNumber, String email, Date registrationDate,int gender) {
        this.idPatient = idPatient;
        this.fName = fName;
        this.sName = sName;
        this.birthDate = birthDate;
        this.phoneNumber= phoneNumber;
        this.email = email;
        this.gender=gender;
        this.registrationDate = registrationDate;
    }
    
    public static String[] getPat(String id)
    {
        String url=MainApp.ip_adress+"/medos/php/patient/listep.php";
        HashMap<String,String> data=new HashMap<>();
        data.put("id", id);
        
        String result=QueryUtils.makeHttpPostRequest(url, data);
        String []tab=result.split("#");
        return tab;
    }
    
    public void setPatientInfo(String[] tab)
    {
        this.fName=tab[1];
        this.sName=tab[2];
        this.birthDate=tab[3];
        this.phoneNumber=tab[5];
        this.email=tab[4];
        
    }
    
    
   
    
    
    
}
