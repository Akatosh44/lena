/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.util.ArrayList;

/**
 *
 * @author ebriand
 */
public class Difference {
    /**
     * Computes the difference of grayLevel between two imgs
     * @param img1
     * @param img2
     * @return 
     */
    public static Image differences(Image img1,Image img2){
        if(img1.getWidth() == img2.getWidth() 
                && img1.getHeight() == img1.getHeight()){
            
            // The images have the same width and height
            Image diff = new Image("DifferenceObtenue",
                    img1.getWidth(),img1.getHeight());
            ArrayList<Pixel> pixelsDiff = new ArrayList<>();
            ArrayList<Pixel> pixelsImg1 = img1.getPixels();
            ArrayList<Pixel> pixelsImg2 = img2.getPixels();
            
            for(int j =0; j<pixelsImg1.size();j++){
                
                Pixel p = pixelsImg1.get(j);
                Pixel pix = pixelsImg2.get(j);
                // Computing the grayLevel
                int grayLevel = p.getLevel() - pix.getLevel();
                Pixel pixDiff = new Pixel(p.getX(),p.getY(),0);
                if(grayLevel>0){
                    pixDiff.setLevel(grayLevel);
                }
                // Add the result pixel in the ArrayList
                pixelsDiff.add(pixDiff);               
            }
            diff.setPixels(pixelsDiff);
            return diff;    
        }
        else {
            // Images with differents height and/or widths
            return new Image("TailleDifferentes");
        }
    }
}
