
public class Line {

	//һ�ַǳ�����ʵ��
	public static void bresenham(int x0, int y0, int x1, int y1) {
	    int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	    int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	    
	    int err = (dx > dy ? dx : -dy) / 2; //�˴����ĳ�ʼֵ��whileѭ���������if�������

	    while (x0 != x1 || y0 != y1) {
	    	setpixel(x0, y0);
	        int e2 = err;

	        if (e2 > -dx) { err -= dy; x0 += sx;}
	        if (e2 <  dy) { err += dx; y0 += sy;}
	    }
	    setpixel(x0, y0);//���յ�
	}

	//��д��һ��ʵ�֣�������������תΪ��������
	public static void myLine_int(int x0, int y0, int x1, int y1)
	{
		int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	    int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
		int m = 2*dy, n = 2*dx;
		int erry = 0, errx = 0;
		while( x0 != x1 || y0 != y1)
		{
			setpixel(x0, y0);
			erry += m;
			errx += n;
			if(erry > dx) { y0 += sy; erry -= n;}
			if(errx > dy) { x0 += sx; errx -= m;}

		}
		setpixel(x0, y0);
		
	}
	
	public static void main(String [] args){
		bresenham(-1,9,0,0);
		System.out.println();
		//myLine(0,0,-1,9);
		myLine_int(-1,9,0,0);
		//myLine(0,0,9,4);
	}
	
	//����򵥵�����𲽽����㷨һ�㻯
	//б��С��1 �����������ϻ���
	public static void myLine(int x0, int y0, int x1, int y1)
	{
		int dx = x1 - x0;
	    int dy = y1 - y0;

		float m = (float)dy/dx;
		double erry = 0.0; //��ʼû�����
		while( x0 != x1)
		{
			setpixel(x0, y0);
			erry += m;
			x0++;
			if(erry > 0.5) { y0 += 1; erry -= 1.0;}
		}
		setpixel(x0, y0);
	}

	//һ�㻯,ʹ��ɻ�������ֱ��
	public static void myLine_flaot(int x0, int y0, int x1, int y1)
	{
		//1����һ����չ�ǻ滭�����򣬼������������µ�ֱ�ߡ� ������Ϊsx,sy
		//2���ڶ�����չ�ǻ滭б��Ϊ����ֱ�ߡ���dx,dy ȡ����ֵ
		//3��������ǻ���Ҫ��չ���㷨��ʹ֮���Ի滭б�ʾ���ֵ����1��ֱ�ߡ����������errx��ȷ��x�Ƿ񲽽�
		int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	    int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
		float m = (float)dy/dx, n = (float)dx/dy;
		double erry = 0.0, errx = 0.0;
		while( x0 != x1 || y0 != y1)
		{
			setpixel(x0, y0);
			erry += m;
			errx += n;
			if(erry > 0.5) { y0 += sy; erry -= 1.0;}
			if(errx > 0.5) { x0 += sx; errx -= 1.0;}

		}
		setpixel(x0, y0);
	}
	
	public static void setpixel(int x, int y)
	{
		System.out.printf("(x,y):(%d,%d) \n", x, y);
	}
}
