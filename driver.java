package DSA01_Package;

import java.util.Random;
import java.util.Scanner;

public class driver 
{

	public static void main(String[] args) 
	{
		Random rand = new Random();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Boolean more = true;
		
		while(more)
		{
			System.out.println("Enter 1 for sorting, 2 for matrix multipliation, 3 to quit:");
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter size of array:");
				int size = sc.nextInt();
				int[] arr = new int[size];
				for (int i = 0; i < size; i++)
				{
					int temp = rand.nextInt(Integer.MAX_VALUE);
					arr[i] = temp;
				}
				SortingAlgs sa1 = new SortingAlgs(arr);
				long startTime = System.nanoTime();
				sa1.exchangeSort();
				long endTime = System.nanoTime();
				double runTime = (double)(endTime - startTime)/1000000000.0;
				System.out.println("Exchange Sort finished in " + runTime + " seconds.");
				
				SortingAlgs sa2 = new SortingAlgs(arr);
				startTime = System.nanoTime();
				sa2.mergeSort();
				endTime = System.nanoTime();
				runTime = (double)(endTime - startTime)/1000000000.0;
				System.out.println("Merge Sort finished in " + runTime + " seconds.");
				
				SortingAlgs sa3 = new SortingAlgs(arr);
				startTime = System.nanoTime();
				sa3.quickSort();
				endTime = System.nanoTime();
				runTime = (double)(endTime - startTime)/1000000000.0;
				System.out.println("Quick Sort finished in " + runTime + " seconds.");
				break;
				
			case 2:
				System.out.println("Enter size of nxn matrix (please select a power of 2):");
				int n = sc.nextInt();
				int[][] matrix1 = new int[n][n];
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						int temp = rand.nextInt(Integer.MAX_VALUE);
						matrix1[i][j] = temp;
					}
				}
				int[][] matrix2 = new int[n][n];
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						int temp = rand.nextInt(Integer.MAX_VALUE);
						matrix2[i][j] = temp;
					}
				}
				MultAlgs ma1 = new MultAlgs(matrix1, matrix2);
				long startTime2 = System.nanoTime();
				ma1.classicMult();
				long endTime2 = System.nanoTime();
				double runTime2 = (double)(endTime2 - startTime2)/1000000000.0;
				System.out.println("Classical method finished in " + runTime2 + " seconds.");
				
				MultAlgs ma2 = new MultAlgs(matrix1, matrix2);
				startTime2 = System.nanoTime();
				ma2.strassenMult();
				endTime2 = System.nanoTime();
				runTime2 = (double)(endTime2 - startTime2)/1000000000.0;
				System.out.println("Strassen method finished in " + runTime2 + " seconds.");
				break;
				
			case 3:
				more = false;
				break;
			}
		}
		System.out.println("Program exit.");
	}

}
