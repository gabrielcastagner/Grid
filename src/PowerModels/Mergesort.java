package PowerModels;

public class Mergesort {

	private Comparable[] aux;
	
	public void sort(Comparable[] a){
		
		aux = new Comparable[a.length];
		
		sort(a, 0, a.length-1);
	}

	private void sort(Comparable[] a, int i, int k) {

		if(k <= i)
			return;
		
		int j = (i+k)/2;
		
		sort(a, i, k);
		sort(a, j+1, k);
		merge(a, i, j, k);
	}

	private void merge(Comparable[] a, int low, int midd, int hi) {
		
		int i = low, j = hi+1;
		
		for (int copy = low; copy<= hi; copy++){
			aux[copy] = a[copy];
		}
		
		for (int merge = low; merge <= hi; merge++){
			if(i > midd)
				a[merge] = aux[j++];
			else if (j > hi)
				a[merge] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[merge] = aux[j++];
			else
				a[merge] = aux[i++];
		}
	}

	private boolean less(Comparable a, Comparable b) {
		
		return a.compareTo(b) < 0;

	}
}
