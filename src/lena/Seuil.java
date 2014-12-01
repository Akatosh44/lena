/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lena;

import java.util.ArrayList;

/**
 *
 * @author avinesse
 */
public class Seuil {
    
    public ArrayList<> seuillage (ArrayList<> listePixels, int seuil) {
    ArrayList<int> listeSeuillee = new ArrayList<>();
      
      for (int i=0; i<listePixels.size(); i++) {
          if (listePixels.get(i)<123) {
              listeSeuillee.set(i,0);
                                      }
      
      return listeSeuillee;
      
    
           }

    
     }
    
}