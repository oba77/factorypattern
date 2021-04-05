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
//Playable gyár osztály
public class PlayableFactory {
    public static Playable getInstrument(String type) throws Exception{
        switch(type)
        {
            case "Piano":
                return new Piano();
            case "Guitar":
                return new Guitar();
            default:
                throw new Exception();
        }
    }
}
