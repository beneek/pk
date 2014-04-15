/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class Dane {
    int [] dane;
    
    void inicjalizujLosowo(int rozmiar, int zakres){
        dane = new int[rozmiar];
        for(int i = 0 ; i < dane.length; i++){
             dane[i] = (int)((Math.random() * zakres) + 0.5);
        }
    }
    void wypiszDane(){
        for(int i = 0 ; i < dane.length ; i++){
            System.out.println(dane[i]);
        }
    }
    double srednia(){
        double suma = 0;
        for(int i = 0 ; i < dane.length; i++){
            suma += dane[i];
        }
      
        return suma/dane.length;
    }
    int max(){
        int max = 0;
        for(int i = 0 ; i < dane.length ; i++){
            if(max < dane[i]){
                max = dane[i];
            }
        }
        return max;
    }
    int min(){
        int min = 0;
        for(int i = 0 ; i < dane.length ; i++){
            if(min > dane[i]){
                min = dane[i];
            }
        }
        return min;
    }
}
