/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author avinesse
 */
public class ImageToolsTest {

    public ImageToolsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of seuillagePixel method, of class ImageTools.
     */
    @Test
    public void testSeuillagePixel() {
        System.out.println("seuillagePixel");
        Pixel pixel1 = new Pixel(0, 0, 23);
        assertEquals(ImageTools.seuillagePixel(pixel1, 123).getLevel(), 0);
        Pixel pixel2 = new Pixel(2, 5, 23);
        assertEquals(ImageTools.seuillagePixel(pixel1, 20).getLevel(), 255);
        Pixel pixel3 = new Pixel(4, 5, 125);
        assertEquals(ImageTools.seuillagePixel(pixel1, 125).getLevel(), 0);
    }

    /**
     * Test of seuillageImage method, of class ImageTools.
     */
    @Test
    public void testSeuillageImage() {
        System.out.println("seuillageImage");
        ArrayList<Pixel> pixelsTest = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            pixelsTest.add(new Pixel(i, 0, i));
        }
        Image image = new Image("test", 20, 1, pixelsTest);
        

        ArrayList<Pixel> pixelsExpected = new ArrayList<>(20);
        for (int j = 0; j < 11; j++) {
            pixelsExpected.add(new Pixel(j, 0, 0));
        }
        for (int k = 11; k < 20; k++) {
            pixelsExpected.add(new Pixel(k, 0, 255));
        }
        assertEquals(pixelsExpected,ImageTools.seuillageImage(image,11).getPixels());


    }

    /**
     * Test of enlargeImage method, of class ImageTools.
     */
    @Test
    public void testEnlargeImage() {
        System.out.println("enlargeImage");
        ArrayList<Pixel> pixels = new ArrayList<>();
        for(int i=0; i < 10; i++){
            pixels.add(new Pixel (i,0,60));
        }
            
        Image image = new Image("test",10,1,pixels);
        assertEquals(2,ImageTools.enlargeImage(image, 2).getHeight());
        assertEquals(30,ImageTools.enlargeImage(image, 3).getWidth());
        
    }
    
    /**
     * Test of differences method, of class ImageTools.
     */
    @Test
    public void testDifferences() {
        System.out.println("differences");
        Image image1 = new Image("image1", 100, 1, null);
        Image image2 = new Image("image2", 100, 1, null);     
        
        ArrayList<Pixel> pixelsImg1 = new ArrayList<>(2);
        pixelsImg1.add(new Pixel(0,1,100));
        pixelsImg1.add(new Pixel(1,0,100));
        image1.setPixels(pixelsImg1);
        
        
        ArrayList<Pixel> pixelsImg2 = new ArrayList<>(2);
        pixelsImg2.add(new Pixel(0,1,50));
        pixelsImg2.add(new Pixel(1,0,50));
        image2.setPixels(pixelsImg2);
        
        ArrayList<Pixel> pixelsExpected1 = new ArrayList<>(2);
        pixelsExpected1.add(new Pixel(0,1,50));
        pixelsExpected1.add(new Pixel(1,0,50));
        
        ArrayList<Pixel> pixelsExpected2 = new ArrayList<>(2);
        pixelsExpected2.add(new Pixel(0,1,0));
        pixelsExpected2.add(new Pixel(1,0,0));
        assertEquals(pixelsExpected1,
                ImageTools.differences(image1, image2).getPixels() );
        assertEquals(pixelsExpected2,
                ImageTools.differences(image2, image1).getPixels() );
    }
    
    /**
     * Test of histogram method, of class ImageTools.
     */
    @Test
    public void testHistogram() {
        System.out.println("differences");
        System.out.println("differences");
        Image histoExpected = new Image("histoExpected", 100, 256, null);
        Image image = new Image("image", 10, 1, null);
        
        ArrayList<Pixel> pixelsExpected = new ArrayList<>(100*256);
        for(int i=0;i<100;i++){
            for(int j=0;j<256;j++){
                if(j==1){
                    if(i!=0){
                        pixelsExpected.add(new Pixel(j,i,0));
                    }
                    else{
                        pixelsExpected.add(new Pixel(j,i,255));
                    }
                    
                }
                else{
                    pixelsExpected.add(new Pixel(j,i,255));
                }
            }
        }
        
        histoExpected.setPixels(pixelsExpected);
        ArrayList<Pixel> pixels = new ArrayList<>(10);
        for(int i=0;i<10;i++){
            pixels.add(new Pixel(i,0,1));
        }
        image.setPixels(pixels);
        
        System.out.println(pixels.size() + " " + pixelsExpected.size());
        assertEquals(pixelsExpected,
                ImageTools.histogram(image).getPixels() );
        
        
    }
    
    
}