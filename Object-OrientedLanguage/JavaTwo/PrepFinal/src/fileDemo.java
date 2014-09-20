import java.io.File;
import java.io.*;

public class fileDemo {
	public static void main(String[] args) throws Exception {
		int[] a = {1,2,6,7,90,50,30,22,100};
		for(int i =0; i<a.length;i++){
			System.out.print(a[i] + " ");
		}
		bubbleSort(a);
		System.out.println();
		for(int i =0; i<a.length;i++){
			System.out.print(a[i] + " ");
		}
	
	}
	
	public static void bubbleSort(int[] n){
		for(int i = 0; i<n.length-1; i++){
			for(int j =1; j<n.length-i; j++){
				if(n[j-1]>n[j]){
					int tem = n[j-1];
					n[j-1] = n[j];
					n[j]= tem;
				}
			}
		}
	}
}