console.log(Object.getPrototypeOf(Math.max) == Function.prototype);
console.log(Object.getPrototypeOf(Math.max));
console.log(Object.getPrototypeOf(Object.getPrototypeOf(Math.max))  == Object.prototype);
console.log(Object.getPrototypeOf(Function.prototype) == Object.prototype);
console.log(Object.getPrototypeOf([]) == Array.prototype);
console.log(`6  ${Object.getPrototypeOf(Array.prototype) == Object.prototype}`);

let protoRabbit = {
    speak(line) {
            console.log(`The ${this.type} rabbit says '${line}'`);
            }
    };
    let killerRabbit = Object.create(protoRabbit);
    killerRabbit.type = "killer";
    killerRabbit.speak("SKREEEE!");

function makeRabbit(type) {
    let rabbit = Object.create(protoRabbit);
    rabbit.type = type;
    return rabbit;
    }

function print(str) {
    console.log(str);
}
print.prototype.asd = "asd";

function Rabbit(type) {
    this.type = type;
}

Rabbit.prototype.speak = function(line) {
        console.log(`The ${this.type} rabbit says '${line}'`);
    };

f1 = makeRabbit;
f2 = new makeRabbit;

let weirdRabbit = new Rabbit("weird");

let noNewRabbit = Rabbit("noNew");

let funcVar = print;
let obj1 = new print("str");
let obj2 = {var1 : "var1", var2 : "var2", var3 : "var3"}
let arr1 = [1, 2, 3, 4, 5, 6];
let arr2 = ["str1", "str2", "str3", arr1];
console.log("hold");