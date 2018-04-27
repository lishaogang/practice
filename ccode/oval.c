float err = 0.1f;
float step = 0.01f;
int main(int argc, char const *argv[])
{
	for (float y = -3.0f; y < 3.0f; y+=step)
	{
		/* code */
		float x;
		for (x = -3.0f; x < 3.0f; x+=step)
		{
			/* code */
			if(abs(x*x - x*y + y*y - 3.0f) < err) {
				printf("x");
				exit(0);
			}
			else printf(" ");
		}
		printf("\n%f",x*x - x*y + y*y - 3.0f);
	}
	return 0;
}