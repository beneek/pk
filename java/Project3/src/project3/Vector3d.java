/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class Vector3d {
    double []dane;
    
    void randomInitialize(int range){
       dane = new double[3];
       for(int i = 0 ; i < dane.length ; i++){
           dane[i] = Math.random() * range + 0.5;
       }
    }
    public void showVector(){
         for(int i = 0 ; i < dane.length ; i++){
            System.out.println(dane[i]);
         }
    }
         
    public Vector3d addVector(Vector3d firstVector, Vector3d secondVector){
        Vector3d newVector = new Vector3d();
        newVector.dane[0] = firstVector.dane[0] + secondVector.dane[0];
        newVector.dane[1] = firstVector.dane[1] + secondVector.dane[1];
        newVector.dane[2] = firstVector.dane[2] + secondVector.dane[2];

        return newVector;    
    }
    public Vector3d addVector2(Vector3d secondVector){

        for(int i = 0 ; i < dane.length ; i++){
            dane[i] += secondVector.dane[i];
        }

        return this;    
    }
    public double vectorLength(){
        double vectorLength = 0;
        
        for(int i = 0 ; i < dane.length ; i++){
            vectorLength += dane[i]*dane[i];
        }
        vectorLength = Math.sqrt(vectorLength);
        return vectorLength;
    }
    
    public Vector3d multiplyByScalar(double scalar){
        for(int i = 0 ; i < dane.length ; i++){
            dane[i]*=scalar;
        }
        return this;
    }
    
    public double scalarMultiply(Vector3d secondVector){
        double scalar = 0;
        for(int i = 0 ; i < dane.length ; i++){
            scalar += dane[i]*secondVector.dane[i];
        }
        
        return scalar;
    }
    
    public Vector3d vectorMultiply(Vector3d secondVector){
        Vector3d newVect = new Vector3d
        ((dane[1]*secondVector.dane[2]-dane[2]*secondVector.dane[1]),
        (dane[2]*secondVector.dane[0]-dane[0]*secondVector.dane[2]),
        (dane[0]*secondVector.dane[1]-dane[1]*secondVector.dane[0]));
        
        return newVect;
    }
/*public Vector3D outerProduct(Vector3D vect) {
        Vector3D ret = new Vector3D((y*vect.z-z*vect.y), (z*vect.x-x*vect.z),
            (x*vect.y-y*vect.x));
       
        return ret;
    }*/
  public Vector3d(double dane1,double dane2, double dane3){
      dane = new double[3];
      dane[0]=dane1;
      dane[1]=dane2;
      dane[2]=dane3;
  }   @Override  
  public String toString(){
        return dane[0] + " " + dane[1] + " " + dane[2];
    }

    public Vector3d() {
            dane = new double[3];
    }
  
}
