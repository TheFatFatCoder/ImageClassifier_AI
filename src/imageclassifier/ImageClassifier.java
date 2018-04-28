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
        //Un-remark line 21-22 if running on John's Linux
        //ArrayList<Data> trainSet = new ReadCSV("/home/john/Desktop/kClassifier/ImageClassifier/train_data.csv").getCSVList(); //60000
        //ArrayList<Data> testSet = new ReadCSV("/home/john/Desktop/kClassifier/ImageClassifier/test_data.csv").getCSVList(); //10000
        //Un-remark line 24-25 if running on John's Windows
        ArrayList<Data> trainSet = new ReadCSV("C:\\Users\\Steven\\Documents\\NetBeansProjects\\ImageClassifier_AI\\src\\imageclassifier\\train_data.csv").getCSVList(); //60000
        ArrayList<Data> testSet = new ReadCSV("C:\\Users\\Steven\\Documents\\NetBeansProjects\\ImageClassifier_AI\\src\\imageclassifier\\test_data.csv").getCSVList(); //10000
        
        int totalData = 0;
        int errorCounter = 0;
        int[][] confusionTable = new int[10][10];
        //Initialise Confusion Table
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                confusionTable[i][j] = 0;
            }
        }
        
        BayesClassifier bayes = new BayesClassifier(trainSet);
        for (int i = 0; i < testSet.size(); i++) {
            int set = bayes.calculateTestSet(testSet.get(i));
            int actual = (int) testSet.get(i).getData()[784];
            System.out.println(set+" || Actual Data: "+actual);
            
            if (set!=actual) {
                errorCounter++;
            }
            totalData++;
            
            confusionTable[set][actual]++;
        }
        System.out.println("Error Count "+errorCounter + " out of Total Data "+totalData);
        System.out.println("Confusion Table");
        System.out.println("=========================================================");
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i+"\t");
            for (int j = 0; j < 10; j++) {
                System.out.print(confusionTable[i][j]+"\t");
            }
            System.out.print("\n");
        }
        System.out.println("=========================================================");
        new DataClassifier(testSet).printData();
    }
    
    private void runEuclidean(ArrayList<Data> testSet, ArrayList<Data> trainSet){
        int errorCount = 0;
        for (int j = 0; j < testSet.size(); j++) {
            Data result = CalculateEuclidean.calculate(testSet.get(j), trainSet);
            System.out.println("Result Set : "+result.getData()[784] + " || Actual Set: "+ testSet.get(j).getData()[784]);
            if (testSet.get(j).getData()[784]!=result.getData()[784]) {
               errorCount++; 
            }
        }
        System.out.println("Error Percentage /% : "+(errorCount/testSet.size())*100);
        System.out.println("Total Error : "+errorCount);
    }
    
}
