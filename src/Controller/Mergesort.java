package Controller;

import java.util.ArrayList;
import java.util.List;

public class Mergesort {

	private static IPowerItemController[] aux;
	
	public static void sort(List<IPowerItemController> list, int select){
		
		aux = new IPowerItemController[list.size()];
		
		sort(list, 0, list.size()-1, select);
	}

	private static void sort(List<IPowerItemController> a, int i, int k, int select) {

		if(k <= i)
			return;
		
		int j = (i+k)/2;
		
		sort(a, i, j, select);
		sort(a, j+1, k, select);
		merge(a, i, j, k, select);
	}

	private static void merge(List<IPowerItemController> a, int low, int mid, int hi, int select) {
		
		int i = low, j = mid+1;
		
		for (int copy = low; copy<= hi; copy++){
			aux[copy] = a.get(copy);
		}
		
		for (int merge = low; merge <= hi; merge++){
			if(i > mid)
				a.set(merge, aux[j++]);
			else if (j > hi)
				a.set(merge, aux[i++]);
			else if (select == 0 && less(aux[j], aux[i]))
				a.set(merge, aux[j++]);
			else if (select == 1 && lessPerDollar(aux[j], aux[i]))
				a.set(merge, aux[j++]);
			else
				a.set(merge, aux[i++]);
		}
	}

	private static boolean less(IPowerItemController iPowerItemController, IPowerItemController iPowerItemController2) {
		
		return iPowerItemController.compareTo(iPowerItemController2) < 0;

	}
	
	private static boolean lessPerDollar(IPowerItemController iPowerItemController, IPowerItemController iPowerItemController2) {
		
		return iPowerItemController.comparePerDollar(iPowerItemController2) < 0;

	}
}
