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
public interface Playable {
    public abstract void PlayNote(int... notes);
    public abstract void PlaySound();
    public abstract ArrayList<String> GetMsg ();
    public abstract void ClearMsg();
}
