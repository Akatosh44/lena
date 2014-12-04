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
     *
     * @param seuil
     * @return
     */
    public static Pixel seuillagePixel(Pixel pixel, int seuil) {
        Pixel pixelSeuil = new Pixel(pixel);
        if (pixel.getLevel() < seuil) {
            pixelSeuil.setLevel(0);
        } else {
            pixelSeuil.setLevel(255);
        }
        return pixelSeuil;

    }

    /**
     * Set a new level at 0 or 255 for each pixel in the image
     *
     * @param image
     * @param seuil
     */
    public static Image seuillageImage(Image image, int seuil) {
        Image imageSeuil = new Image(image);
        for (Pixel p : imageSeuil.getPixels()) {
            p = seuillagePixel(p, seuil);
        }
        return imageSeuil;
    }

    /**
     * Enlargement of an image by a coefficient. Each pixel is duplicated coeff²
     * times.
     *
     * @param image
     * @param coeff
     */
    public static Image enlargeImage(Image image, int coeff) {
        ArrayList<Pixel> newPixelList = new ArrayList<>();
        Image newImage = new Image(image.getName() + "_large", coeff * image.getHeight(), coeff * image.getWidth(), newPixelList);
        for (Pixel p : image.getPixels()) {
            Pixel newP = new Pixel(coeff * p.getX(), coeff * p.getY(), p.getLevel());
            for (int i = 0; i < coeff; i++) {
                for (int j = 0; j < coeff; j++) {
                    Pixel newNeighbor = newImage.getPixel(newP.getX() + i, newP.getY() + j);
                    newNeighbor.setLevel(newP.getLevel());
                    newPixelList.add(newNeighbor);
                }
            }
        }
        
        
        /**
         * Enlargement of an image by a coefficient. Each pixel is duplicated coeff² times.
         * @param image
         * @param coeff 
         */
       public static Image enlargeImage (Image image, int coeff) {
           ArrayList<Pixel> newPixelList = new ArrayList<>();
           Image newImage = new Image(image.getName()+"_large",coeff*image.getHeight(),coeff*image.getWidth(),newPixelList);
           for (Pixel p : image.getPixels()) {
               Pixel newP = new Pixel (coeff*p.getX(),coeff*p.getY(),p.getLevel());
               for (int i=0; i<coeff; i++) {
                   for (int j=0; j<coeff; j++) {
                   Pixel newNeighbor = newImage.getPixel(newP.getX()+i,newP.getY()+j);
                   newNeighbor.setLevel(newP.getLevel());
                   newPixelList.add(newNeighbor);
                   }
               }
           }
           return newImage;
       }
       
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
