// MO06.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

void showMatrix(double matrix[][DIM]);
void showVect(double vect[DIM]);
void jacobi(int n, double matrix[][DIM], double b[], double x[]);
void gaussSeidel(int n, double matrix[][DIM], double b[], double x[]);
void sor(int n, double matrix[][DIM], double b[], double x[], double omega);

int _tmain(int argc, _TCHAR* argv[])
{
	double matrix[DIM][DIM], b[DIM];
	matrix[0][0] = 5; matrix[0][1] = 1;
	matrix[1][0] = 4; matrix[1][1] = 10;

	//Przyblizenia poczatkowe
	double x[DIM];
	x[0] = 1; x[1] = 1;

	b[0] = 49; b[1] =30;

	cout<<"Macierz A:"<<endl;
	showMatrix(matrix);
	cout<<"Wektor b:"<<endl;
	showVect(b);
	cout<<"Przyblizenia poczatkowe:"<<endl;
	showVect(x);

	jacobi(DIM,matrix,b,x);
	x[0] = 1; x[1] = 1;
	gaussSeidel(DIM,matrix,b,x);
	x[0] = 1; x[1] = 1;
	sor(DIM,matrix,b,x,0.5);

	string dupa ="dupa";
	cout<<dupa;

	return 0;
}
////////////////////////////////////////
void showMatrix(double matrix[][DIM]){
	for(int i = 0 ; i < DIM ; i++){
		for(int j = 0 ; j < DIM ; j++){
			cout<<matrix[i][j]<<" ";
		}
		cout<<endl;
	}
		cout<<endl;

}
void showVect(double vect[DIM]){
	for(int i = 0 ; i < DIM ; i++){
		cout<<vect[i]<<" ";
	}
	cout<<endl;
    cout<<endl;

}
///////////////////////////////////////////////////
void jacobi(int n, double matrix[][DIM], double b[], double x[]){
	double sum, newApprox[DIM];
	double temp, r;
	int iter = 0;
	bool epsFlag = false,tolFlag = false;
	cout<<"METODA JAKOBIEGO:"<<endl;
	cout<<"Wyniki posrednie:"<<endl;

	while(true){
		r = 0;
//		est = 0;
		for(int i = 0 ; i < n ; i++){
			sum = 0;
			for(int j = 0; j < n ; j++){
				if(j != i ) sum+=matrix[i][j] * x[j];	
			}

		newApprox[i] = (b[i] - sum) / matrix[i][i];
			//r[i] = matrix[i][0] * newApprox[0] + matrix[i][1] * newApprox[1] - b[i];

		if(fabs(x[i] - newApprox[i]) < EPS){
				epsFlag = true;
				break;
		}
		
		temp = fabs(b[i] - sum);
		if(temp > r){
			r = temp;
		}

		if( r < VECTEPS){
			tolFlag = true;
			break;
		}
		for(int i = 0 ; i < n ; i++){
			x[i] = newApprox[i];
		}
		}
		cout<<iter<<"\t"<<x[0] << "\t"<<x[1]<<endl;
		if(iter >= MAX_ITER){
			cout<<"Osiagnieto max ilosc iteracji"<<endl;
			break;
		}
		if(epsFlag){
			cout<<"Osiagnieto zadana dokladnosc eps dla wektora"<<endl;
			break;
		}
		if(tolFlag){
			cout<<"Osiagnieto zadana tolerancje"<<endl;

			break;
		}
		iter++;
	}

}

void gaussSeidel(int n, double matrix[][DIM], double b[], double x[]){
	double sum, x1,r,temp;
	int iter = 0;
	bool epsFlag = false,tolFlag = false;
	cout<<"METODA GaussaSiedela:"<<endl;
	cout<<"Wyniki posrednie:"<<endl;
	while(true){
		r = 0;
		for(int i = 0 ; i  < n ; i++){
			sum = 0;
			for(int j= 0 ; j < n ; j++){
				if(j != i) sum += matrix[i][j] * x[j];
			}
		   x1 = x[i];
		   x[i] = (b[i] - sum)/ matrix[i][i];

		   if(fabs(x[i] - x1)< EPS){
			   epsFlag = true;
			   break;
		   }
		temp = fabs(b[i] - sum);
		if(temp > r){
			r = temp;
		}

		if( r < VECTEPS){
			tolFlag = true;
			break;
		}
		}
		cout<<iter << "\t" << x[0] << "\t"<<x[1]<<endl;
		if(iter >= MAX_ITER){
			cout<<"Osiagnieto max ilosc iteracji."<<endl;
			break;
		}
		if(epsFlag){
			cout<<"Osiagnieto zadana dokladnosc eps dla wektora"<<endl;
			break;
		}
		if(tolFlag){
			cout<<"Osiagnieto zadana tolerancje"<<endl;

			break;
		}
		iter++;
	}
}
void sor(int n, double a[][DIM], double b[], double x[], double omega) {
	double sum, newApprox,r,temp;
	int iter = 0;
	bool epsFlag = false,tolFlag = false;
	cout << "METODA SOR" << endl;
	cout << "Wyniki posrednie:" << endl;
	while(true) {
		r = 0;
		for (int i = 0; i < n; i++) {
			sum = 0;
			for (int j = 0; j < n; j++)
				if(j != i) sum += a[i][j] * x[j];		
			newApprox = (1 - omega) * x[i] + omega *  (b[i] - sum) / a[i][i];
			if(fabs(x[i] - newApprox) < EPS)
				epsFlag = true;
			x[i] = newApprox;
			if(epsFlag) break;

			temp = fabs(b[i] - sum);
			if(temp > r){
			r = temp;
			}

		if( r < VECTEPS){
			tolFlag = true;
			break;
		}
		}
		cout << iter << "\t" << x[0] << "\t" << x[1] << endl;
		if(iter >= MAX_ITER) {
			cout << "Osiagnieto ilosc iteracji." << endl;
			break;
		}
		if(epsFlag) {
			cout << "Osiagnieto dokladnosc eps dla elementow wektora." << endl;
			break;
		}
		if(tolFlag){
			cout<<"Osiagnieto zadana tolerancje"<<endl;
			break;
		}
		iter ++;
	}
}

