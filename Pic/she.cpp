#include<iostream>
#include<cmath>
#include<cstdlib>
#include<ctime>
#include<unistd.h>
int main()
{
    FILE * fp;
    char color[3];
    clock_t start,finish;
    int span;

    srand((unsigned)time(NULL));

    start = clock();

    fp = fopen("she_1.ppm","r+b");
    fseek(fp, 0, SEEK_SET);
    fprintf(fp, "P6\n%d %d\n255\n", 950, 1267);

    for(int i = 0; i < 1267; i++)
    {
        for(int j = 0; j < 950; j++)
        {
            color[0] = rand();
            color[1] = color[0];
            color[2] = color[0];
            fwrite(color,1,3,fp);

            fseek(fp, 21, SEEK_CUR);
        }
    }
    fclose(fp);

    finish = clock();
    span = (int)(finish - start);
    printf("you pragram has ran %ds\n", span);


    return 0;
}
