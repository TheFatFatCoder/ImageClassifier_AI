/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageclassifier;

import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class DataClassifier {
    private ArrayList<ArrayList<Data>> population = new ArrayList<>();
    private ArrayList<Data> one = new ArrayList<Data>();
    private ArrayList<Data> two = new ArrayList<Data>();
    private ArrayList<Data> three = new ArrayList<Data>();
    private ArrayList<Data> four = new ArrayList<Data>();
    private ArrayList<Data> five = new ArrayList<Data>();
    private ArrayList<Data> six = new ArrayList<Data>();
    private ArrayList<Data> seven = new ArrayList<Data>();
    private ArrayList<Data> eight = new ArrayList<Data>();
    private ArrayList<Data> nine = new ArrayList<Data>();
    private ArrayList<Data> zero = new ArrayList<Data>();
    private double[] mean = new double[10];
    
    public DataClassifier(ArrayList<Data> data){
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getData()[784] == 0) {
                zero.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 1) {
                one.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 2) {
                two.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 3) {
                three.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 4) {
                four.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 5) {
                five.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 6) {
                six.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 7) {
                seven.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 8) {
                eight.add(data.get(i));
            }
            
            else if (data.get(i).getData()[784] == 9) {
                nine.add(data.get(i));
            }
        }
        population.add(zero);
        population.add(one);
        population.add(two);
        population.add(three);
        population.add(four);
        population.add(five);
        population.add(six);
        population.add(seven);
        population.add(eight);
        population.add(nine);
    }
    
    public ArrayList getData(){
        return this.population;
    }
}
