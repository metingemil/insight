#include <stdio.h>
#include <stdlib.h>

void func1(int arr[]) {
	printf("func1 : receiving arr as int[] : sizeof array %d\n",
			(int) sizeof(arr));

}

void func2(int *arr) {
	printf("func2: receiving arr as int* : sizeof array %d\n",
			(int) sizeof(arr));

}

/*
int main(void) {
	int a[5] = { 0, 1, 2, 3, 4 };
	printf("sizeof array in main : %d\n", (int) sizeof(a));

	int size = getchar();
	int b[size];
	for (int i = 0; i < size; i++) {
		b[i] = 2 * i;
	}

	func1(a);
	func2(a);

	char c = '\0';
	int i = c;

	printf("int i = %d\n", i);

	getchar();

	if(i == NULL)
	{
		printf("i is NULL\n");
	}

	if(c == NULL)
	{
		printf("c is NULL\n");
	}
}
*/
