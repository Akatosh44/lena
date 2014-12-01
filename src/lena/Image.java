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
public class Image {
    
    private String name;
    private int width;
    private int height;
    private ArrayList<Pixel> Pixels;

    public Image() {
        this.name="Default";
        this.width = 10;
        this.height = 10;
        Pixels = new ArrayList<Pixel>();
    }
    
    public Image(String name) {
        this.name= name;
        this.width = 10;
        this.height = 10;
        Pixels = new ArrayList<Pixel>();
    }
    
    public Image(String name, int width, int height, ArrayList<Pixel> pix) {
        this.name= name;
        this.width = width;
        this.height = height;
        Pixels = pix;
    }
        
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Pixel> getPixels() {
        return Pixels;
    }

    public void setPixels(ArrayList<Pixel> Pixels) {
        this.Pixels = Pixels;
    }
    
    
}
