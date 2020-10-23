//Project 1
//Matrix.cpp
//Sam Disharoon

#include"matrix.h"
#include<iostream>
#include<time.h>
Matrix::Matrix(){
	rows=0;
	cols=0;
	A=create();

}
Matrix::Matrix(long unsigned int x, long unsigned int y){//ctor
	if(x<=0||y<=0){
		std::cout<<"Matrix error!\n";
	}
	else{
		rows=x;
		cols=y;
		A=create();
	}
}
float** Matrix::create()const{//allocate memory
	float** m=new float*[rows];
	for(int i=0;i<rows;i++){
		m[i]=new float[cols];
	}
	return m;
}
void Matrix::fill(){//fill values
	srand(time(NULL));
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			A[i][j]=rand() %100/2;
		}
	}
}
Matrix::Matrix(const Matrix& other){//Copy ctor
	rows=other.rows;
	cols=other.cols;
	A=new float*[rows];
	for(int y=0;y<other.rows;y++){
		A[y]=new float[cols];
	}
	for(int g=0;g<other.rows;g++){
		for(int r=0;r<other.cols;r++){
			A[g][r]=0;
			A[g][r]+=other.A[g][r];
		}
	}

}
Matrix Matrix::operator=(const Matrix& other){//overloaded = operator
	rows=other.rows;
	cols=other.cols;
	A=new float*[rows];
	for(int y=0;y<other.rows;y++){
		A[y]=new float[cols];
	}
	for(int g=0;g<other.rows;g++){
		for(int r=0;r<other.cols;r++){
			A[g][r]=0;
			A[g][r]+=other.A[g][r];
		}
	}

	return *this;
}
Matrix::~Matrix(){//default contructor
	for(int j=0;j<rows;j++){
		delete[] A[j];
	}
	delete[] A;

}
void Matrix::print(){//print function
	int i,j;
	for(i=0;i<rows;i++){
		for(j=0;j<cols;j++){
			std::cout<<A[i][j]<<" ";
		}
		std::cout<<"\n";
	}
}
Matrix Matrix::operator+(const Matrix& m1)const{//overloaded + operator
	if(rows!=m1.rows||cols!=m1.cols){
		std::cout<<"Rows and columns don't match!\n";
		Matrix empty;
		return empty;
	}
	Matrix m2= Matrix(rows,cols);
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			m2.A[i][j]=A[i][j]+m1.A[i][j];
		}
	}
	return m2;

}
Matrix Matrix::operator-(const Matrix& m1)const{//overloaded - operator
	if(rows!=m1.rows||cols!=m1.cols){
		std::cout<<"Rows and columns don't match!\n";
		Matrix empty;
		return empty;
	}
	Matrix m2=Matrix(rows,cols);
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			m2.A[i][j]=A[i][j]-m1.A[i][j];
		}
	}
	return m2;
}
Matrix Matrix::operator*(const Matrix& m1)const{//overloaded * for matrix multiplication
	if(cols!=m1.rows){
		std::cout<<"Column of first doesn't match row of second!\n";
		Matrix empty;
		return empty;
	}
	Matrix m2=Matrix(rows,m1.cols);
	for(int i=0;i<rows;i++){
		for(int j=0;j<m1.cols;j++){
			for(int k=0;k<cols;k++){
				m2.A[i][j]+=A[i][j]*m1.A[k][j];
			}
		}
	}
	return m2;
}
Matrix Matrix::add(Matrix& a1,Matrix& a2){//+ function
	if(rows==0&&cols==0){
		rows=a1.rows;
		cols=a1.cols;
		A=new float* [rows];
		for(int i=0;i<rows;i++){
			A[i]=new float[cols];
		}
	}
	else if(a1.rows!=a2.rows || a1.cols!=a2.cols||rows!=a1.rows||cols!=a1.cols)
	{
		std::cout<<"Can't add!\n";
		Matrix empty;
		return empty;
	}
	rows=a1.rows;
	cols=a1.cols;

	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			A[i][j]=a1.A[i][j]+a2.A[i][j];
		}
	}
	return *this;
}
Matrix Matrix::sub(Matrix& a1,Matrix& a2){//- function
	if(rows==0&&cols==0){
		rows=a1.rows;
		cols=a1.cols;
		A=new float* [rows];
		for(int i=0;i<rows;i++){
			A[i]=new float[cols];
		}
	}
	else if(a1.rows!=a2.rows || a1.cols!=a2.cols ||rows!=a1.rows ||cols!=a1.cols)
	{
		std::cout<<"Can't subtract!\n";
		Matrix empty;
		return empty;
	}
	rows=a1.rows;
	cols=a1.cols;

	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			A[i][j]=a1.A[i][j]-a2.A[i][j];
		}
	}
	return *this;
}
Matrix Matrix::mul(Matrix& a1, Matrix& a2){//* function
	if(rows==0&&cols==0){
		rows=a1.rows;
		cols=a2.cols;
		A=new float* [rows];
		for(int i=0;i<rows;i++){
			A[i]=new float[cols];
		}
	}
	else if(a1.rows!=a2.cols||rows!=a1.rows||cols!=a1.cols||rows!=a2.rows||cols!=a2.cols){
		std::cout<<"Can't multiply!\n";
		Matrix empty;
		return empty;
	}
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
			A[i][j]=0;

			for(int t=0;t<cols;t++){
				A[i][j]=a1.A[i][t]*a2.A[t][j];
			}
		}
	}
	return *this;
}
Matrix Matrix::trans(){//Transpose
	Matrix trans;
	trans.cols=cols;
	trans.rows=rows;
	trans.A=new float*[trans.rows];
	for(int h=0;h<trans.rows;h++){
		trans.A[h]=new float[cols];
	}
	for(int i=0;i<rows;i++){
		for(int j=0;j<rows;j++){
			trans.A[j][i]=A[i][j];
		}
	}
	return trans;
}
Matrix Matrix::pad(){//Pad matrix
	int padder=2;
	while((padder<rows||padder<cols)&&padder>0){
	padder*=2;
	}
	float** oldA=A;
	long unsigned int oldR=rows;
	long unsigned int oldC=cols;
	rows=cols=padder;
	A=create();

	for(int i=0;i<rows;i++){
	for(int j=0;j<cols;j++){
		if(i>=oldR||j>=oldC){
			if(i==j){
			A[i][j]=1;
			}
			else{
			A[i][j]=0;
			}
			}
		else {
			A[i][j]=oldA[i][j];
			}

		}

	}
	for(int k=0;k<oldR;k++){
		delete[] oldA[k];
	}
	delete[] oldA;
	return *this;
}
Matrix Matrix::operator*(const int& s){//Scalar multiplication
	Matrix m2(rows,cols);
	int x=0;
	for(int i=0;i<rows;i++){
	for(int j=0;j<cols;j++){
		x=s*A[i][j];
		std::cout<<x<<"\n";
	}
	}
	return m2;
}
Matrix Matrix::operator*=(const int& s){//Scalar multiplication
	for(int i=0;i<rows;i++){
	for(int j=0;j<cols;j++){
		A[i][j]*=s;
	}
	}
	return *this;
}
float* Matrix::operator[](const int& x)const{//[]overload
	return A[x];
}
Matrix Matrix::inv(){		//Inverse function
	if(rows!=cols){
	std::cout<<"Not square!\n";
	Matrix empty;
	return empty;
	}
	if(rows==1&&cols==1){
		if(A[0][0]!=0){
		A[0][0]=1/A[0][0];
		}
		return *this;
	}
	int padR=rows;
	int padC=cols;
	pad();
	int halfR=rows/2;
	int halfC=cols/2;

	Matrix B=Matrix(halfR,halfC);
	Matrix C=Matrix(halfR,halfC);
	Matrix Ct=Matrix(halfR,halfC);
	Matrix D=Matrix(halfR,halfC);

	for(int i=0;i<rows;i++){
	for(int j=0;j<cols;j++){
		if(i<halfR&&j<halfC){
			B[i][j]=A[i][j];
		}
		else if(i<halfR && j<halfC){
			Ct[i][j-halfC]=A[i][j];
		}
		else if(i>=halfR && j<halfC){
			C[i-halfR][j]=A[i][j];
		}
		else if(i>=halfR && j>=halfC){
			D[i-halfR][j-halfC]=A[i][j];
		}
	}
	}
	Matrix Bi=B.inv();
	Matrix W=C*Bi;
	Matrix Wt=W.trans();
	Matrix X=W*Ct;
	Matrix S=D-X;
	Matrix V=S.inv();
	Matrix Y=V*W;
	Matrix Yt=Y.trans();
	Matrix L=Yt*-1;
	Matrix U=Y*-1;
	Matrix Z=Wt*Y;
	Matrix R=Bi+Z;

	for(int i=0;i<rows;i++){
		delete[] A[i];
	}
		delete[] A;

	rows=padR;
	cols=padC;
	A=create();
	for(int i=0;i<rows;i++){
	for(int j=0;j<cols;j++){
		if(i<halfR&&j<halfC){
			A[i][j]=R.A[i][j];
		}
		else if(i<halfR&&j>=halfC){
			A[i][j]=L.A[i][j-halfC];
		}
		else if(i>=halfR&&j<halfC){
			A[i][j]=U.A[i-halfR][j];
		}
		else if(i>= halfR&&j>=halfC){
			A[i][j]=V.A[i-halfR][j-halfC];
		}
	}
	}
	return *this;
}
