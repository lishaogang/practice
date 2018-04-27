
public class sort{
	public static void swap(int arr[], int a, int b){
		int t = arr[a];
		arr[a] = arr[b];
		arr[b] = t;
	}

	public static void quickSort(int arr[], int _left, int _right){
		int left = _left;
		int right = _right;
		

		if(left < right){
			int temp = arr[left];
			while(left != right){
				while(right > left && arr[right] >= temp)
					right--;
				arr[left] = arr[right];
				while(right > left && arr[left] <= temp)
					left++;
				arr[right] = arr[left];
			}
			arr[right] = temp;
			quickSort(arr, _left, left-1);
			quickSort(arr, right+1, _right);
		}
	}

	public static int [] getIntArr(int n)
	{
		int arr[] = new int[n];
		for(int i = 0; i < n; i++){
			arr[i] = (int)(Math.random() * 100);
		}
		return arr;
	}

	public static void main(String[] args){
		int arr[] = getIntArr(10);
		for(int e:arr)
			System.out.print(e+"\t");
		System.out.println();
		quickSort(arr,0,9);
		for(int i = 0; i < 10; i++)
			System.out.print(arr[i]+"\t");
	}
}

