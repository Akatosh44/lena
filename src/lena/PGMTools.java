/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.io.*;
import java.util.ArrayList;

/**
 * @author jlangloi
 */
public class PGMTools {
    private static ArrayList<String> lineStack;
    
    public static Image readPGM(String fileName){
        BufferedReader br=null;
        
        try{
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line=br.readLine())!=null){
                lineStack.add(line);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(br!=null){
                try{
                    br.close();
                    return parseLines(fileName);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private static Image parseLines(String fileName){
        Image result = new Image();
        result.setWidth(Integer.parseInt(lineStack.get(2).split(" ")[0]));
        result.setHeight(Integer.parseInt(lineStack.get(2).split(" ")[1]));
        result.setName(fileName);
        for(int j=0;j<result.getHeight();j++){
            for(int i=0;i<result.getWidth();i++){
                Pixel pixel = new Pixel(j,i,Integer.parseInt(lineStack.get(3).split(" ")[i]));
                result.getPixels().add(pixel);
            }
        }
        return result;
    }
    public static void writePGM(String fileName, Image image){
        BufferedWriter bw=null;
         try{
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("P5");
            bw.write("#");
            bw.write(image.getWidth()+" "+image.getHeight());
            bw.write(255);
            String line;
            for(int j=0;j<image.getHeight();j++){   
                line="";
                for(int i=0;i<image.getWidth();i++){
                   line+=image.getPixels().get(i+j*image.getWidth())+" ";
                }
                bw.write(line);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(bw!=null){
                try{
                    bw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
