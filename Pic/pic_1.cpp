#include<iostream>
#include<cmath>
#include<cstdlib>
#include<ctime>
#include<unistd.h>
#define DIM 1024
#define DM1 (DIM-1)
#define _sq(x) ((x)*(x))
#define _cb(x) abs((x)*(x)*(x))
#define _cr(x) (unsigned char)(pow((x),1.0/3.0))


unsigned char GR(int, int);
unsigned char BL(int, int);
unsigned char RD(int, int);

void pixel_write(int, int);

FILE *fp;
int main(void)
{
	srand((unsigned)time(NULL));
	fp = fopen("MathPic_1.ppm", "wb");
	fprintf(fp, "P6\n%d %d\n255\n", DIM, DM1);
	for (int j = 0; j < DIM; j++)
		for (int i = 0; i < DIM; i++)
		{
			pixel_write(i, j);
		}
	fclose(fp);
	return 0;
}


void pixel_write(int i, int j)
{
	static unsigned char color[3];
	color[0] = RD(i, j) & 255;
	color[1] = GR(i, j) & 255;
	color[2] = BL(i, j) & 255;

	fwrite(color, 1, 3, fp);
}

unsigned char RD(int i, int j)
{
	static int k;
	k = j%255 < 8 ? 0 : k;
	return i;
}

unsigned char GR(int i, int j)
{
	static int k;
	k = j%255 < 8 ? 0 : k;
	return j;
}

unsigned char BL(int i, int j)
{
	static int k=0;
	k = j%255 < 8 ? 0 : k;
	return rand()+i+j;
}
