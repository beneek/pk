#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX_ITER 100
#define EPS 1e-5

double funkcja1(double );
double funkcja2(double );
double funkcja1poch(double x);
double funkcja2poch(double x);
double MetodaPicarda(double (*f)(double),double);
double MetodaNewtona(double (*f)(double),double (*fpoch)(double), double x0);

int main(){
MetodaPicarda(&funkcja1,3);
MetodaNewtona(&funkcja1,&funkcja1poch,2);
MetodaNewtona(&funkcja2,&funkcja2poch,3);

return 0;
}
///////////////////////////////////////////////////
double funkcja1(double x){
        return 1 - sin(x/2.0);
}
double funkcja1poch(double x){
        return (-cos(x/2.0) / 2.0);
}
///////////////////////////////////////////////////
double funkcja2(double x){
        return 1 - tan(x);
}
///////////////////////////////////////////////////
double funkcja2poch(double x){
	return (-1 / ()cos(x)*cos(x)));
}
///////////////////////////////////////////////////
double MetodaPicarda(double (*f)(double),double x0)
{
        int i=0;
        double xn;
        xn = f(x0);
        do{
        x0 = xn;
        xn = f(x0);
        printf("%d:[Picard]:%.25lf\n",i,xn);
        i++;
        }while( i < MAX_ITER && (fabs(x0 - xn) > EPS));
        printf("Wartosc z dokladnoscia %e w %d iteracjach\n\n",EPS,i);
        return xn;
}
///////////////////////////////////////////////////////
double MetodaNewtona(double (*f)(double),double (*fpoch)(double), double x0)
{
	double f1, xn = x0 + 0.1 , f0 = f(x0);
	int i = 0;
	do{
	 f1 = fpoch(x0);
	 xn = x0;
	 x0 = x0 - f0 / f1;
	 f0 = f(x0);	
	 i++;
	 printf("%d:[Newton]:%0.25lf\n",i,xn);
	}while(i < MAX_ITER && fabs(xn - x0) > EPS && fabs(f1) >= EPS);
	 printf("Wartosc z dokladnoscia %e w %d iteracjach\n\n",EPS,i);

}
