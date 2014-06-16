/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class matrixCollection implements Container {

    Macierz []macierz;
    Iterator iterator;
    
    
    @Override
    public Iterator getIterator() {
        matrixIterator result = new matrixIterator(3);
        return result;
    }
    
private class matrixIterator implements Iterator 
   {
        private int position;
        
        public matrixIterator(int n){
            macierz = new Macierz[n];
            float []dim = new float[macierz.length];
            
            for(int i = 0 ; i < macierz.length ; i++){
                macierz[i] = new Macierz();
                dim[i] = macierz[i].ret_wyznacznik();
            }
            
            Macierz temp;
            boolean zamieniono;
            
            do{
                zamieniono = false;
                for(int i = 1 ; i < macierz.length ; i++){
                    if(dim[i] < dim[i-1]){
                        temp = macierz[i];
                        macierz[i] = macierz[i-1];
                        macierz[i-1] = temp;
                        zamieniono = true;
                    }
                }             
            }while(zamieniono);
        }
        
        
        @Override
    public boolean hasNext(){
            return position < macierz.length;
      }
       
        @Override
     public Object next()
     {
         if(this.hasNext()) {
             return macierz[position++];
         }
         else {
             return null;
         }
     }
    
}

   }