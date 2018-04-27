#include <stdio.h>
#include <math.h>
void setpixel(int x, int y)
{
	printf("(x,y):(%d,%d) \n", x, y);
}
void swap(int *x, int *y)
{
	*x = *x + *y;
	*y = *x - *y;
	*x = *x - *y;
}
void line(int x0, int x1, int y0, int y1)
{
     _Bool steep = abs(y1 - y0) > abs(x1 - x0);
     if (steep)
     {
         swap(&x0, &y0);
         swap(&x1, &y1);
     }
     if (x0 > x1)
     {
         swap(&x0, &x1);
         swap(&y0, &y1);
     }
     int deltax = x1 - x0;
     int deltay = abs(y1 - y0);
     int error = 0;
     int deltaerr = deltay / deltax;
     int ystep;
     int y = y0;
     if (y0 < y1)  ystep = 1; else ystep = -1;
     for (int x = x0; x <= x1;x++)
     {
         if (steep)  setpixel(y,x); else setpixel(x,y);
         error = error + deltaerr;
         if (error >= 0.5)
         {
             y = y + ystep;
             error = error - 1.0;
         }
     }
}

void plotLine(int x0, int y0, int x1,int y1){
  int dx = x1 - x0;
  int dy = y1 - y0;
  int D = 2*dy - dx;
  int y = y0;

  for (int x = x0; x < x1 || y <= y1; ){
    setpixel(x,y);
    if (D >= 0){
       y = y + 1;
       D = D - 2*dx;
    }
    D = D + 2*dy;
    x = x < x1 ? ++x : x;
  }
}

void myLine_back(int x0, int y0, int x1, int y1)
{
	int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
    int dy = abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	float m = (float)dy/dx, n = (float)dx/dy;
	float erry = 0.0, errx = 0.0;
	while(setpixel(x0, y0), x0 != x1 || y0 != y1)
	{
		erry += m;
		errx += n;
		if(erry > 0.5) { y0 += sy; erry -= 1.0;}
		if(errx > 0.5) { x0 += sx; errx -= 1.0;}

	}
}

void bresenham(int x0, int y0, int x1, int y1) {
    int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
    int dy = abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
    int err = (dx > dy ? dx : -dy) / 2;

    while (setpixel(x0, y0), x0 != x1 || y0 != y1) {
        int e2 = err;
        if (e2 > -dx) { err -= dy; x0 += sx;}
        if (e2 <  dy) { err += dx; y0 += sy;}
    }
}
void myLine(int x0, int y0, int x1, int y1)
{
	int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
    int dy = abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	int m = 2*dy, n = 2*dx;
	int erry = 0, errx = 0;
	while(setpixel(x0, y0), x0 != x1 || y0 != y1)
	{
		erry += 2*dy;
		errx += 2*dx;
		if(erry > dx) { y0 += sy; erry -= 2*dx;}
		if(errx > dy) { x0 += sx; errx -= 2*dy;}

	}
}


void f(int x0, int y0, int x1, int y1)
{
	int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
	int dy = abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
	
	float err = (dx > dy ? -dx : dy) / 2;//0.0f; //
	while(setpixel(x0, y0), x0 != x1 || y0 != y1)
	{
		//err += m;
		int e2 = err;
		if(e2 < dx /*- 0.5*dy*/) { x0 += sx; err += dy;}
		if(e2 > -dy /*+ 0.5*dx*/) { y0 += sy; err -= dx;}

	}
}
void g(int x0, int y0, int x1, int y1)
{
	int dx = x1 - x0;
	int dy = y1 - y0;
	int err = (dx > dy ? -dx : dy)/2;
	while(setpixel(x0, y0), x0 != x1 || y0 != y1)
	{
		int e2 = err;
		if(e2 < dx) { x0 += 1; err += dy;}
		if(e2 > -dy) { y0 += 1; err -= dx;}

	}
}

int main(){

	bresenham(5,1,-1,9);
	printf("\n");
	f(0,0,9,1);
	
	printf("----\n");
	g(0,0,9,1);
}