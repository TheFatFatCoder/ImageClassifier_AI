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
public class ImageClassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Data> trainSet = new ReadCSV("/home/john/Desktop/kClassifier/ImageClassifier/train_data.csv").getCSVList(); //60000
        ArrayList<Data> testSet = new ReadCSV("/home/john/Desktop/kClassifier/ImageClassifier/test_data.csv").getCSVList(); //10000
   
        int errorCount = 0;
        for (int j = 0; j < testSet.size(); j++) {
            Data result = CalculateEuclidean.calculate(testSet.get(j), trainSet);
            System.out.println("Result Set : "+result.getData()[784] + " || Actual Set: "+ testSet.get(j).getData()[784]);
            if (testSet.get(j).getData()[784]!=result.getData()[784]) {
               errorCount++; 
            }
        }
        System.out.println("Error Percentage /% : "+(errorCount/testSet.size())*100);
   
        //Data result = CalculateEuclidean.calculate(testSet.get(20), trainSet);
        //System.out.println("Result Set : "+testSet.get(20).getData()[784] + " || Actual Set: "+ trainSet.get(20).getData()[784]);
        
        //Data result = CalculateEuclidean.calculate(testSet.get(1), trainSet);
        //System.out.println("Result Set : "+testSet.get(1).getData()[784] + " || Actual Set: "+ trainSet.get(1).getData()[784]);
    }
    
}
