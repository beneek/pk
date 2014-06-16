/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class Project3 {

   
    public static void main(String[] args) {
        Dane dane = new Dane();
        dane.inicjalizujLosowo(4, 5);
        dane.wypiszDane();
        System.out.println();
        System.out.println(dane.max());
        System.out.println(dane.min());
        System.out.println(dane.srednia());
        
        System.out.println();
        Vector3d vector3d = new Vector3d();
        Vector3d vector3d1 = new Vector3d();
        vector3d1.randomInitialize(5);
        vector3d1.showVector();
        System.out.println();

        vector3d.randomInitialize(10);
        vector3d.showVector();
        System.out.println(vector3d.multiplyByScalar(2).toString());
        System.out.println(vector3d.vectorLength());
        System.out.println();
        System.out.println(vector3d.addVector2(vector3d1).toString());
        System.out.println(vector3d.scalarMultiply(vector3d1));
        System.out.println(vector3d.vectorMultiply(vector3d1));
        System.out.println();
        System.out.println();
        
        Macierz maciorka =  new Macierz();       
        Macierz maciorka1 =  new Macierz(2);
        Macierz maciorka2 = new Macierz();
        
        maciorka.wyswietlMacierz();
        maciorka1.wyswietlMacierz();
        maciorka2 = Macierz.mnozMacierzy(maciorka, maciorka1);
        maciorka2.wyswietlMacierz();
        maciorka2.Transponowanie().wyswietlMacierz();
        
        System.out.println();
        System.out.println(maciorka2.ret_wyznacznik());
        System.out.println();

        vector3dCollection vectorCollection = new vector3dCollection();
        vectorCollection.iterator = vectorCollection.getIterator();
        
        
        System.out.println();
        System.out.println();

        while(vectorCollection.iterator.hasNext()){
            System.out.println(vectorCollection.iterator.next());  
        }
        System.out.println();
        System.out.println();

        
        matrixCollection MatrixCollection = new matrixCollection();
        
        MatrixCollection.iterator = MatrixCollection.getIterator();
        
        while(MatrixCollection.iterator.hasNext()){
            System.out.println(MatrixCollection.iterator.next().toString());
            System.out.println();
            System.out.println();
        }


        
    }
}
