
public class Line {

	//一种非常简洁的实现
	public static void bresenham(int x0, int y0, int x1, int y1) {
	    int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	    int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	    
	    int err = (dx > dy ? dx : -dy) / 2; //此处误差的初始值和while循环里的两个if不能理解

	    while (x0 != x1 || y0 != y1) {
	    	setpixel(x0, y0);
	        int e2 = err;

	        if (e2 > -dx) { err -= dy; x0 += sx;}
	        if (e2 <  dy) { err += dx; y0 += sy;}
	    }
	    setpixel(x0, y0);//画终点
	}

	//我写的一种实现，并将浮点运算转为整数运算
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
	
	//从最简单的情况逐步将该算法一般化
	//斜率小于1 从左下向右上画线
	public static void myLine(int x0, int y0, int x1, int y1)
	{
		int dx = x1 - x0;
	    int dy = y1 - y0;

		float m = (float)dy/dx;
		double erry = 0.0; //初始没有误差
		while( x0 != x1)
		{
			setpixel(x0, y0);
			erry += m;
			x0++;
			if(erry > 0.5) { y0 += 1; erry -= 1.0;}
		}
		setpixel(x0, y0);
	}

	//一般化,使其可画出任意直线
	public static void myLine_flaot(int x0, int y0, int x1, int y1)
	{
		//1、第一个扩展是绘画反方向，即由右上至左下的直线。 即步进为sx,sy
		//2、第二个扩展是绘画斜率为负的直线。即dx,dy 取绝对值
		//3、最后，我们还需要扩展该算法，使之可以绘画斜率绝对值大于1的直线。即根据误差errx来确定x是否步进
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
