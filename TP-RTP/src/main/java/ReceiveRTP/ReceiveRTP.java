/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReceiveRTP;

/**
 *
 * @author slixo
 */

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.protocol.DataSource;


public class ReceiveRTP {

public static void main(String[] args) {
        try {
         Player player;     
            // medialocator to receive data from this url : includes the sender that we want to receive data from
            
            // ip de la destination qui envoi le flux : port de la source pour la reception du flux
            MediaLocator url = new MediaLocator("rtp://127.0.0.1:10000/audio/1");
            
            //or DS
            DataSource source = Manager.createDataSource(url);
            player = Manager.createPlayer(source);
            
            
            //creating a player to receive data
            //player = Manager.createPlayer(url);
            player.realize();
            player.start();
            
            System.out.println("Receiving...");
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    
}
}



