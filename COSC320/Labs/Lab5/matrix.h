//matrix.h
//Sam Disharoon
#ifndef MATRIX_H
#define MATRIX_H
	
class Matrix{
	private:
	long unsigned int rows,cols;
	int** A;
	public:
	Matrix(){rows=0;cols=0;A=new int*[rows];
	for(int i=0;i<rows;i++)
	A[i]=new int[cols];
	}
	Matrix add(Matrix&, Matrix&);
	Matrix sub(Matrix&, Matrix&);
	Matrix mul(Matrix&, Matrix&);
	void print();
	void test(void (*yur),Matrix&,Matrix&);
	Matrix(long unsigned int, long unsigned int);
	Matrix(const Matrix&);
	~Matrix();
	Matrix& operator=(const Matrix&);
};
#endif
