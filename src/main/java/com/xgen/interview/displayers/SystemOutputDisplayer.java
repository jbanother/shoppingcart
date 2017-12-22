package com.xgen.interview.displayers;

/**
 * Displays data to system output
 */
public class SystemOutputDisplayer implements IDisplayer {

    public void Display(String data){
        System.out.println(data);
    }

}
