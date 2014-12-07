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

    
    public GUIWindow(int w, int h){
        super("VIEWER");
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        
        content = new JPanel();
        content.setSize(w, h);
        content.setLayout(null);
        //content.setBackground(Color.red);
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
        
        menuActions.add(histogram);
        menuActions.add(enlargeItem);
        menuActions.add(new JSeparator());
        menuActions.add(previousItem);
        menuActions.add(nextItem);
        
        menu.add(openItem);
        menu.add(saveItem);
        menu.add(new JSeparator());
        menu.add(closeItem);
        
        menuBar.add(menu);
        menuBar.add(menuActions);

        this.setJMenuBar(menuBar);

        this.setVisible(true);
        
    }
    
  
    public JPanel getContent() {
        return content;
    }

    public void setContent(JPanel content) {
        this.content = content;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

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

    
}
