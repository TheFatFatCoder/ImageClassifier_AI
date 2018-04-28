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
public class BayesClassifier {
    private final double pi = 22/7;
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
    //Prior Probability Handling
    double[] prior = new double[10];
    int counter = 0;
    
    public BayesClassifier(ArrayList<Data> trainSet){
        ArrayList<ArrayList<Data>> population = new DataClassifier(trainSet).getData();
        
        //Calculate Average
        for (int i = 0; i < population.size(); i++) {
            //Calculate Average for i category (i is max of 10)
            double[] sum = new double[784];
            double[] average = new double[784];
            //Initialise sum[]
            for (int j = 0; j < 784; j++) {
                sum[j] = 0;
            }
            for (int j = 0; j < population.get(i).size(); j++) { //get data inside each category
                for (int k = 0; k < 784; k++) {
                    sum[k] += (double) population.get(i).get(j).getData()[k];
                }
                counter++;
                for (int k = 0; k < 784; k++) {
                    average[k] = (double) sum[k]/population.get(i).size();
                }
            }
            
            switch (i){
                case 0 : this.zeroAverage = average;
                        break;
                case 1 : this.oneAverage = average;
                        break;
                case 2 : this.twoAverage = average;
                        break;
                case 3 : this.threeAverage = average;
                        break;
                case 4 : this.fourAverage = average;
                        break;
                case 5 : this.fiveAverage = average;
                        break;
                case 6 : this.sixAverage = average;
                        break;
                case 7 : this.sevenAverage = average;
                        break;
                case 8 : this.eightAverage = average;
                        break;
                case 9 : this.nineAverage = average;
                        break;
                default: break;
            }
            
            double[] stdev = new double[784];
            double[] sumDiffFromMean = new double[784];
            //Initial sumDiffFromMean
            for (int j = 0; j < 784; j++) {
                sumDiffFromMean[j] = 0;
            }
            for (int j = 0; j < population.get(i).size(); j++) {
                for (int k = 0; k < 784; k++) {
                    sumDiffFromMean[k] += Math.pow((population.get(i).get(j).getData()[k]-average[k]),2);
                }
                for (int k = 0; k < 784; k++) {
                    stdev[k] = Math.sqrt(sumDiffFromMean[k]/((population.get(i).size()-1)));
                }
            }
            switch (i){
                case 0 : this.zeroSTDEV = stdev;
                        break;
                case 1 : this.oneSTDEV = stdev;
                        break;
                case 2 : this.twoSTDEV = stdev;
                        break;
                case 3 : this.threeSTDEV = stdev;
                        break;
                case 4 : this.fourSTDEV = stdev;
                        break;
                case 5 : this.fiveSTDEV = stdev;
                        break;
                case 6 : this.sixSTDEV = stdev;
                        break;
                case 7 : this.sevenSTDEV = stdev;
                        break;
                case 8 : this.eightSTDEV = stdev;
                        break;
                case 9 : this.nineSTDEV = stdev;
                        break;
                default: break;
            }
        }
        //Calculate prior probability
        //double sum = 0;
        for (int i = 0; i < population.size(); i++) {
            prior[i] = (double) population.get(i).size()/counter;
            System.out.println("Prior of "+i+" is "+prior[i]);
            //sum+= prior[i];
        }
        //System.out.println("Total : "+sum);
        //for (int i = 0; i < 784; i++) {
            //System.out.println(zeroSTDEV[i]+" ");
            //System.out.println(zeroAverage[i]+" ");
        //}
        System.out.println();
        
    }
    
    public int calculateTestSet(Data testData){
        System.out.println("Bayes Calculation Starts:\n=========================================");
        double[] testValues = testData.getData();
        
        ArrayList<Double> posteriorProbability = new ArrayList<Double>();
        double posterior = 0;
        for (int i = 0; i < 10; i++) {
            posterior = 0;
            double[] likelihood = new double[784];
            for (int j = 0; j <784; j++) {
                //System.out.println(testValues.length);
                switch(i){
                    case 0 :if (zeroSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-zeroAverage[j]), 2))/(2*zeroSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(zeroSTDEV[j], 2)));
                            }
                            break;
                    case 1 :if (oneSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-oneAverage[j]), 2))/(2*oneSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(oneSTDEV[j], 2)));
                            }
                            break;
                    case 2 :if (twoSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-twoAverage[j]), 2))/(2*twoSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(twoSTDEV[j], 2)));
                            }
                            break;
                    case 3 : if (threeSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-threeAverage[j]), 2))/(2*threeSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(threeSTDEV[j], 2)));
                            }
                            break;
                    case 4 :if (fourSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-fourAverage[j]), 2))/(2*fourSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(fourSTDEV[j], 2)));
                            }
                            break;
                    case 5 :if (fiveSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-fiveAverage[j]), 2))/(2*fiveSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(fiveSTDEV[j], 2)));
                            }
                            break;
                    case 6 :if (sixSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-sixAverage[j]), 2))/(2*sixSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(sixSTDEV[j], 2)));
                            }
                            break;      
                    case 7 :if (sevenSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-sevenAverage[j]), 2))/(2*sevenSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(sevenSTDEV[j], 2)));
                            }
                            break;
                    case 8 :if (eightSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-eightAverage[j]), 2))/(2*eightSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(eightSTDEV[j], 2)));
                            }
                            break;
                    case 9: if (nineSTDEV[j]==0) {
                                likelihood[j] = 0;
                            }else{
                                likelihood[j] = (double) -(Math.pow((testValues[j]-nineAverage[j]), 2))/(2*nineSTDEV[j])-(0.5*Math.log(2*pi))-(0.5*Math.log(Math.pow(nineSTDEV[j], 2)));
                            }
                            break;
                    default: break;
                }
                for (int k = 0; k < 784; k++) {                    
                    posterior += likelihood[k] + Math.log(prior[i]);
                    //System.out.println("Likelihood "+likelihood[j] );
                }
                
            }   
            posteriorProbability.add(posterior);
        }
        //Find max posterior
        int index = 0;
        double max = 0;
        //System.out.println("Posterior Probability Size "+posteriorProbability.size());
        for (int i = 0; i < posteriorProbability.size(); i++) {
            if (i==0) {
                index = 0;
                max = posteriorProbability.get(i);
            }else if (posteriorProbability.get(i)>max) {
                index = i;
                max = posteriorProbability.get(i);
            }
        }
        return index;
    }
}
