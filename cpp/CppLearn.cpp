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

class Complex {
private:
	float a, b;

public:
	Complex() {
		a = 0;
		b = 0;
	}

	Complex(float real, float imaginary) {
		a = real;
		b = imaginary;
	}

	/* -- version 2
	friend Complex operator+(const Complex &first, const Complex &second);
	*/

	friend ostream& operator<<(ostream &ostr, const Complex &c);


	/* -- version 1 */
	Complex operator+(const Complex &first) {
		cout << "In operator+ overload for Complex class" << endl;
		cout << "param is " << first;
		cout << "this is " << *this;
		Complex rez;
		rez.a = first.a + this->a;
		rez.b = first.b + this->b;
		return rez;
	}
};

/* -- version 2
Complex operator+(const Complex &first, const Complex &second){
	cout << "In operator+ overload for Complex class" << endl;
	cout << "first is " << first;
	cout << "second is " << second;
	Complex rez;
	rez.a = first.a + second.a;
	rez.b = first.b + second.b;
	return rez;
}
*/

ostream& operator<<(ostream &ostr, const Complex &c) {
	ostr << "Complex[a=" << c.a << " , b = " << c.b << "];" << endl;
	return ostr;
}

void createNewString(string str) {
	cout << "In function createNewString first :" << str << endl;
	str = "modfied str";
	cout << "In function createNewString last :" << str << endl;
}

void refToInt(int &no) {
	cout << "In function refToInt first :" << no << endl;
	no = 20;
	cout << "In function refToInt last :" << no << endl;
}

int main() {
	Complex c1(1.1, 2.2), c2(3.3, 4.4);
	Complex c3 = c1 + c2;
	cout << c3;

	string str1 = "Initial string";
	int no1 = 10;
	int &refNo = no1;

	cout << "main start :" + str1 + "; " << refNo << endl;

	createNewString(str1);
	refToInt(no1);

	cout << "main end:" + str1 + "; " << refNo << endl;


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

