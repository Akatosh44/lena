/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.io.*;
import java.util.ArrayList;

/**
 * Reader and writer of PGM file
 * Both are static methods
 * @author Akatosh
 */

public class PGMTools {

    private static ArrayList<String> lineStack;

    /**
     * read a pgm file 
     * @param fileName
     * @return Image object
     */
    public static Image readPGM(String fileName){
        BufferedReader br=null;
        lineStack = new ArrayList<String>();
        try{
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line=br.readLine())!=null){
                //We stack the lines readed in an array
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
                    //We parse the file if everything went fine
                    return parseLines(fileName);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * Used to parse the lines stacked with the reader static method
     * PGM file must be (P5,#,width height, datas)
     * @param fileName
     * @return Image object
     */
    private static Image parseLines(String fileName){
        Image result = new Image();
        result.setWidth(Integer.parseInt(lineStack.get(2).split("\\s+")[0]));
        result.setHeight(Integer.parseInt(lineStack.get(2).split("\\s+")[1]));
        result.setName(fileName.substring(0, fileName.lastIndexOf('.')));
        
        for(int j=0;j<result.getHeight();j++){
            String[] split = lineStack.get(4+j).split("\\s+");
            for(int i=0;i<result.getWidth();i++){
                Pixel pixel = new Pixel(j,i,Integer.parseInt(split[i]));
                result.getPixels().add(pixel);
            }
        }
        return result;
    }
    /**
     * Used to write a pgm file from an Image object
     * @param fileName
     * @param image 
     */
    public static void writePGM(String fileName, Image image){
        BufferedWriter bw=null;
         try{
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("P2");
            bw.newLine();
            bw.write("#");
            bw.newLine();
            bw.write(image.getWidth()+" "+image.getHeight());
            bw.newLine();
            bw.write("255");
            bw.newLine();
            for(int j=0;j<image.getHeight();j++){   
                for(int i=0;i<image.getWidth();i++){
                  bw.write(image.getPixels().get(i+j*image.getWidth()).getLevel()+" ");
                }
                bw.newLine();
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
