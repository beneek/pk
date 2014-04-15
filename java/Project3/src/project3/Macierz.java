/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author bpokrzyw
 */
public class Macierz {
   float[][] dane= new float[10][10]; 

public Macierz() {
    for(float[] dane1 : dane){
        for(int i = 0 ; i < dane1.length ; i++){
            dane1[i]=(float) ((float)Math.random() * 10 +0.5);
        }
    }
};
public Macierz(int k) {
     for(int i = 0 ; i < 10; i++){
        for(int j = 0 ; j < 10 ; j++){
           if(i == j){
               dane[i][j]=1;
           }
           else{
               dane[i][j]=0;
           }
               
        }
     } 
};

public void wyswietlMacierz(){
    for(float[] dane1 : dane){
        System.out.println();
        for(int i = 0 ; i < dane1.length ; i++){
            System.out.printf("\t%.3f",dane1[i]);
        }
    }
    System.out.println();
};
public static Macierz mnozMacierzy(Macierz pierwszaMacierz,Macierz drugaMacierz){

    Macierz nowaMacierz = new Macierz();
    nowaMacierz.zeruj_macierz();
    for(int i = 0 ; i < pierwszaMacierz.dane.length ; i++){
        for(int j = 0 ; j < drugaMacierz.dane[0].length ; j++){
            for(int k = 0 ; k < drugaMacierz.dane.length ; k++){
            nowaMacierz.dane[i][j] += pierwszaMacierz.dane[j][k] * drugaMacierz.dane[k][i];
               }
         }
 }          
return nowaMacierz;
};

private void zeruj_macierz(){
     for(int i = 0 ; i < dane.length ; i++) {
        for(int j = 0 ; j < dane.length ; j++) {
             dane[i][j] = 0;
         }
    }
};

public Macierz Transponowanie(){
    Macierz newMatrix = new Macierz();
       for(int i = 0 ; i < dane.length ; i++){
            for(int j = 0 ; j < dane[0].length ; j++){
               newMatrix.dane[i][j] = dane[j][i];     
            }
       }    
       return newMatrix;
}
public float ret_wyznacznik(){
    return licz_wyznacznik(this.dane,this.dane.length);
}
 
 private float licz_wyznacznik(float[][] mac, int n) {
        int sign = 1, p = 0, q = 0;
        float det = 0;
 
	if(n==1){
		det = mac[0][0];
	}
	else{
		float b[][] = new float[n-1][n-1];
		for(int x = 0 ; x < n ; x++){
			p=0;q=0;
			for(int i = 1;i < n; i++){
				for(int j = 0; j < n;j++){
					if(j != x){
						b[p][q++] = mac[i][j];
						if(q % (n-1) == 0){
							p++;
							q=0;
						}
					}
				}
			}
			det = det + mac[0][x] *
                        licz_wyznacznik(b, n-1) * sign;
			sign = -sign;
		}
	}
	return det;
 };

    @Override
    public String toString() {
       String stringBuilder = new String();
       for (float[] dane1 : dane) {
           for (int j = 0; j < dane1.length; j++) {
               stringBuilder += String.valueOf(dane1[j]);
           }
           stringBuilder += '\n';
       }
       return stringBuilder;
    }
 
};
   
   


