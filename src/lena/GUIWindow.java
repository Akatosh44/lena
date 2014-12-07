/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.EventListener;


/**
 *
 * @author jlangloi
 */
public class GUIWindow extends JFrame implements EventListener{
    
    private JPanel content;
    private Image image;
    private JLabel imgName;
    private ImagePanel contenuImage;

    /**
     * Constructor. Allow us to display the GUI.
     * @param w
     * @param h 
     */
    public GUIWindow(int w, int h){
        super("VIEWER");
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        
        content = new JPanel();
        content.setSize(w, h);
        content.setLayout(null);
        this.setContentPane(content);
        contenuImage=new ImagePanel();
        imgName=new JLabel("");
        imgName.setBounds(0, 0, w, 15);
        image=null;
        content.add(contenuImage);
        content.add(imgName);
        
        JMenuBar menuBar=new JMenuBar();
        
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem openItem = new JMenuItem("Open (.pgm)",KeyEvent.VK_O);
        openItem.setToolTipText("Open a .pgm file");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        openItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser dialogue;
                dialogue = new JFileChooser(new File("."));
               
                if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    File fichier = dialogue.getSelectedFile();

                    if (fichier!=null){
                        setImage(PGMTools.readPGM(fichier.getAbsolutePath()));
                        contenuImage.setImage(image);
                        imgName.setText(image.getName());
                        repaint();
                    }
                    
                }
            }
        });
        
        JMenuItem saveItem = new JMenuItem("Save (.pgm)",KeyEvent.VK_S);
        saveItem.setToolTipText("Save the current image as a .pgm file");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        
        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setToolTipText("Exit application");
        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        JMenu menuActions = new JMenu("Actions");
        
        JMenuItem histogram = new JMenuItem("histogram");
        histogram.setToolTipText("Display the histogram of the picture");
        histogram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (image!=null){
                    JDialog popupHistogram = new JDialog();
                    popupHistogram.setTitle(image.getName()+" histogram");
                    
                    popupHistogram.setLayout(null);
                    ImagePanel histo = new ImagePanel();
                    histo.setImage(ImageTools.histogram(image));
                    popupHistogram.add(histo);
                    popupHistogram.setSize(histo.getWidth()+20,histo.getHeight()+60);
                    popupHistogram.setVisible(true);
                    /*setImage(ImageTools.histogram(image));
                    contenuImage.setImage(image);
                    repaint();*/
                }
            }
        });
        
        
        JMenuItem thresholdingItem = new JMenuItem("Thresholding");
        thresholdingItem.setToolTipText("Display a black and white version of the picture");
        thresholdingItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (image!=null){
                    int factor = showThresholdingPopup();
                    if (factor >= 0 && factor <=255){
                        setImage(ImageTools.seuillageImage(image,factor));
                        contenuImage.setImage(image);
                        imgName.setText(image.getName());
                        repaint();
                    }
                }
            }
        });
        
        
        JMenuItem enlargeItem = new JMenuItem("Enlarge");
        enlargeItem.setToolTipText("Display an enlargement of the picture");
        enlargeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (image!=null){
                    int factor = showEnlargementPopup();
                    if (factor != 0){
                        setImage(ImageTools.enlargeImage(image,factor));
                        contenuImage.setImage(image);
                        imgName.setText(image.getName());
                        repaint();
                    }
                }
            }
        });
        
        
        JMenuItem differenceItem = new JMenuItem("Difference with");
        differenceItem.setToolTipText("Difference between the actual picture and another");
        differenceItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (image!=null){
                    Image image2= new Image();
                    JFileChooser dialogue;
                    dialogue = new JFileChooser(new File("."));

                    if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                        File fichier = dialogue.getSelectedFile();
//TODO gerer exceptions taille
                        if (fichier!=null){
                            image2 = PGMTools.readPGM(fichier.getAbsolutePath());
                            if (image2.getHeight()==image.getHeight() && 
                                    image2.getWidth()==image.getWidth()){
                                setImage(ImageTools.differences(image,image2));
                                contenuImage.setImage(image);
                                imgName.setText(image.getName());
                                repaint();
                            }
                            else {
                                showSizeErrorMessage();
                            }
                            
                        }
                    }
                }
            }
        });
        
        
        JMenuItem previousItem = new JMenuItem("Previous");
        previousItem.setToolTipText("Display the last Image");
        previousItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                contenuImage.previousImage();
            }
        });
        
        JMenuItem nextItem = new JMenuItem("Next");
        nextItem.setToolTipText("Display the next Image");
        nextItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                contenuImage.nextImage();
            }
        });
        
        
        menu.add(openItem);
        menu.add(saveItem);
        menu.add(new JSeparator());
        menu.add(closeItem);
        
        menuActions.add(histogram);
        menuActions.add(thresholdingItem);
        menuActions.add(enlargeItem);
        menuActions.add(differenceItem);
        menuActions.add(new JSeparator());
        menuActions.add(previousItem);
        menuActions.add(nextItem);
        
        menuBar.add(menu);
        menuBar.add(menuActions);

        this.setJMenuBar(menuBar);

        this.setVisible(true);
        
    }
    
    /**
     * content getter
     * @return 
     */
    public JPanel getContent() {
        return content;
    }
    /**
     * content setter
     * @param content 
     */
    public void setContent(JPanel content) {
        this.content = content;
    }
    /**
     * Image getter
     * @return 
     */
    public Image getImage() {
        return image;
    }
    /**
     * Image setter
     * @param image 
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * Allow to show a popup asking the user the enlargement factor.
     * return 0 if there have been an error of type.
     * @return 
     */
    private int showEnlargementPopup(){
        int factor = 0;
        try{
            factor = Integer.parseInt(
            JOptionPane.showInputDialog(this, 
                    "Enlargement factor (int):",
                    "1"));
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,
                "The value is not correct.\nIt must be an int",
                "Incorrect factor value",
                JOptionPane.ERROR_MESSAGE);
        }
        finally{
            return factor;
        }
    
    }
    /**
     * Allow to show a popup asking the user the thresholdhe want to apply.
     * return -1 if there have been an error of type or value (0-255).
     * @return 
     */
    private int showThresholdingPopup(){
        int factor = 125;
        try{
            factor = Integer.parseInt(
            JOptionPane.showInputDialog(this, 
                    "Thresholding factor (int):",
                    "1"));
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,
                "The value is not correct.\nIt must be an int between 0 and 255",
                "Incorrect factor value",
                JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if (factor <0 || factor >255){
                factor = -1;
                JOptionPane.showMessageDialog(this,
                "The value is not correct.\nIt must be an int between 0 and 255",
                "Incorrect factor value",
                JOptionPane.ERROR_MESSAGE);
            }
            return factor;
        }
    
    }
    /**
     * Allow us to show an error message saying 
     * "The pictures don't got the same size"
     */
    private void showSizeErrorMessage(){
        JOptionPane.showMessageDialog(this,
            "The pictures don't got the same size",
            "Incorrect size",
            JOptionPane.ERROR_MESSAGE);
    }
    
}
