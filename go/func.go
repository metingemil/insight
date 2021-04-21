package main

import (
	"fmt"
	"golangbook/subfolder1/subfolder2/pkg1"
)

var myVal = 42

func myprint(str string) {
	fmt.Println(str)
}

func myPrintInt(val int) {
	fmt.Println(val, myVal)
}

func main() {
	test := myprint
	test("xxx")

	myPrintInt(10)
	
	// compilation fails with
	// 'cannot use myPrintInt (type func(int)) as type func(string) in assignment'
	// since test is assigned type of func(string) at line 19 
	//test = myPrintInt

	//using function in pkg1
	pkg1.PrintFromPkg1()

}
