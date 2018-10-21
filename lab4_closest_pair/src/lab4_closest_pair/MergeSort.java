package lab4_closest_pair;

import java.util.ArrayList;

public class MergeSort {
	
	public static ArrayList<Object> mergeSort(ArrayList<Object> obj, int left, int right)
	{
		if(right - left > 0)
		{
			int mid = (left+right)/2;
			mergeSort(obj, left, mid);
			mergeSort(obj, mid+1, right);
			
			ArrayList<Object> tmp = new ArrayList<Object>();
			for(int i = mid; i>left; --i)
			{
				tmp.add(obj.get(i));
			}
			for(int j = mid+1; j<right; ++j)
			{
				tmp.get()
			}
		}
	}

}
