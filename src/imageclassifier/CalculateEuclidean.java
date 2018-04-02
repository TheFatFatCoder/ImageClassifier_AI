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
public class CalculateEuclidean {
    public static Data calculate(Data compareData, ArrayList<Data> wholeSet){
        int index = 0;
        double minimum = 0;
        double[] testData = compareData.getData();
        for (int i = 0; i < wholeSet.size(); i++) {
            double[] compareWith = wholeSet.get(i).getData();
            double rawSub = 0;
            for (int j = 0; j < 784; j++) {
                rawSub += (Math.pow((compareWith[j]-testData[j]),2));
            }
            double distance = Math.sqrt(rawSub); 
            if (i==0) {
                minimum = distance;
                index = i;
            }else if (distance<=minimum) {
                   minimum = distance;
                   index = i;
            }
        }
        //System.out.println("Index - "+index);
        return wholeSet.get(index);
    }
    
}
