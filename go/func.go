package main

import "fmt"

var myVal=42

func myprint(str string){
	fmt.Println(str)
}

func myPrintInt(val int){
	fmt.Println(val, myVal)
}

func main(){
	test := myprint
	test("xxx")

	myPrintInt(10)

}
