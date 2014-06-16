#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#define MAX_ITER 100
#define EPS 10e-23

double funkcja1(double ,double );
double funkcja2(double ,double );
void MetNew(double (*f1)(double,double), double(*f2)(double,double));


int main()
{
	
	MetNew(&funkcja1,&funkcja2);
	return 0;
}
//////////////////////////////////////////////////////////////////////
double funkcja1(double x, double y){
	return x * x + y * y - 8;
}
double funkcja2(double x, double y){
	return 4*x + y*y*y;
}
//////////////////////////////////////////////////////////////////////
void MetNew(double (*f1)(double,double), double (*f2)(double,double)){
	int i = 0;
	double Jakobi[2][2];
	double Przyb[2] = { 0 , sqrt(8)};
	double Wynik[2];
	double WyzJ,WyzJx,WyzJy,fx,fy;
	do{
		fx = f1(Przyb[0],Przyb[1]);
		fy = f2(Przyb[0],Przyb[1]);
		//Obliczanie macierzy Jacobiego
		Jakobi[0][0] = 2*Przyb[0];
		Jakobi[1][0] = 4;
		Jakobi[0][1] = 2*Przyb[1];
		Jakobi[1][1] = 3*Przyb[1]*Przyb[1];

		//Liczenie wyznacznika macierzy Jacobiego
		WyzJ = Jakobi[0][0] * Jakobi[1][1] - Jakobi[1][0]*Jakobi[0][1];
		WyzJx = fx * Jakobi[1][1] - fy *Jakobi[0][1];
		WyzJy = Jakobi[0][0] * fy - Jakobi[1][0] * fx;

		Wynik[0] = WyzJx / WyzJ;
		Wynik[1] = WyzJy / WyzJ;

		//Nastepne przyblizenie
		Przyb[0]-=Wynik[0];
		Przyb[1]-=Wynik[1];
		
		printf("%d-> x:%lf\ty:%lf\tf1(x,y):%lf\tf2(x,y):%lf\n",i,Przyb[0],Przyb[1],fx,fy);
		i++;
		if(fabs(fx) < EPS && fabs(fy) < EPS){
			printf("Osiagnieto odpowiednia dokladnosc dla wartosci funkcji, koncze zadanie\n");
			return;
		}
		if(fabs(Wynik[0]) < EPS && fabs(Wynik[1]) < EPS){
			printf("Osiagnieto odpowiednia dokladnosc dla wektora, koncze zadanie\n");
			return;
		}
	}while(i <= MAX_ITER);
	printf("Osiagnieto maksymalna ilosc iteracji!\n");

}