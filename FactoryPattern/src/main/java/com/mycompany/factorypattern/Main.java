package com.mycompany.factorypattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bence
 */
public class Main {
    public static void main(String[] args){
        try{
            Playable piano=PlayableFactory.getInstrument("Piano");
            Playable guitar=PlayableFactory.getInstrument("Guitar");
            piano.PlaySound();
            guitar.PlaySound();
            piano.PlayNote(23, 47);                     //48, 24
            guitar.PlayNote(3, 2);                      //3, 4
            piano.PlaySound();
            guitar.PlaySound();
            //akkordok lejátszása
            piano.PlayNote(32, 54, 28, 24, 60, 62);     //33, 55, 29, 25, 61, 63
            guitar.PlayNote(5, 4, 8, 3, 7, 1);          //5, 6, 4, 9, 2, 8
            piano.PlaySound();
            guitar.PlaySound();
            //out of bounds inputok
            piano.PlayNote(90);                         //nincs ilyen
            guitar.PlayNote(10);                        //túl kevés
            guitar.PlayNote(15, 5, 14, 5, 7);           //nincs ilyen, 6, 15, túl kevés
            guitar.PlayNote(14, 6);                     //nincs ilyen
            piano.PlaySound();
            guitar.PlaySound();
            guitar.PlayNote(0, 0);                      //1, 1
            guitar.PlaySound();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }
}
