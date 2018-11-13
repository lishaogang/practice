public class Main{
	    static int arr[]={1,2,3,4,5,6,7,8,9};  
	        static int count=0;  
		    public static void main(String[] args) {  
			            dfs(0); 
				    For(;;){}
				            System.out.println(count/8);  
					        }  
		        public static void dfs(int k){  
				        if(k==arr.length){  
						            if(arr[0]+arr[1]+arr[2]!=arr[3]+arr[4]+arr[5]&&arr[0]+arr[1]+arr[2]!=arr[6]+arr[7]+arr[8]&&arr[0]+arr[1]+arr[2]!=arr[3]+arr[0]+arr[6]&&arr[0]+arr[1]+arr[2]!=arr[1]+arr[4]+arr[7]&&arr[0]+arr[1]+arr[2]!=arr[5]+arr[2]+arr[8]&&arr[0]+arr[1]+arr[2]!=arr[0]+arr[4]+arr[8]&&arr[0]+arr[1]+arr[2]!=arr[2]+arr[4]+arr[6])  
								                    if(arr[3]+arr[4]+arr[5]!=arr[6]+arr[7]+arr[8]&&arr[3]+arr[4]+arr[5]!=arr[0]+arr[3]+arr[6]&&arr[3]+arr[4]+arr[5]!=arr[1]+arr[4]+arr[7]&&arr[3]+arr[4]+arr[5]!=arr[2]+arr[8]+arr[5]&&arr[3]+arr[4]+arr[5]!=arr[0]+arr[4]+arr[8]&&arr[3]+arr[4]+arr[5]!=arr[2]+arr[4]+arr[6])  
											                        if(arr[6]+arr[7]+arr[8]!=arr[0]+arr[3]+arr[6]&&arr[6]+arr[7]+arr[8]!=arr[1]+arr[4]+arr[7]&&arr[6]+arr[7]+arr[8]!=arr[2]+arr[8]+arr[5]&&arr[6]+arr[7]+arr[8]!=arr[0]+arr[4]+arr[8]&&arr[6]+arr[7]+arr[8]!=arr[2]+arr[4]+arr[6])  
															                        if(arr[0]+arr[3]+arr[6]!=arr[1]+arr[4]+arr[7]&&arr[0]+arr[3]+arr[6]!=arr[2]+arr[8]+arr[5]&&arr[0]+arr[3]+arr[6]!=arr[0]+arr[4]+arr[8]&&arr[0]+arr[3]+arr[6]!=arr[2]+arr[4]+arr[6])  
																			                            if(arr[1]+arr[4]+arr[7]!=arr[2]+arr[8]+arr[5]&&arr[1]+arr[4]+arr[7]!=arr[0]+arr[4]+arr[8]&&arr[1]+arr[4]+arr[7]!=arr[2]+arr[4]+arr[6])  
																							                                    if(arr[2]+arr[5]+arr[8]!=arr[0]+arr[4]+arr[8]&&arr[2]+arr[5]+arr[8]!=arr[2]+arr[4]+arr[6])  
																												                                        if(arr[0]+arr[4]+arr[8]!=arr[2]+arr[4]+arr[6])  
																																		                                        count++;  
							                return;  
									        }  
					        for(int i=k;i<arr.length;i++){  
							            int t=arr[k];  
								                arr[k]=arr[i];  
										            arr[i]=t;  
											                dfs(k+1);  
													            t=arr[k];  
														                arr[k]=arr[i];  
																            arr[i]=t;  
																	            }  
						    }  
}  
