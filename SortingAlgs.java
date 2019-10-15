package DSA01_Package;

public class SortingAlgs 
{
	private int[] sortArray;
	
	//Creates the sortArray and copies the provided array to it.
	public SortingAlgs(int[] input)
	{
		sortArray = new int[input.length];
		sortArray = input.clone();
	}
	
	public void exchangeSort()
	{
		//Selects each element in order
		for (int i = 0; i < (sortArray.length -1); i++)
		{
			//Begins selecting elements after the element held above
			for (int j = (i + 1); j < sortArray.length; j++)
			{
				if (sortArray[i] > sortArray[j])
				{
					//Swaps sortArray[i] and sortArray[j]
					int temp = sortArray[i];
					sortArray[i] = sortArray[j];
					sortArray[j] = temp;
				}
			}
		}
	}
	
	public void mergeSort()
	{
		mSort(sortArray, 0, sortArray.length - 1);
	}
	
	private void mSort(int[] arr, int start, int end)
	{
		//Will continue to make recursive calls until it is broken down into its smallest possible comparable units
		if (start < end)
		{
			//set divide point
			int middle = (start + end)/2;
			
			//makes recursive call on "bottom" half of array.
			mSort(arr, start, middle);
			//makes recursive call on "top" half of array.
			mSort(arr, middle + 1, end);
			
			//combines recursively called arrays with middle element.
			merge(arr, start, middle, end);
		}
	}
	
	private void merge(int[] arr, int start, int middle, int end)
	{
		int size1 = middle - start + 1;
		int size2 = end - middle;
		
		//creates two sub arrays to hold elements of the main array
		int low[] = new int[size1];
		int high[] = new int[size2];
		
		//copies "low" end of subarray
		for (int i = 0; i < size1; ++i)
			low[i] = arr[start + i];
		//copies "high" end of subarray
		for (int j = 0; j < size2; ++j)
			high[j] = arr[middle + j + 1];
		
		int i = 0, j = 0, k = start;
		while (i < size1 && j < size2)
		{
			if (low[i] <= high[j])
			{
				//sets k element of main array equal to an element in the "low' subarray
				arr[k] = low[i];
				i++;
			}
			else
			{
				//sets k element of main array equal to an element in the "high" subarray
				arr[k] = high[j];
				j++;
			}
			k++;
		}
		
		while (i < size1)
		{
			//populates the rest of the "low" end of the main array with elements remaining in subarray
			arr[k] = low[i];
			i++;
			k++;
		}
		
		while (j < size2)
		{
			//populates the rest of the "high" end of the main array with elements remaining in the subarray
			arr[k] = high[j];
			j++;
			k++;
		}
	}
	
	public void quickSort()
	{
		qSort(sortArray, 0, sortArray.length -1);
	}
	
	private void qSort(int[] arr, int low, int high)
	{
		if (low < high)
		{
			//finds pivot in array
			int pivot = partition(arr, low, high);
			
			//divides array in half and calls method recursively
			qSort(arr, low, pivot - 1);
			qSort(arr, pivot + 1, high);
		}
	}
	
	private int partition(int[] arr, int low, int high)
	{
		int pivot = arr[low];
		int j = low;
		
		for (int i = low + 1;i <= high; i++)
		{
			if (arr[i] < pivot)
			{
				//swaps arr[i] and arr[j]
				j++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		//swaps arr[low] and arr[pivot]
		pivot = j;
		int temp = arr[low];
		arr[low] = arr[j];
		arr[j] = temp;
		
		return pivot;
	}
	
}
