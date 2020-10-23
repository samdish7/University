//matrix.cpp
//Sam Disharoon
#include "matrix.h"
#include<iostream>
#include<time.h>
#include<chrono>
Matrix::Matrix(long unsigned int x, long unsigned int y){
	srand(time(NULL));
	rows=x;
	cols=y;
	A=new int*[rows];
	for (int h=0;h<rows;h++){
		A[h]=new int[cols];
	}
	for (int i=0;i<rows;i++){
		for (int j=0;j<cols;j++){
		A[i][j]=rand() % 10+1;
		}
	}
}
Matrix::Matrix(const Matrix& other){
	rows=other.rows;
	cols=other.cols;	
	A=new int*[rows];
	for(int y=0;y<other.rows;y++){
	A[y]=new int[cols];
	}
	for(int g=0;g<other.rows;g++){
		for(int r=0;r<other.cols;r++){
		A[g][r]=0;
		A[g][r]+=other.A[g][r];
		}
	}

}
Matrix& Matrix::operator=(const Matrix& other){
	rows=other.rows;
	cols=other.cols;
	A=new int*[rows];
	for(int y=0;y<other.rows;y++){
	A[y]=new int[cols];
	}
	for(int g=0;g<other.rows;g++){
		for(int r=0;r<other.cols;r++){
		A[g][r]=0;
		A[g][r]+=other.A[g][r];
		}
	}

return *this;
}
Matrix::~Matrix(){
	for(int j=0;j<cols;j++){
	delete[] A[j];
	}	
	delete[] A;
	
}
void Matrix::print(){
	int i,j;
	for(i=0;i<rows;i++){
		for(j=0;j<cols;j++){
		std::cout<<A[i][j]<<" ";
		}
	std::cout<<"\n";
	}
}
void test(void (*yur)(Matrix&,Matrix&),Matrix& a,Matrix& b){
        auto start=std::chrono::system_clock::now();
        yur(a,b);
        auto end=std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds=end-start;
        std::time_t end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

}
Matrix Matrix::add(Matrix& a1,Matrix& a2){
	if(rows==0&&cols==0){
	rows=a1.rows;
	cols=a1.cols;
	A=new int* [rows];
	for(int i=0;i<rows;i++){
	 A[i]=new int[cols];
	}
	}
	else if(a1.rows!=a2.rows || a1.cols!=a2.cols||rows!=a1.rows||cols!=a1.cols)
	{
	std::cout<<"Can't add!\n";
	Matrix empty;
	return empty;
	}
        auto start=std::chrono::system_clock::now();
	rows=a1.rows;
	cols=a1.cols;

	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
		A[i][j]=a1.A[i][j]+a2.A[i][j];
		}
	}
        auto end=std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds=end-start;
        std::time_t end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

return *this;
}
Matrix Matrix::sub(Matrix& a1,Matrix& a2){
	if(rows==0&&cols==0){
	rows=a1.rows;
	cols=a1.cols;
	A=new int* [rows];
	for(int i=0;i<rows;i++){
	 A[i]=new int[cols];
	}
	}
	else if(a1.rows!=a2.rows || a1.cols!=a2.cols ||rows!=a1.rows ||cols!=a1.cols)
	{
	std::cout<<"Can't subtract!\n";
	Matrix empty;
	return empty;
	}
        auto start=std::chrono::system_clock::now();
	rows=a1.rows;
	cols=a1.cols;

	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
		A[i][j]=a1.A[i][j]-a2.A[i][j];
		}
	}
        auto end=std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds=end-start;
        std::time_t end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

return *this;
}
Matrix Matrix::mul(Matrix& a1, Matrix& a2){
	if(rows==0&&cols==0){
	rows=a1.rows;
	cols=a2.cols;
	A=new int* [rows];
	for(int i=0;i<rows;i++){
	 A[i]=new int[cols];
	}
	}
	else if(a1.rows!=a2.cols||rows!=a1.rows||cols!=a1.cols||rows!=a2.rows||cols!=a2.cols){
		std::cout<<"Can't multiply!\n";
		Matrix empty;
		return empty;
	}
        auto start=std::chrono::system_clock::now();
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++){
		A[i][j]=0;
		
			for(int t=0;t<cols;t++){
				A[i][j]=a1.A[i][t]*a2.A[t][j];
			}
		}
	}
        auto end=std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds=end-start;
        std::time_t end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

return *this;
}

