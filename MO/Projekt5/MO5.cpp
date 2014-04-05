// MO5.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

#define DIM 4
#define WHOLE 1
#define MAT_L 2
#define MAT_U 3

using namespace std;

double ** newMatrix(int n, int m);
void deleteMatrix(double **a,int n);
void showMatrix(double **matrix,int n,int mode);
void dek_LU(double **matrix,double *vect,int *index,  int n);
void find_el(double **matrix, int *vect, int n, int pi, int pj);
void solve_eq(double **matrix, int n, double *vect, double *x, double *y, int *index);



int _tmain(int argc, _TCHAR* argv[])
{
	double **Matrix = newMatrix(DIM,DIM);
    Matrix[0][0] = 1.0; Matrix[0][1] = 2.0; Matrix[0][2] = 2.0; Matrix[0][3] = 1.0;    
    Matrix[1][0] = 2.0; Matrix[1][1] = 4.0; Matrix[1][2] = 4.0; Matrix[1][3] = 1.0;    
    Matrix[2][0] = 2.0; Matrix[2][1] = 2.0; Matrix[2][2] = 2.0; Matrix[2][3] = 1.0;
    Matrix[3][0] = 1.0; Matrix[3][1] = 1.0; Matrix[3][2] = 2.0; Matrix[3][3] = 1.0;

	double **MacLU = newMatrix(DIM,DIM);
	double *x = new double[4];
	double *y = new double[4];

	double Vector[4] = {1.0,2.0,3.0,4.0};
	int index[4] = {0 ,1 , 2 ,3};

	cout<<"Macierz: "<<endl;
	showMatrix(Matrix,DIM,WHOLE);
	dek_LU(Matrix,Vector,index,DIM);
	showMatrix(Matrix,DIM,MAT_L);
	showMatrix(Matrix,DIM,MAT_U);
	solve_eq(Matrix, DIM, Vector, x, y, index);



	cout<<endl;

	cout<<"Rozwiazania:"<<endl;
	for(int i = 0 ; i < DIM ; i++){
		cout<<i<<": "<<x
			[i]<<endl;
	}


   
	system("PAUSE");
	return 0;
}

double ** newMatrix(int n, int m){
	int i;
	double **a;
	a = new double*[n];
	for(i = 0;i<n;i++)
	{
		a[i] = new double[m];
	}
	return a;
}

void deleteMatrix(double **a,int n){
	int i;
	for(i = (n - 1);i>=0;i--)
	{
		delete [] a[i];
	}
	delete [] a;
}

void showMatrix(double **matrix,int n,int mode){
	if(mode == WHOLE){
	for(int i = 0 ; i < n ; i++){
		cout<<endl;
		for(int j = 0 ; j < n ; j++)
			cout<<"\t"<<matrix[i][j]<<" ";
		}
	}
	else if(mode == MAT_U){
	cout<<"Macierz U:"<<endl;
	for(int i = 0 ; i < DIM ; i++){
		cout<<endl;
		for(int j = 0 ; j < DIM ; j++)
			printf("\t%.2lf " , (j<i) ? 0 : matrix[i][j]);
		}
	}
	else if(mode == MAT_L){
	cout<<"Macierz L:"<<endl;
	for(int i = 0 ; i < DIM ; i++){
		cout<<endl;
		for(int j = 0 ; j < DIM ; j++)
			printf("\t%.2lf " , (j>i) ? 0 :  (j==i) ? 1 : matrix[i][j]);
		}
	}
	cout<<endl;
	cout<<endl;


}

void dek_LU(double **matrix,double *vect,int *index, int n){	
	//int j;
	for(int i = 0 ; i < n ; i++){
		if(matrix[index[i]][i] == 0) find_el(matrix,index, n , i , i);
		for(int j = i+1 ; j < n ; j++){
			for(int k = i+1; k < n ; k++){
				matrix[index[j]][k] = matrix[index[j]][k] - matrix[index[i]][k] * matrix[index[j]][i]/matrix[index[i]][i];//gorna czesc macierzy
			}
			matrix[index[j]][i] = matrix[index[j]][i]/matrix[index[i]][i];//dolna zczesc macierzy
		}
	}
}; 
//funkcja pomocnicza, znajduje maksymalny element kolumny i zamienia poszczegolne kolumny
void find_el(double **matrix, int *vect, int n, int pi, int pj){

	int i,j,temp,position;

	double max;
	i = pi;

	//szukanie pierwszej wartosci roznej od zera
	do{
		position = i;
		max = matrix[i][pj];
		i++;
	}while(max == 0);

	//szukanie maksimum w kolumnie

    for(; i < n ; i++){
		//jezeli element macierzy jest wiekszy od max, zamien wiersze
		if(fabs(matrix[vect[i]][pj]) > max){
			max = matrix[vect[i]][pj];
			position = i;
		}
	}
	temp = vect[pi];
	vect[pi] = vect[position];
	vect[position] = temp;
}
void solve_eq(double **matrix, int n, double *vect, double *x, double *y, int *index){
	double sum = 0;
	y[0] = vect[index[0]] / matrix[index[0]][0];
	for(int i = 1 ; i < n ; i++ , sum = 0){
		for(int j = 0 ; j <  i ; j++){
			sum += matrix[index[i]][j] * y[j];
		}
		y[i] = vect[index[i]] - sum;
	}
	x[n-1] = y[n-1] / matrix[index[n-1]][n-1];
	for(int i = n - 2; i >= 0 ; i-- , sum = 0){
		for(int j = i + 1; j < n ; j++){
			sum+= matrix[index[i]][j] * x[j];
		}
		x[i] = (y[i] - sum)/matrix[index[i]][i];
	}
}
