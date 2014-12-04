package lena;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JPanel;


public class ImagePanel extends JPanel{

    private BufferedImage image;
    private final LinkedList<Image> list;
    private int index;
    private Image img;

    public ImagePanel() {
        image = null;
        list = new LinkedList<>();
        index = 0;
    }
    
    /**
     * Allow us to add an image to the panel and 
     * in the list (for next and previous)
     * @param imgSource 
     */
    public void setImage(Image imgSource){
        
        /*If the index is in the last position of the list when we want to add
        an image, we check that the list isn't longer than 10 elements */
        if (index==list.size()){
            while (list.size() > 10){
                list.remove(0);
                index--;
            }
        }
        else{
            /*If the index is not at the last position of the list when we want 
            to add a picture, we delete the pictures that follow in the list */
            while (list.size()>index){
                list.remove(list.size()-1);
            }
        }
        
        list.add(imgSource);
        index++;
        changeImage(imgSource);
    }
    
    /**
     * Allow us to change the image in the panel
     * @param imgSource
     */
    private void changeImage(Image imgSource){
        
        this.img=imgSource;
        this.setBounds(0,15,img.getWidth(),  img.getHeight());
        image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int j=0; j<img.getHeight(); j++){
            for(int i=0; i<img.getWidth(); i++){
                image.setRGB(
                        i, j, 
                        ((img.getPixels().get(i+img.getWidth()*j).getLevel() << 16) 
                        | (img.getPixels().get(i+img.getWidth()*j).getLevel() <<8) 
                        | img.getPixels().get(i+img.getWidth()*j).getLevel()));
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        
    }
    
    /**
     * Allow us to change the image to the previous in the list
     */
    public void previousImage(){
        if (index > 1 && list.size()>1){
            index --;
            changeImage(list.get(index-1));
        }
    }
    
    /**
     * Allow us to change the image to the next in the list
     */
    public void nextImage(){
        if (index != list.size() && list.size()>0){
            index ++;
            changeImage(list.get(index-1));
        }
    }

}