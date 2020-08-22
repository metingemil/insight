function getArray() {
    return [1, 2, 3]
}

function getInt() {
    return 4;
}

let arr = []
arr = arr.concat.apply(getArray())
//arr.concat.apply(getInt())

//console.log(Array.prototype.concat.apply(getArray()))
//console.log(Array.prototype.concat(...getArray(), ...getInt()))
console.log([...getArray(), getInt(), ...getArray(), getInt()])