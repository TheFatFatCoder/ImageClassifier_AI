/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageclassifier;

import java.util.ArrayList;

/**
 *
 * @author john
 */
public class Data {
    private double[] data;
    
    public Data(String[] input){
        this.data = new double[785];
        for (int i = 0; i <= 784; i++) {
            this.data[i] = Double.parseDouble(input[i]);
        }
    }
    
    public double[] getData(){
        return this.data;
    }
}
