package sort;

public class Sort {

	public static void main(String[] args){
	int[] testArray = {10,4,9,12,33,1,46,14,7,55,9};
//		BubbleSort(testArray);
//		QuickSort(testArray,0, testArray.length-1);
//        InsertSort(testArray);
	 HeapSort(testArray);
		printArray(testArray);
	}
	
	//冒泡排序
	public static void BubbleSort(int[] array){
		boolean flag = false;
		if (array.length == 0) return;
		if(array.length ==1) {System.out.println(array[0]); return;}
		for (int i = 0; i < array.length-1; i++) {
			for (int j = 0; j < array.length-i-1; j++) {
				if(array[j]>array[j+1]){
					int tmp = array[j+1];
					array[j+1] = array[j];
					array[j] = tmp;
					flag = true;
				}
			}	
			if(!flag) return; 
		}
		
	}
	
	//快速排序
	public static void QuickSort(int[] array,int start,int end){
		if(array == null || start == end) return;
		int flag = (int)(Math.random()*(end-start))+start;
		int compare = array[flag];
	    int left = start;
	    int right = end;
	    while (left<right) {
	    	while(left<right && array[right] > compare){
				right--;
			}
			if (left<right) {
				array[flag] = array[right];
				flag = right;		
			}	
			while(left<right && array[left] < compare){
				left++;
			}
			if(left < right){
				array[flag] = array[left]; 
				flag = left;
			}
			if(array[left] == array[right] && right -left == 1 ){return;}
		}
	    array[left] = compare;
		if(flag-start > 1)QuickSort(array, start, flag-1);
		if(end-flag > 1) QuickSort(array, flag+1, end);
		}
	
	//插入排序
	public static void InsertSort(int[] array){
		int[] resutlArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int location = i;
			for (int j = 0; j < i; j++) {
				if(array[i]<resutlArray[j]) {location = j;break;}//一定要加Break
			}
			for (int k = i-1; k >=location; k--) {//一定注意等号
			resutlArray[k+1] = resutlArray[k];	
			}
			resutlArray[location] = array[i];
		}
		System.arraycopy(resutlArray, 0, array, 0, array.length);
	}
		
	//希尔排序
	public static void  ShellSort(){
				
	}
	public static void HeapSort(int[] array){
		int length = array.length;
		for (int i = length/2; i >=1 ; i--) {
			HeapAdjust(array, i, length);
		}
			
		}
		
	//大根堆调整
	public static void HeapAdjust(int[] array,int start,int sum){
		int i = start;
		int cmp = array[start];
		for (int j = 2*i; j < sum-1; j*=2) {
		if(j<sum && array[j]<array[j+1]) j = j+1;
		if(cmp > array[j]) break;
		array[i] = array[j];
		i = j;
		}
		
		array[i] = cmp;
		
	}
		

	
	
	public static void printArray(int[] array){
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
}
