/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;


/**
 *
 * @author jlangloi
 */
public class Lena {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Image test = PGMTools.readPGM("lena.pgm");
        PGMTools.writePGM("lena2.pgm", test);
        Image grandTest = ImageTools.enlargeImage(test, 2);
        PGMTools.writePGM("lena_large.pgm", grandTest);
      
      
    }

}
          
          
     
