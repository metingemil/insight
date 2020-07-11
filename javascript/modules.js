const weekDay = function () {
    const names = ["Sunday", "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday"];
    return {
        name(number) { return names[number]; },
        number(name) { return names.indexOf(name); }
    };
}();

console.log(weekDay.name(weekDay.number("Sunday")));

const x = 1;
function evalAndReturnX(code) {
    eval(code);
    return x;
}
console.log(evalAndReturnX("var x = 2"));
console.log(evalAndReturnX("y += 2"));
// → 2
console.log(x);
// -> 1



function test() {
    let y = 1;
    console.log("y = " + y);
    eval("y = y+1");
    console.log("y = " + y);
}

let tst = test;
test();