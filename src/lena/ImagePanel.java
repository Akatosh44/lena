package lena;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ImagePanel extends JPanel{

    private BufferedImage image;
    private Image img;

    public ImagePanel() {
        image = null;
    }
    
    public void setImage(Image imgSource){
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

}