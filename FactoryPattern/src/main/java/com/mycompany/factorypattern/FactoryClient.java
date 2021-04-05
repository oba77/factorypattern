/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorypattern;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Bence
 */
public class FactoryClient {
    public static void main(String[] args){
        try{
            System.out.println("Fut a factory kliens.");
            System.out.println("Hangszer kérése: zongora -> Piano, gitár -> Guitar.");
            BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
            String msg, msgBack;
            while(!((msg=kb.readLine()).equals("kilépés"))){            //ameddig nem 'kilépés', addig olvasunk a kliens billentyűzetéről
                Socket s=new Socket("localhost", 6898+10);
                BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream fromClient=new DataOutputStream(s.getOutputStream());
                fromClient.writeBytes(msg+'\n');                        //átküldjük a szervernek
                while((msgBack=br.readLine())!=null){                   //szerver összes válaszát átvesszük és kiírjuk
                    System.out.println(msgBack);
                }
                fromClient.close();
                s.close();
                br.close();
            }
            kb.close();
            System.exit(0);
        }
        catch(Exception e) {
            System.out.println("Az alkalmazás nem várt kivételt kapott, ezért leáll!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
