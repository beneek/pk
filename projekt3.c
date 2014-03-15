#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX_ITER 100
#define EPS 1e-5

double funkcja1_pic(double );
double funkcja2_pic(double );
double funkcja1(double );
double funkcja2(double );
double funkcja1poch(double x);
double funkcja2poch(double x);
double MetodaPicarda(double (*f)(double),double);
double MetodaNewtona(double (*f)(double),double (*fpoch)(double), double x0);
double RegulaFalsi(double (*f));
double MetodaBisekcji(double(*f)(double),double xp, double xk);

int main(){
printf("[Picard,sin]:%0.25lf\n\n",MetodaPicarda(&funkcja1_pic,5));
printf("[Picard,tan]:%0.25lf\n\n",MetodaPicarda(&funkcja2_pic,5));

printf("[Newton,sin]:%0.25lf\n\n",MetodaNewtona(&funkcja1,&funkcja1poch,1));
printf("[Newton,tan]:%0.25lf\n\n",MetodaNewtona(&funkcja2,&funkcja2poch,1));

printf("[Bisekcja,sin]:%0.25lf\n\n",MetodaBisekcji(&funkcja1,-5,5));
printf("[Bisekcja,tan]:%0.25lf\n\n",MetodaBisekcji(&funkcja2,-5,5));



return 0;
}
///////////////////////////////////////////////////
double funkcja1_pic(double x){
        return (1 - sin(x/2.0) );
}
double funkcja1poch(double x){
        return (-cos(x/2.0) / 2.0);
}
////////////////////////////////////////////////////
double funkcja1(double x){
        return (x+ sin(x/2.0) -1 );
}
double funkcja2(double x){
        return (x + tan(x) - 1);
}
///////////////////////////////////////////////////
double funkcja2_pic(double x){
        return (1.0 - tan(x));
}
///////////////////////////////////////////////////
double funkcja2poch(double x){
	return (-1.0 / (cos(x)*cos(x)));
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
        return xn;
}
///////////////////////////////////////////////////////
double MetodaNewtona(double (*f)(double),double (*fpoch)(double), double x0)
{
	double f1, xn = x0 - 1 , f0 = f(x0);
	int i = 0;
	do{
	 f1 = fpoch(x0);
	 xn = x0;
	 x0 = x0 - f0/f1;
	 f0 = f(x0);	
	 i++;
	 printf("%d:[Newton]:%0.25lf\n",i,xn);
	}while(i < MAX_ITER && fabs(xn - x0) > EPS && fabs(f1) >= EPS);
	 return xn;

}
//////////////////////////////////////////////////////
double MetodaBisekcji(double(*f)(double),double xp, double xk)
{
	int i = 0;
	double xn;
	if((f(xp) * f(xk)) < 0){
	
	do{
		xn = (xp + xk)/2;
		if(f(xp) * f(xn) >= 0)
			xp = xn;
	 	else
	 		xk = xn; 		
	 	printf("%d:[Bisekcja]:%.25lf\n",i,xn);
	 	i++;		
	}while(i < MAX_ITER && fabs(xp - xk) >= EPS && fabs(f(xp) - f(xk)) >= EPS);
	}	
	else
	{
		printf("Zly przedzial lub funkcja!\n");
		return 0;
	}
	return xn;
}

