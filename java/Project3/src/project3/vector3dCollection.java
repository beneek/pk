/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class vector3dCollection implements Container {
     Vector3d []vector3d;
     Iterator iterator;
    /**
     *
     * @return
     */
    @Override
    public Iterator getIterator(){
        vector3dIterator result = new vector3dIterator(5);
        return result;
    }
    

    
private class vector3dIterator implements Iterator 
   {
        private int position;
        
    public vector3dIterator(int n){
    vector3d = new Vector3d[n];
    double []dlugosc = new double [vector3d.length];
    for(int i = 0 ; i < vector3d.length ; i++){
        vector3d[i] = new Vector3d();
        dlugosc[i] = vector3d[i].vectorLength();
    }
    
    
            for (Vector3d vector3d1 : vector3d) {
                for (int j = 0; j < vector3d1.dane.length; j++) {
                    vector3d1.dane[j] = (Math.random() * 0.5 + 0.2);
                }
            }
    Vector3d temp;
    boolean zamieniono;
    
    do{
       zamieniono = false;
       for(int i = 1 ; i < vector3d.length ; i++){
           if(dlugosc[i] < dlugosc[i-1]){
               temp = vector3d[i];
               vector3d[i] = vector3d[i-1];
               vector3d[i-1] = temp;
               zamieniono = true;
           }
       }
    }while(zamieniono);
}    
    @Override
       public boolean hasNext(){
            return position < vector3d.length;
      }
       
    @Override
     public Object next()
     {
         if(this.hasNext()) {
             return vector3d[position++];
         }
         else {
             return null;
         }
     }
    
}
}
