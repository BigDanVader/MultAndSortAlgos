package DSA01_Package;

public class MultAlgs 
{
	private int[][] matrix1;
	private int[][] matrix2;
	
	public MultAlgs(int[][] input1, int[][] input2)
	{
		matrix1 = new int[input1.length][];
		for (int i = 0; i < input1.length; i++)
			matrix1[i] = input1[i].clone();
		
		matrix2 = new int[input2.length][];
		for (int i = 0; i < input2.length; i++)
			matrix2[i] = input2[i].clone();
	}
	
	public void classicMult()
	{
		int[][] result = new int[matrix1.length][matrix1.length];
		
		for (int i = 0; i < result.length; i++)
		{
			for (int j = 0; j < result.length; j++)
			{
				for (int k = 0; k < result.length; k++)
				{
					//multiplies individual matrix elements and stores them in the result matrix
					result[i][j] = result[i][j] + matrix1[i][k] * matrix2[k][j]; 
				}
			}
		}
	}
	
	public void strassenMult()
	{
		@SuppressWarnings("unused")
		int[][] result = sMult(matrix1, matrix2);
	}
	
	private int[][] sMult(int[][] a, int[][]b)
	{
		int n = a.length;
		int[][] result = new int[n][n];
		
		if (n == 1)
			result[0][0] = a[0][0] * b[0][0];
		else
		{
			//creates four sub-matricies for each matrix
			int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
            
            //populates the sub-matricies with portions of the main matricies
            split(a, A11, 0 , 0);
            split(a, A12, 0 , n/2);
            split(a, A21, n/2, 0);
            split(a, A22, n/2, n/2);

            split(b, B11, 0 , 0);
            split(b, B12, 0 , n/2);
            split(b, B21, n/2, 0);
            split(b, B22, n/2, n/2);
            
            //makes recursive calls on pairs of sub-matricies after adding or subtracting them together
            int [][] M1 = sMult(add(A11, A22), add(B11, B22));
            int [][] M2 = sMult(add(A21, A22), B11);
            int [][] M3 = sMult(A11, sub(B12, B22));
            int [][] M4 = sMult(A22, sub(B21, B11));
            int [][] M5 = sMult(add(A11, A12), B22);
            int [][] M6 = sMult(sub(A21, A11), add(B11, B12));
            int [][] M7 = sMult(sub(A12, A22), add(B21, B22));
            
            //adds results of all recursive calls
            int [][] C11 = add(sub(add(M1, M4), M5), M7);
            int [][] C12 = add(M3, M5);
            int [][] C21 = add(M2, M4);
            int [][] C22 = add(sub(add(M1, M3), M2), M6);
            
            //combines results from above into one matrix
            join(C11, result, 0 , 0);
            join(C12, result, 0 , n/2);
            join(C21, result, n/2, 0);
            join(C22, result, n/2, n/2);
		}
		
		return result;
	}
	
	private int[][] add(int[][]a, int[][]b)
	{
		int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
	}
	
	private void join(int[][] c, int[][] p, int ib, int jb)
	{
		for(int i1 = 0, i2 = ib; i1 < c.length; i1++, i2++)
            for(int j1 = 0, j2 = jb; j1 < c.length; j1++, j2++)
                p[i2][j2] = c[i1][j1];
	}
	
	private void split(int[][] p, int[][] c, int ib, int jb)
	{
		for(int i1 = 0, i2 = ib; i1 < c.length; i1++, i2++)
            for(int j1 = 0, j2 = jb; j1 < c.length; j1++, j2++)
                c[i1][j1] = p[i2][j2];
	}
	
	private int[][] sub(int[][] a, int[][] b)
	{
		 int n = a.length;
	        int[][] c = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                c[i][j] = a[i][j] - b[i][j];
	        return c;
	}
}
