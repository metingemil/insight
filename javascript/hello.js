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