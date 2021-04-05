/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorypattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Bence
 */
public class FactoryServer {
    public static void main(String[] args){
        ArrayList<Playable> instruments= new ArrayList<>();
        ArrayList<ArrayList<String>> messages= new ArrayList<>();
        try{
            System.out.println("Fut a factory szerver.");
            ServerSocket ss=new ServerSocket(6898+10);
            while(true){
                Socket s=ss.accept();
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(s.getInputStream()));
                String msgClient=fromClient.readLine();
                System.out.println(msgClient);
                PrintStream ps= new PrintStream(s.getOutputStream());
                int index=msgClient.indexOf(",");                   //megkeressük az első vesszőt, ha nincs, akkor -1-et ad vissza
                if(index==-1)                                       //ez esetben a klienstől kapott string hosszát állítjuk indexnek
                    index=msgClient.length();
                String msgParsed=msgClient.substring(index);        //ketté szedjük a stringet, az eleje alapján vezérlünk, a végében van az adat
                String msgBeginning=msgClient.substring(0, index);
                switch(msgBeginning){
                    case "lejátszás":
                        if(!instruments.isEmpty()){
                            for(Playable inst: instruments){
                                inst.PlaySound();
                                messages.add(inst.GetMsg());        //lejátszuk az összes hangot és kinyerjük a kliens felé menő üzeneteket
                            }
                            for(int i=0; i<messages.size(); i++){
                                for(int j=0; j<messages.get(i).size(); j++){
                                    ps.println(messages.get(i).get(j));     //kliensnek átküldjük az adatokat
                                }
                            }
                            for(Playable inst: instruments){
                                inst.ClearMsg();                    //töröljük az összes referenciát
                            }
                            messages.clear();
                        }
                        else
                            ps.println("Nincs hangszer regisztrálva!");
                        break;
                    case "játszd":
                        if(!instruments.isEmpty()){
                            msgParsed=msgParsed.substring(1);           // vesszővel kezdődik, ezt eldobjuk
                            int ind=msgParsed.indexOf(",");
                            int which=Integer.parseInt(msgParsed.substring(0, ind));    //az utasítás után jön a címzés
                            String msgUsefulParsed=msgParsed.substring(ind+1);          //címzés után az adat
                            if(which<instruments.size()){
                                String[] tempStringArray;
                                tempStringArray = msgUsefulParsed.split(",");           //adatokat szétválasztjuk
                                int[] tempToInt=new int[tempStringArray.length];
                                for(int i=0; i<tempToInt.length; i++){
                                    tempToInt[i]=Integer.parseInt(tempStringArray[i]);  //integerré váltjuk
                                }
                                instruments.get(which).PlayNote(tempToInt);             //majd átküldjük a hangszernek
                                ArrayList<String> msgs= instruments.get(which).GetMsg();//kinyerjük a kliens felé küldendő válaszokat
                                for(String msg: msgs){
                                    ps.println(msg);                                    //átküldjük a kliensnek
                                }
                                instruments.get(which).ClearMsg();                      //törlünk
                            }
                            else
                                ps.println("Nincs ilyen sorszámú hangszer regisztrálva: "+which);
                        }
                        else
                            ps.println("Nincs hangszer regisztrálva!");
                        break;
                    default:
                        try{
                            instruments.add(PlayableFactory.getInstrument(msgClient));        //getInstrument dobhat exceptiont
                            ps.println("Hangszer létrejött");
                        }
                        catch(Exception e){
                            String str="Nincs ilyen hangszer: " + msgClient;
                            System.out.println(str);
                            ps.println(str);
                        }
                }
                fromClient.close();
                s.close();
                ps.close();
            }
        }
        catch(Exception e) {
            System.out.println("A szerver nem várt kivételt kapott, az alkalmazás leáll!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
