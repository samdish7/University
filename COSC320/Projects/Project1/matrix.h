//Project 1
//Matrix.h
//Sam Disharoon

#ifndef MATRIX_H
#define MATRIX_H

class Matrix{
	private:
	long unsigned int rows,cols;
	float** A;
	public:
	Matrix();
	Matrix(long unsigned int,long unsigned int);
	Matrix(const Matrix&);
	Matrix operator=(const Matrix&);
	~Matrix();
	float** create()const;
	void fill();
	Matrix operator+(const Matrix&)const;
	Matrix operator-(const Matrix&)const;
	Matrix operator*(const Matrix&)const;
	Matrix operator*(const int&);
	Matrix operator*=(const int&);
	float* operator[](const int&)const;
	Matrix add(Matrix&,Matrix&);
	Matrix sub(Matrix&,Matrix&);
	Matrix mul(Matrix&,Matrix&);
	Matrix pad();
	Matrix trans();
	Matrix inv();
	void print();
};

#endif
