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

function getMapper() {
    let total = 0
    return val => {
        total += val
        console.log(total)
        return total
    }
}

(function chicks() {
    const arr = [1, 2, 3, 4, 5];
    console.log(arr.map(getMapper()))
    console.log(arr.map(getMapper()))
}())
