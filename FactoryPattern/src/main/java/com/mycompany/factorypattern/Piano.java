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
public class Piano extends Instrument {
    
    //88 billentyűs
    private final int key=88;
    
    //nem leütöttként inicializáljuk a billentyűket
    public Piano(){
        this.keys = new ArrayList<>();
        for(int i=0; i<key; i++){
            keys.add(i, Boolean.FALSE);
        }
        this.message = new ArrayList<>();
    }
    
    //leütünk billentyűket
    @Override
    public void PlayNote(int... notes) {
        for(int note : notes){
            if(note<0 || note>key){
                System.out.println("Nincs ilyen billentyű: " + note);
                this.message.add("Nincs ilyen billentyű: " + (note+1));
            }
            else{
                keys.set(note, Boolean.TRUE);
                this.message.add("Billentyű beállítva: " + (note+1));
            }
        }
    }

    //megszólaltatjuk a leütött billentyűket
    @Override
    public void PlaySound() {
        int i=0;
        for(Boolean key : keys){
            if(key==true){
                System.out.println("A leütött billentyű a "+ (i+1) +". pozícióban van.");
                this.message.add("A leütött billentyű a "+ (i+1) +". pozícióban van.");
            }
            i++;
        }
        //kinullázzuk a billentyűket
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
