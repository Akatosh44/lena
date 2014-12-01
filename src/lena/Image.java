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

    /**
     * Empty Constructor Image
     */
    public Image() {
        this.name="Default";
        this.width = 10;
        this.height = 10;
        Pixels = new ArrayList<Pixel>();
    }
    /**
     * Constructor with name
     * @param name 
     */
    public Image(String name) {
        this();
        this.name= name;
        
    }
    
    /**
     * Copy constructor
     * @param img 
     */
    public Image(Image img){
        this.name = img.getName();
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.Pixels = img.getPixels();
    }
    
    /**
     * Constructor with Paramaters name width and height
     * @param name
     * @param width
     * @param height 
     */
    public Image(String name, int width, int height){
        this();
        this.width = width;
        this.height = height;
    }
    
    /**
     * Ful Constructor
     * @param name
     * @param width
     * @param height
     * @param pix 
     */
    public Image(String name, int width, int height, ArrayList<Pixel> pix) {
        this.name= name;
        this.width = width;
        this.height = height;
        Pixels = pix;
    }
        
    /**
     * Getter Name
     * @return 
     */   
    public String getName() {
        return name;
    }
    /**
     * Setter Name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter Width
     * @return 
     */
    public int getWidth() {
        return width;
    }
    /**
     * Setter Width
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Getter Height
     * @return 
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Setter Height
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Getter Pixels
     * @return 
     */
    public ArrayList<Pixel> getPixels() {
        return Pixels;
    }
    
    /**
     * Setter Pixels
     * @param Pixels 
     */
    public void setPixels(ArrayList<Pixel> Pixels) {
        this.Pixels = Pixels;
    }
    /**
     * toString
     * @return 
     */
    @Override
    public String toString(){
        String s = "Name: " + this.name +"Width " + this.width + "Height: ";
        s = s + this.height + "Pixels number: " +  Pixels.size();
        return s;
    }
    
    
}
