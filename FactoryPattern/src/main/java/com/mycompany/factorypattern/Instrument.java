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
public abstract class Instrument implements Playable {
        protected ArrayList<Boolean> keys=null;
        protected ArrayList<String> message=null;
}
