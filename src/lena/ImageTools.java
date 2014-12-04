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
     * @param pixel
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
     * @return image
     */
    public static Image seuillageImage(Image image, int seuil) {
        Image imageSeuil = new Image(image);
        for (Pixel p : imageSeuil.getPixels()) {
            p = seuillagePixel(p, seuil);
        }
        return imageSeuil;
    }

        
        /**
         * Enlargement of an image by a coefficient. Each pixel is duplicated coeff² times.
         * @param image
         * @param coeff 
         * @return
         */
    public static Image enlargeImage(Image image, int coeff) {
           ArrayList<Pixel> newPixelList = new ArrayList<>();
        Image newImage = new Image(image.getName() + "_large", coeff * image.getWidth(), coeff * image.getHeight(), newPixelList);
       
        for(int j=0;j<newImage.getHeight();j++){   
                for(int i=0;i<newImage.getWidth();i++){
                  newPixelList.add(new Pixel(i,j,image.getPixels().get((int)Math.floor((i/coeff))+(int)Math.floor(j/coeff)*image.getWidth()).getLevel()));
                }
            }
        
        return newImage;
    }

    /**
     * Computes the difference of grayLevel between two imgs
     *
     * @param img1
     * @param img2
     * @return
     */
    public static Image differences(Image img1, Image img2) {
        if (img1.getWidth() == img2.getWidth()
                && img1.getHeight() == img1.getHeight()) {

            // The images have the same width and height
            Image diff = new Image("DifferenceObtenue",
                    img1.getWidth(), img1.getHeight());
            ArrayList<Pixel> pixelsDiff = new ArrayList<>();
            ArrayList<Pixel> pixelsImg1 = img1.getPixels();
            ArrayList<Pixel> pixelsImg2 = img2.getPixels();

            for (int j = 0; j < pixelsImg1.size(); j++) {

                Pixel p = pixelsImg1.get(j);
                Pixel pix = pixelsImg2.get(j);
                // Computing the grayLevel
                int grayLevel = p.getLevel() - pix.getLevel();
                Pixel pixDiff = new Pixel(p.getX(), p.getY(), 0);
                if (grayLevel > 0) {
                    pixDiff.setLevel(grayLevel);
                }
                // Add the result pixel in the ArrayList
                pixelsDiff.add(pixDiff);
            }
            diff.setPixels(pixelsDiff);
            return diff;
        } else {
            // Images with differents height and/or widths
            return new Image("TailleDifferentes");
        }
    }

    /**
     * Creation de l'historamme de l'image envoyé
     * 
     * @param image
     * @return 
     */    
    public static Image histogram(Image image){
        
        int HEIGHT = 100;
        int WIDTH = 256;
        int[] levels = new int[256];
        int max = 0;
        ArrayList<Pixel> pixelList = new ArrayList<>();

        //Getting the number of pixels for each level
        for (Pixel px : image.getPixels()){
                //We add 1 to the level of the current pixel
                levels[px.getLevel()] ++;
        }

        //Getting the max
        for (int i=0; i<256; i++){

                if (levels[i]>max){max=levels[i];}
        }

        //putting in percent
        for (int i=0; i<256; i++){
                levels[i]=((int)((double)levels[i]*100.0/(double)max));
                System.out.println(levels[i]);
        }

        //computing
        for (int i=0; i<HEIGHT; i++){
                for (int j=0; j<WIDTH; j++){
                        if (levels[j] <= 100-i){
                                pixelList.add(new Pixel(j,i,255));
                        }
                        else {
                                pixelList.add(new Pixel(j,i,0));
                        }
                }
        }
        
        //Creating the histogram image
        Image histogram= new Image(image.getName()+"_histogram",
                                    WIDTH,HEIGHT,pixelList);
	return histogram;
    }
    

}
