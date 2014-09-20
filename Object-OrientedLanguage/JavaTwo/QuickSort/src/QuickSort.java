
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = {7, 3, 5, 7, 8, 9, 10};
		for(int i =0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		quickSort(a, 0, a.length-1);
		System.out.println();
		for(int i =0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		

	}
	
	public static void quickSort(int[] a, int start, int end){
	
		if (end <= start)
			return;

		int pivot = a[end];

		int swaplocation = start;

		for (int runner = start; runner < end; runner++) {
			if (a[runner] <= pivot) {
				int temp = a[runner];
				a[runner] = a[swaplocation];
				a[swaplocation] = temp;
				swaplocation++;
			}

		}
		a[end] = a[swaplocation];
		a[swaplocation] = pivot;
		
		quickSort(a, 0, swaplocation-1);
		quickSort(a, swaplocation + 1, end);

	}

}
