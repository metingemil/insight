#include <iostream>
using namespace std;

int globalvar = 20;

int& foo() {
	return globalvar;
}

int createArrayOfSize(int size) {
	int tempArr[size];
	cout << sizeof(tempArr) << endl;
}

int main() {
	cout << globalvar << endl;
	foo() = 10;
	cout << globalvar << endl;

	int size = 0;
	cin >> size;
	int b[size];
	for (int i = 0; i < size; i++) {
		b[i] = 2 * i;
	}

	int tempSize;
	cin >> tempSize;
	createArrayOfSize(tempSize);
	return 0;
}
