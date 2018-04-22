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
public class BayesClassifier {
    private final int DATA_LENGTH = 784;
    private final double pi = 22/7;
    private final double e = 2.718281828;
    //Average Handling
    double[] zeroAverage;
    double[] oneAverage;
    double[] twoAverage;
    double[] threeAverage;
    double[] fourAverage;
    double[] fiveAverage;
    double[] sixAverage;
    double[] sevenAverage;
    double[] eightAverage;
    double[] nineAverage;
    //STDEV Handling
    double[] zeroSTDEV;
    double[] oneSTDEV;
    double[] twoSTDEV;
    double[] threeSTDEV;
    double[] fourSTDEV;
    double[] fiveSTDEV;
    double[] sixSTDEV;
    double[] sevenSTDEV;
    double[] eightSTDEV;
    double[] nineSTDEV;
    int totalCounter = 0;
    int[] classCounter = new int[10];
    double[] priorProbability = new double[10];
    
    public BayesClassifier(ArrayList<Data> testData, ArrayList<Data> trainData){
        ArrayList<ArrayList<Data>> population = new DataClassifier(trainData).getData(); //population is classified trainData
        for (int h = 0; h < 10; h++) {
            classCounter[h] = 0;
            double[] sum = new double[DATA_LENGTH];
            for (int i = 0; i < DATA_LENGTH; i++) {
                sum[i] = 0;
            }
            
            for (int i = 0; i < population.get(h).size(); i++) {
                for (int j = 0; j < DATA_LENGTH; j++) {
                    sum[j] += population.get(h).get(i).getData()[j];
                }
                classCounter[h]++;
                totalCounter++;
            }
            
            double[] tempAvg = new double[DATA_LENGTH];
            double[] sumDiffFromMean = new double[DATA_LENGTH];
            for (int i = 0; i < DATA_LENGTH; i++) {
                sumDiffFromMean[i] = 0;
                tempAvg[i] = sum[i]/population.get(h).size();
            }
            System.out.println("Average Label "+h+" Calculated");
            for (int i = 0; i < population.get(h).size(); i++) {
                for (int j = 0; j < DATA_LENGTH; j++) {
                    sumDiffFromMean[j] += (population.get(h).get(i).getData()[j] - tempAvg[j]);
                }
            }
            
            double[] tempSTDEV = new double[DATA_LENGTH];
            for (int i = 0; i < DATA_LENGTH; i++) {
                tempSTDEV[i] = Math.sqrt(Math.pow(sumDiffFromMean[i],2)/(population.get(h).size()-1));
            }
            System.out.println("STDEV Label "+h+" Calculated");
            switch  (h){
                case 0: zeroAverage = tempAvg;
                        zeroSTDEV = tempSTDEV;
                        break;
                case 1: oneAverage = tempAvg;
                        oneSTDEV = tempSTDEV;
                        break;
                case 2: twoAverage = tempAvg;
                        twoSTDEV = tempSTDEV;
                        break;
                case 3: threeAverage = tempAvg;
                        threeSTDEV = tempSTDEV;
                        break;
                case 4: fourAverage = tempAvg;
                        fourSTDEV = tempSTDEV;
                        break;
                case 5: fiveAverage = tempAvg;
                        fiveSTDEV = tempSTDEV;
                        break;
                case 6: sixAverage = tempAvg;
                        sixSTDEV = tempSTDEV;
                        break;
                case 7: sevenAverage = tempAvg;
                        sevenSTDEV = tempSTDEV;
                        break;
                case 8: eightAverage = tempAvg;
                        eightSTDEV = tempSTDEV;
                        break;
                case 9: nineAverage = tempAvg;
                        nineSTDEV = tempSTDEV;
                        break;
                default: break;
            }
        }
        
        System.out.println("Total Data : "+totalCounter);
        for (int i = 0; i < 10; i++) {
            System.out.println("Counter Data For Label "+i+" is "+classCounter[i]);
            priorProbability[i] = classCounter[i]/totalCounter;
        }
        
        System.out.println("================================================================");
        System.out.println("Bayes Calculation Starts:\n");
        for (int i = 0; i < testData.size(); i++) { //test_data can only use 783 (DATA_LENGTH-1)
            
        }
    }
}
