/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author sofiane
 */
public class ServerRequests {
    String url;

    public ServerRequests(String u) {
        this.url=u;
    }
    
    public void postSignin(String username,String password) throws MalformedURLException, ProtocolException
    {
        
        
    }
    
    
}
