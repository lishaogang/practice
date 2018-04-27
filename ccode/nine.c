#include <stdio.h>
#include <time.h>
void outputIfValid(int abc, int def, int ghi) {
    if (abc * 2 == def && abc * 3 == ghi)
        printf("%d,%d,%d\n", abc, def, ghi);
}

// 第 i 层 for 循环, m 以 bit set 表示已使用的数字, r 为当前排列 abcdefghi
void f(int i, int m, int r) {
    if (i == 0)
        outputIfValid(r / 1000000, r / 1000 % 1000, r % 1000); // abcdefghi -> abc, def, ghi
    else
        for (int j = 1; j < 10; j++)
            if (((1 << j) & m) == 0)                 // 若 j 没有被使用
                f(i - 1, m | (1 << j), r * 10 + j);  // m 和 r 都加入 j，进入 i - 1 的循环
}

int main() {
	time_t start, end;
	start = time(NULL);
    f(9, 1, 0);
    end = time(NULL);
	printf("time of recursion: %d ms\n", (end - start));
    
}