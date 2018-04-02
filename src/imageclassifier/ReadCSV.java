/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageclassifier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author john
 */
public class ReadCSV{
        private String filePath;
        private final String csvSplit = ",";
        private BufferedReader br;
        private String line = "";
        private ArrayList<Data> dataList = new ArrayList<Data>();
        
        public ReadCSV(String path){
            try{
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {

                String[] data = line.split(csvSplit);
                //System.out.println(data[783]);
                dataList.add(new Data(data));
                }   
            }catch(FileNotFoundException fe){
                fe.printStackTrace();
            }catch(IOException ie){
                ie.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if (br != null) {
                   try{
                       br.close();
                   }catch(Exception e){
                       e.printStackTrace();
                   }
                }
            }
        }
        
        public ArrayList getCSVList(){
            return this.dataList;
        }
    }



