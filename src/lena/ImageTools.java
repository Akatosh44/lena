/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.util.ArrayList;

/**
 *
 * @author avinesse
 */
public class ImageTools {
    
    
    /**
         * Set a new level at 0 or 255 for a pixel
         * @param seuil
         * @return 
         */
        public static void seuillagePixel (Pixel pixel, int seuil){
            
            if (pixel.getLevel() < seuil){
                pixel.setLevel(0);
            }
            else pixel.setLevel(255);
            
        }
        
        
        /**
         * Set a new level at 0 or 255 for each pixel in the image
         * @param image
         * @param seuil 
         */
        public static void seuillageImage (Image image, int seuil) {
            for (Pixel p : image.getPixels()) {
                seuillagePixel(p, seuil);
            }
        }
        
        
        /**
         * Enlargement of an image by a coefficient. Each pixel is duplicated coeff² times.
         * @param image
         * @param coeff 
         */
       public static void enlargeImage (Image image, int coeff) {
           image.setHeight(coeff*image.getHeight());
           image.setWidth(coeff*image.getWidth());
           ArrayList<Pixel> newPixelList = new ArrayList<>();
           for (Pixel p : image.getPixels()) {
               p.setX(coeff*p.getX());
               p.setY(coeff*p.getY());
               for (int i=0; i<coeff; i++) {
                   for (int j=0; j<coeff; j++) {
                   Pixel newP = new Pixel(p.getX()+i , p.getY()+j, p.getLevel());
                   newPixelList.add(newP);
                   }
               }
           }
           
           image.setPixels(newPixelList);
       }
        
        
    
    
}
