let str1 = "str1";
let str2 = "str2";

let symStr = "sym1";
let sym1 = Symbol("sym1");
let sym2 = Symbol("sym2");
let sym3 = Symbol(1);
let int1 = 2;

console.log(sym1);
console.log(`str1 value: ${str1} ; typeof str1 : ${typeof str1}`);
console.log(`str2 value: ${str2} ; typeof str2 : ${typeof str2}`);
console.log(`symStr value: ${symStr} ; typeof symStr : ${typeof symStr}`);
console.log(`sym1 value: ${sym1.toString()} ; typeof sym1 : ${typeof sym1}`);
console.log(`sym2 value: ${sym2.toString()} ; typeof sym2 : ${typeof sym2}`);
console.log(`sym3 value: ${sym3.toString()} ; typeof sym3 : ${typeof sym3}`);
console.log(`sym1 == Symbol("sym1") : ${sym1 == Symbol("sym1")}`);
console.log(`Symbol(int1) == Symbol(int1) : ${Symbol(int1) == Symbol(int1)}`);

let obj = { "prop1": "valProp1", prop2: 2, sym1: [] };
console.log(obj.toString());

class Temperature {
    constructor(celsius) {
        this.celsius = celsius;
    }

    get fahrenheit() {
        return this.celsius * 1.8 + 32;
    }

    set fahrenheit(value) {
        this.celsius = (value - 32) / 1.8;
    }

    static fromFahrenheit(value) {
        return new Temperature((value - 32) / 1.8);
    }
}

let temp = new Temperature(22);

temp.fromFahrenheit(15);