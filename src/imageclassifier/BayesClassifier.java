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
public class BayesClassifier extends DataClassifier{
    private final double pi = 22/7;
    //Prior Probability Handling
    double[] prior = new double[10];
    int counter = 0;
    
    public BayesClassifier(ArrayList<Data> trainSet){
        super(trainSet);
        
        for (int i = 0; i < population.size(); i++) {
            prior[i] = (double) population.get(i).size()/60000;
            System.out.println("Prior of "+i+" is "+prior[i]);
        }
        
    }
    
    public int calculateTestSet(Data testData){
        System.out.println("Bayes Calculation Starts:\n=========================================");
        double[] posteriorProbability = new double[10];
        double posterior = 0;
        double[] testValues = testData.getData();
        for (int i = 0; i < 10; i++) {
            posterior = 0;
            double[] likelihood = new double[784];
            for (int j = 0; j <784; j++) {
                if (variance[i][j]==0 || Double.isNaN(variance[i][j])) {
                    likelihood[j] = 0;
                }else{
                    //likelihood[j] = (double) (-(Math.pow((testValues[j]-average[i][j]), 2))/(2*variance[i][j]))-(0.5*Math.log(2*pi))-(0.5*Math.log(variance[i][j]));
                    likelihood[j] = (double) (-(testValues[j]-average[i][j])*(testValues[j]-average[i][j]))/(2*variance[i][j])-0.5*Math.log(variance[i][j]);
                }
                
                for (int k = 0; k < 784; k++) {                    
                    posterior += likelihood[k];
                }
                
            }
            posteriorProbability[i] = posterior+Math.log(prior[i]);
        }
        //Find max posterior
        int index = 0;
        double max = 0;
        //System.out.println("Posterior Probability Size "+posteriorProbability.size());
        for (int i = 0; i < 10; i++) {
            if (i==0) {
                index = 0;
                max = posteriorProbability[i];
            }else if (posteriorProbability[i]>max) {
                index = i;
                max = posteriorProbability[i];
            }
        }
        
        
        return index;
    }
}
