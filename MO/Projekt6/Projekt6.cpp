// Projekt6.cpp : Defines the entry point for the console application.
//


#include "stdafx.h"
using namespace std;
#define DIM 5

void show_matrix(double matrix[DIM][DIM]);
void met_thomas(double matrix[DIM][DIM]);
void solutions(double matrix[DIM][DIM],double *vect, double *solution);
void show_vect(double vect[DIM], string message);

int _tmain(int argc, _TCHAR* argv[])
{
	double Matrix[DIM][DIM];
	Matrix[0][0] = 10.0; Matrix[0][1] =  4.0; Matrix[0][2]=  0.0; Matrix[0][3] =  0.0; Matrix[0][4] =  0.0;
	Matrix[1][0] =  1.0; Matrix[1][1] = 11.0; Matrix[1][2]=  3.0; Matrix[1][3] =  0.0; Matrix[1][4] =  0.0;
	Matrix[2][0] =  0.0; Matrix[2][1] =  2.0; Matrix[2][2]= 12.0; Matrix[2][3] =  2.0; Matrix[2][4] =  0.0;
	Matrix[3][0] =  0.0; Matrix[3][1] =  0.0; Matrix[3][2]=  3.0; Matrix[3][3] = 13.0; Matrix[3][4] =  1.0;
	Matrix[4][0] =  0.0; Matrix[4][1] =  0.0; Matrix[4][2]=  0.0; Matrix[4][3] =  4.0; Matrix[4][4] = 14.0;

	double b[DIM] =  {18.0 , 32.0 , 44.0, 36.0, 22.0};
	double solution[DIM];
	show_matrix(Matrix);
	show_vect(b,"Wektor b:");
	
	met_thomas(Matrix);
	show_matrix(Matrix);
	solutions(Matrix,b,solution);
	cout<<"WYNIKI"<<endl;
	cout<<"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"<<endl;
	show_vect(solution,"Wektor wynikow:");
	return 0;
}
//////////////////////////////////////////////////////////////
void show_matrix(double matrix[DIM][DIM]){
	cout<<"Macierz trojdiagonalna:"<<endl;
	for(int i = 0 ; i < DIM ; i++){
		for(int j = 0 ; j < DIM ; j++){
			cout<<setw(12)<<matrix[i][j];
		}
		cout<<endl;
	}
	cout<<endl;
}
void met_thomas(double matrix[DIM][DIM]){
	cout<<"Metoda Thomasa:"<<endl;
	for(int i = 1 ; i < DIM ; i++){
		matrix[i][i] = matrix[i][i] - matrix[i][i-1]*(matrix[i-1][i]/matrix[i-1][i-1]);
		matrix[i][i-1] = matrix[i][i-1]/matrix[i-1][i-1];
	}
}
void solutions(double matrix[DIM][DIM],double *vect, double *solution){
	for(int i = 1 ; i < DIM ; i++){
		vect[i] = vect[i] - matrix[i][i-1]*vect[i-1];
	}
	solution[DIM - 1] = vect[DIM - 1]/matrix[DIM - 1][DIM - 1];
	for(int i = DIM - 2 ; i >= 0 ; i --){
		solution[i] = (vect[i] - matrix[i][i+1] * solution[i+1])/matrix[i][i];
	}
}

void show_vect(double vect[DIM],string message){
	cout<<message<<endl;
	cout.precision(5);
	for(int i = 0 ; i < DIM ; i ++){
		cout<<"\t\t  "<<vect[i]<<endl;;
	}
	cout<<endl;
}