/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

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
        public void seuillagePixel (Pixel pixel, int seuil){
            
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
        public void seuillageImage (Image image, int seuil) {
            for (Pixel p : image.getPixels()) {
                seuillagePixel(p, seuil);
            }
        }
        
        
       
        
        
    
    
}
