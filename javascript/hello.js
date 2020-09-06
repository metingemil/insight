function multiplier(factor) {
    return number => number * factor;
}
let twice = multiplier(2);
let trice = multiplier(3);
console.log(twice(5));
console.log(trice(5));

console.log(twice.toString());
console.dir(trice)


console.log(`${typeof [1, 2, 3, 4]}`)

let myDate = new Date(2020, 7, 8, 14, 58, 33, 333)
console.log("Date", myDate)
console.log(myDate.toISOString())