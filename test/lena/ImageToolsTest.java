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
        ArrayList<Pixel> pixelsTest = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            pixelsTest.add(new Pixel(i, 0, i));
        }
        Image image = new Image("test", 100, 1, pixelsTest);

        ArrayList<Pixel> pixelsExpected = new ArrayList<>(100);
        for (int j = 0; j < 51; j++) {
            pixelsExpected.add(new Pixel(j, 0, 0));
        }
        for (int k = 51; k < 100; k++) {
            pixelsExpected.add(new Pixel(k, 0, 255));
        }
        Image imageExpected = new Image("test", 100, 1, pixelsExpected);

        assertEquals(imageExpected, ImageTools.seuillageImage(image, 50));


    }

    /**
     * Test of enlargeImage method, of class ImageTools.
     */
    @Test
    public void testEnlargeImage() {
        System.out.println("enlargeImage");
        Image image = null;
        int coeff = 0;
        Image expResult = null;
        Image result = ImageTools.enlargeImage(image, coeff);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}