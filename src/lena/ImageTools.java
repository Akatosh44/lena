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
        return newImage;
    }
}
