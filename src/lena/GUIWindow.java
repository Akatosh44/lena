/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *
 * @author jlangloi
 */
public class GUIWindow extends JFrame{
    
    private JPanel content;
    
    public GUIWindow(int w, int h){
        this.setSize(w, h);
        content = new JPanel();
        content.setSize(w, h);
        content.setLayout(new BorderLayout());
        JMenuBar menuBar=new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openItem = new JMenuItem("Open (.pgm)",KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        JMenuItem saveItem = new JMenuItem("Save (.pgm)",KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menu.add(openItem);
        menu.add(saveItem);
        menuBar.add(menu);
        content.add(menuBar, BorderLayout.PAGE_START);
        this.setTitle("LENA Viewer");
        this.setContentPane(content);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //TODO
    
}
