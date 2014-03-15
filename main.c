#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX_ITER 100
#define EPS 1e-5

double funkcja1(double );
double funkcja2(double );
double funkcja1poch(double x);
double MetodaPicarda(double (*f)(double),double);
double MetodaNewtona(double (*f)),double;

int main(){
MetodaPicarda(&funkcja1,3);

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
double MetodaPicarda(double (*f)(double),double x0)
{
        int i=0;
        double xn;
        xn = f(x0);
        do{
        x0 = xn;
        xn = f(x0);
        printf("%d:[Picard]:%.50lf\n",i,xn);
        i++;
        }while( i <= MAX_ITER && (fabs(x0 - xn) > EPS));
        printf("Wartosc z dokladnoscia %e w %d iteracjach\n",EPS,i);
        return xn;
}
