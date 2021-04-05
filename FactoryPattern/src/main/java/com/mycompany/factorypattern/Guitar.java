package com.mycompany.factorypattern;


import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bence
 */
public class Guitar extends Instrument {
    
    //6 húros
    private final int str=5;
    //14 fretes, de a 0-s fret a lefogatlan húrt reprezentálja
    private final int fret=14;
    
    //nem lefogottként inicializáljuk a húrokat
    public Guitar(){
        this.keys = new ArrayList<>();
        for(int i=0; i<((str+1)*fret)+1; i++){
            keys.add(i, Boolean.FALSE);
        }
        this.message= new ArrayList<>();
    }

    //lefogjuk a húrokat, első
    @Override
    public void PlayNote(int... notes) {
        if(notes.length<=1){
            System.out.println("Nincs ilyen húr, túl kevés input");
            this.message.add("Nincs ilyen húr, túl kevés input");
        }
        else{
            if(notes.length%2==0){
                for(int i=0; i<notes.length; i++){
                    if(notes[i]<0 || notes[i]>fret || notes[i+1]<0 || notes[i+1]>str){
                        System.out.println("Nincs ilyen húr: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                        this.message.add("Nincs ilyen húr: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                    }
                    else{
                        keys.set((((notes[i+1])*fret)+notes[i]), Boolean.TRUE);   //húr*fret+fret pozíció (húrt 1-től hatig számolom)
                        this.message.add("Húr beállítva: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                    }
                    i++;
                }
            }
            else{
                System.out.println("Nincs ilyen pozíció: " + notes[notes.length-1] + ", hiányzik a húr.");
                this.message.add("Nincs ilyen pozíció: " + notes[notes.length-1] + ", hiányzik a húr.");
                for(int i=0; i<notes.length-1; i++){
                    if(notes[i]<0 || notes[i]>fret || notes[i+1]<0 || notes[i+1]>str){
                        System.out.println("Nincs ilyen húr: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                        this.message.add("Nincs ilyen húr: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                    }
                    else{
                        keys.set((((notes[i+1])*fret)+notes[i]), Boolean.TRUE);   //húr*fret+fret pozíció (húrt 1-től hatig számolom)
                        this.message.add("Húr beállítva: " + (notes[i]+1) + ", " + (notes[i+1]+1));
                    }
                    i++;
                }
            }
        }
    }
    
    //megpendítjük a húrokat (pl lehetne valami hangfájl lejátszása)
    @Override
    public void PlaySound() {
        int i=0;
        for(Boolean key : keys){
            if(key==true){
                if(i%fret==0){
                    System.out.println("A megpendített húr a "+(((i-1)/fret)+1)+"., a "+(((i-1)%fret)+2)+". pozícióban");
                    this.message.add("A megpendített húr a "+(((i-1)/fret)+1)+"., a "+(((i-1)%fret)+2)+". pozícióban");
                }else{
                    System.out.println("A megpendített húr a "+(((i-1)/fret)+1)+"., a "+((i%fret)+1)+". pozícióban");
                    this.message.add("A megpendített húr a "+(((i-1)/fret)+1)+"., a "+((i%fret)+1)+". pozícióban");
                }
            }
            i++;
        }
        //kinullázzuk a húrokat
        i=0;
        for(Boolean key : keys){
            if(key==true){
                keys.set(i, Boolean.FALSE);
            }
            i++;
        }
        
    }

    @Override
    public ArrayList<String> GetMsg() {
        return this.message;
    }
    
    @Override
    public void ClearMsg(){
        this.message.clear();
    }
}
