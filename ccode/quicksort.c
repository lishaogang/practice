
divide(int arr[],int index,int head, int tail)
{
	int i = 0, t = 0;
	int n = index;
	for(i = head; i < index; i++)
	{
		if(arr[i] > arr[index])
		{
			n++;
			t = arr[i];
			arr[i] = arr[n];
			arr[n] = t;
			i--;
		}
	}
	n = index;
	for(i = tail-1; i > n; i--)
	{
		if(arr[i] < arr[index])
		{
			n++;
			t = arr[i];
			arr[i]=arr[n];
			arr[n]=t;
			i++;
		}
	}
	t = arr[index];
	arr[index]=arr[n];
	arr[n]=t;
	return n;
}
show(int arr[],int N)
{	
	int i = 0;
	for(i = 0; i < N; i++)
	{
		printf("%d ",arr[i]);
	}
	printf("\n");
}
sort(int arr[], int head, int m, int tail)
{
	int m1,m2;
	m1=divide(arr,(head+m)/2,head,m);
	sort(arr,head,m1,m);
	show(arr,10);
	m2=divide(arr,(m+tail)/2,m,tail);
	sort(arr,m,m2,tail);
}

main()
{
	int arr[10] = {10,9,2,7,6,5,4,3,2,1};
	show(arr,10);
	int m = divide(arr,4,0,10);
	show(arr,10);
	sort(arr,0,m,10);
}
