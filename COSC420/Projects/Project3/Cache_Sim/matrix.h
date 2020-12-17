#ifndef MATRIX_H
#define MATRIX_H
#define INDEX(A,i,j) A->cols*i+j
#define ACCESS(A,i,j) A->data[INDEX(A,i,j)]
#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <math.h>

static int cache = 32000;

typedef struct 
{
	int rows, cols;
	double* data;	
} matrix;

void initMatrix(matrix *A, int rows, int cols)
{	
	int i,j;
	A->rows = rows;
	A->cols = cols;
	A->data = malloc(A->rows*A->cols*sizeof(double));
	for(i=0; i < A->rows; i++)
	{
		for(j = 0; j < A->cols; j++)
		{
			ACCESS(A,i,j) = rand() % 10 + 1;
		}
	}
}

void createMatrix(matrix *A)
{
	int i,j;
	A->data = malloc(A->rows*A->cols*sizeof(double));
	for(i=0;i < A->rows;i++)
	{	
		for(j=0;j < A->cols;j++)
		{
			ACCESS(A,i,j) = rand() % 10;
		}
	}
}

void AllocateMatrix(matrix *A)
{
	int i,j;
	A->data = malloc(A->rows*A->cols*sizeof(double));
	for(i=0;i < A->rows;i++)
	{	
		for(j=0;j < A->cols;j++)
		{
			ACCESS(A,i,j) = 0;
		}
	}
}

void createIdentityMatrix(matrix *A)
{
	int i,j;
	A->data = malloc(A->rows*A->cols*sizeof(double));
	for(i=0; i < A->rows; i++)
	{
		for(j=0; j < A->cols; j++)
		{
			if(i == j)
			{
				ACCESS(A,i,j) = 1;
			}
			else
			{
				ACCESS(A,i,j) = 0;
			}
		}
		puts("");
	}
}

void printMatrix(matrix *A)
{
	int i,j;
	for(i=0;i < A->rows;i++)
	{	
		for(j=0;j < A->cols;j++)
		{
			printf("%0.2f ", ACCESS(A,i,j));
		}
		puts("");
	}
}

void MPIAddMatrix(matrix *A, matrix *B, matrix *C, MPI_Comm* world, int worldSize, int myRank)
{
	int N = A->rows * A->cols; //Area of amtrix
	int localLen = N/worldSize; //Length of each partition
	double *localArrA = malloc(localLen*sizeof(double));
	double *localArrB = malloc(localLen*sizeof(double));
	double *sol = malloc(localLen*sizeof(double)); // solution
	//Scatters both Matrices to be operated on in parts.
	MPI_Scatter(A->data, localLen, MPI_DOUBLE, localArrA, localLen, MPI_DOUBLE, 0, *world);
	MPI_Scatter(B->data, localLen, MPI_DOUBLE, localArrB, localLen, MPI_DOUBLE, 0, *world);
	int i;
	//Cretaes the array of solutions. The actual addition part
	//puts("Matrix A: ");
	for(i=0; i < localLen; i++)
	{
		sol[i] = localArrA[i] + localArrB[i]; 
		//printf("%0.2f ",sol[i]); 
	}
	// Puts all data into fin to be transfered to C->dataMPI_Gather(sol, localLen, MPI_DOUBLE, C->data, localLocal, MPI_DOUBLE, 0, *world);
	MPI_Gather(sol, localLen, MPI_DOUBLE, C->data, localLen, MPI_DOUBLE, 0, *world);

	free(localArrA);
	free(localArrB);
	free(sol);
}

void MPISubMatrix(matrix *A, matrix *B, matrix *C, MPI_Comm* world, int worldSize, int myRank)
{
	int N = A->rows * A->cols; //Area of amtrix
	int localLen = N/worldSize; //Length of each partition
	double *localArrA = malloc(localLen*sizeof(double));
	double *localArrB = malloc(localLen*sizeof(double));
	double *sol = malloc(localLen*sizeof(double)); // solution
	//Scatters both Matrices to be operated on in parts.
	MPI_Scatter(A->data, localLen, MPI_DOUBLE, localArrA, localLen, MPI_DOUBLE, 0, *world);
	MPI_Scatter(B->data, localLen, MPI_DOUBLE, localArrB, localLen, MPI_DOUBLE, 0, *world);
	int i;
	//Cretaes the array of solutions. The actual addition part


	for(i=0; i < localLen; i++)
	{
		sol[i] = localArrA[i] - localArrB[i];  
	}
	// Puts all data into fin to be transfered to C->data
	MPI_Gather(sol, localLen, MPI_DOUBLE, C->data, localLen, MPI_DOUBLE, 0, *world);	
	free(localArrA);
	free(localArrB);
	free(sol);
}

//ijk form of Mult
//B is accessed by cols which only provides part of a row for each cache loaded.
void SerialMult(matrix *A, matrix *B, matrix *C, int myRank)
{
	int i,j,k;
	double cacheHits = 0;
	double cacheMisses = 0;
	double total = 0; //cacheHits + cacheMisses;
	double cacheHitRatio = 0; //(cacheHits / total) 
	double cacheMissRatio = 0; // cacheMisses / requests 
	for(i=0; i < A->rows; i++)
	{
		for(j=0;j < B->cols; j++)
		{
			for(k = 0; k < A->cols; k++)
			{
				ACCESS(C,i,j) += ACCESS(A,i,k) * ACCESS(B,k,j);
			}
		}
	}
	cacheHits = A->rows * A->rows; //Cache hits once per row
	cacheMisses = (9/8 * (A->rows * A->rows * A->rows));	
	total = cacheHits + cacheMisses;
	cacheHitRatio = cacheHits / total;
	cacheMissRatio = (1 - cacheHitRatio);
	cacheHitRatio *= 100; //Make percentage
	cacheMissRatio *= 100; //Make percentage
	if(myRank == 0)
	{
		puts("-------------------------------------------");
		printf("Serial IJK ordering of Matrix Mult Info:\n");
		printf("Number of Cache Hits: %3.0f\n", cacheHits);
		printf("Number of Cache Misses: %3.0f\n", cacheMisses);
		printf("Cache Hit ratio: %3.2f percent\n", cacheHitRatio);
		printf("Cache Miss ratio: %3.2f percent\n", cacheMissRatio);
	}
//	Free all long doubles and ints
}

//A is loaded once as opposed to n times in SerialMult
//B is accessed by rows which fully utilizes caches
//ikj form of Mult
void BestSerialMult(matrix *A, matrix *B, matrix *C, int myRank)
{
	int i,j,k;
	double cacheHits = 0;
	int cacheMisses = 0;
	int total = 0; //cacheHits + cacheMisses;
	double cacheHitRatio = 0; //(cacheHits / total) 
	double cacheMissRatio = 0; // cacheMisses / requests 
	for(i = 0; i < A->rows; i++)
	{
		for(k = 0; k < B->cols; k++)
		{
			for(j = 0; j < A->cols ; j++)
			{
				ACCESS(C,i,j) += ACCESS(A,i,k) * ACCESS(B,k,j);
			}
		}
	}
	cacheHits = (A->rows * A->rows - (1 * A->rows)); //Cache hits everytime except once per row
/*	cacheMisses = ((A->rows * A->rows) / B->cols) + ((A->rows * A->rows * A->rows) / B->cols) + ((A->rows * A->rows) / B->cols);
*/	cacheMisses = (9/8) * (A->rows * A->rows);
	total = cacheHits + cacheMisses;
	cacheHitRatio = (cacheHits / total);
	cacheMissRatio = (1 - cacheHitRatio);
	cacheHitRatio *= 100; //Make percentage
	cacheMissRatio *= 100; //Make percentage
	if(myRank == 0)
	{
		puts("-------------------------------------------");
		printf("Serial IKJ ordering of Matrix Mult Info:\n");
		printf("Number of Cache Hits: %3.0f\n", cacheHits);
		printf("Number of Cache Misses: %d\n", cacheMisses);
		printf("Cache Hit ratio: %3.2f percent\n", cacheHitRatio);
		printf("Cache Miss ratio: %3.2f percent\n", cacheMissRatio);
	}
}

//Blocked Matrix Mult
void BlockedSerialMatrixMult(matrix *A, matrix *B, matrix *C, int blockSize, int myRank)
{
	int i,j,k, i1, j1, k1;
	double cacheHits = 0;
	int cacheMisses = 0;
	int total = 0; //cacheHits + cacheMisses;
	double cacheHitRatio = 0; //(cacheHits / total) 
	double cacheMissRatio = 0; // cacheMisses / requests 
	for(i = 0; i < A->rows; i += blockSize)
	{
		for(j = 0; j < A->rows; j += blockSize)
		{
			for(k=0; k < A->rows; k += blockSize)
			{
				for(i1 = i; i1 < i + blockSize; i1++)
				{
					for(j1 = j; j1 < j + blockSize; j1++)
					{
						for(k1 = k; k1 < k + blockSize; k1++)
						{
							ACCESS(C,i1,j1) += ACCESS(A,i1,k1) * ACCESS(B,k1,j1);
						}
					}
				}
			}
		}
	}
	cacheHits = (A->rows * A->rows - (1 * A->rows)); //Cache hits everytime except once per row
	cacheMisses = (A->rows * A->rows * A->rows) / (4 * blockSize);
	total = cacheHits + cacheMisses;
	cacheHitRatio = (cacheHits / total);
	cacheMissRatio = (1 - cacheHitRatio);
	cacheHitRatio *= 100; //Make percentage
	cacheMissRatio *= 100; //Make percentage
	if(myRank == 0)
	{
		printf("Blocked ordering of Matrix Mult Info:\n");
		printf("Number of Cache Hits: %3.0f\n", cacheHits);
		printf("Number of Cache Misses: %d\n", cacheMisses);
		printf("Cache Hit ratio: %3.2f percent\n", cacheHitRatio);
		printf("Cache Miss ratio: %3.2f percent\n", cacheMissRatio);
	}
}

double InnerProd(matrix *A, matrix *B, MPI_Comm *world, int worldSize, int myRank)
{
	if(A->rows != 1 && A->cols != 1)
	{
		puts("A is not a vector\n");
		return -1;
	}
	if(B->rows != 1 && B->cols != 1)
	{
		puts("B is not a vector\n");
		return -1;
	}
	if(A->rows*A->cols != B->rows*B->cols)
	{
		puts("Matrices aren't same size\n");
		return -1;
	}

	int length = A->rows * A->cols;
	int VectorArray[worldSize];
	int displacement[worldSize];
	int j;

	for(j = 0; j < worldSize; j++)
	{
		VectorArray[j] = length / worldSize;
	}

	for(j = 0; j < (length % worldSize); j++)
	{
		VectorArray[j] += 1;
	}

	int nextLength = 0;
	for(j = 0; j < worldSize; j++)
	{
		if(j == 0)
		{
			displacement[j] = 0;
			nextLength = VectorArray[j];
			continue;
		}
		displacement[j] = displacement[j - 1] + nextLength;
		nextLength = VectorArray[j];
	}

	int matrixLen = VectorArray[myRank]; // Nodes divide array size
	double result = 0; //Final prod
	
	double *localMatA = (double*)malloc(matrixLen*sizeof(double));
	double *localMatB = (double*)malloc(matrixLen*sizeof(double));
	
	MPI_Scatterv(A->data, VectorArray, displacement, MPI_DOUBLE, localMatA, matrixLen, MPI_DOUBLE, 0, *world);
	MPI_Scatterv(B->data, VectorArray, displacement, MPI_DOUBLE, localMatB, matrixLen, MPI_DOUBLE, 0, *world);

	double sum = 0;
	int i;
	int matrixLenByte = matrixLen*sizeof(double);
	int localcache = (matrixLenByte / cache);
	if(matrixLenByte < cache)
	{
		for(i = 0; i < matrixLen; i++)
		{
			sum += localMatA[i] * localMatB[i];
		}
	}
	else
	{
		for(i = 0; i < matrixLenByte * localcache; i+= localcache)
		{
			for(j = 0; j < i+cache && j < matrixLen; j++)
			{
				sum += localMatA[i] * localMatB[i];	
			}	
		}
	}

	MPI_Reduce(&sum, &result, 1, MPI_DOUBLE, MPI_SUM, 0, *world);
	
	free(localMatA);
	free(localMatB);
	if(myRank == 0)
	{
		return result;
	}
	return -1;
}

//Altered matrix Mult
void classicMultMatrix(matrix *A, matrix *B, matrix *C, MPI_Comm *world, int worldSize, int myRank)
{
	if(A->cols != B->rows)
	{
		puts("Wrong dimensions, need square matrices\n");
	}

	matrix Atemp;
	matrix Btemp;
	initMatrix(&Atemp, 1, A->cols);
	initMatrix(&Btemp, B->cols, 1);

	int i,j,k;
	for(i = 0; i < A->rows; i++)
	{
		for(j = 0; j < B->cols; j++)
		{
			if(myRank == 0)
			{
				//Copies temp A data over
				for(k = 0; k < A->cols; k++)
				{
					Atemp.data[k] = ACCESS(A,i,k);
				}
			}

			if(myRank == 0)
			{
				//Copies temp A data over
				for(k = 0; k < A->cols; k++)
				{
					Btemp.data[k] = ACCESS(B,k,j);
				}
			}

			double InnerProduct = InnerProd(&Atemp, &Btemp, world, worldSize, myRank);

			if(myRank == 0)
			{
				ACCESS(C,i,j) = InnerProduct;
			}
		}
	}

	free(Atemp.data);
	free(Btemp.data);
}

void FasterMultMatrix(matrix *A, matrix *B, matrix *C, MPI_Comm *world, int worldSize, int myRank)
{
	if(A->cols != B->rows)
	{
		puts("Wrong dimensions, need square matrices\n");
	}

	matrix Atemp;
	matrix Btemp;
	initMatrix(&Atemp, 1, A->cols);
	initMatrix(&Btemp, B->cols, 1);

	int i,j,k;
	for(i = 0; i < A->rows; i++)
	{
		for(k = 0; k < B->cols; k++)
		{
			if(myRank == 0)
			{
				//Copies temp A data over
				for(j = 0; j < A->cols; j++)
				{
					Atemp.data[k] = ACCESS(A,i,k);
				}
			}

			if(myRank == 0)
			{
				//Copies temp A data over
				for(j = 0; j < A->cols; j++)
				{
					Btemp.data[k] = ACCESS(B,k,j);
				}
			}

			double InnerProduct = InnerProd(&Atemp, &Btemp, world, worldSize, myRank);

			if(myRank == 0)
			{
				ACCESS(C,i,j) = InnerProduct;
			}
		}
	}

	free(Atemp.data);
	free(Btemp.data);
}

#endif
