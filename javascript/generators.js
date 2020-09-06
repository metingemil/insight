function* powers(n) {
    for (let current = n; ; current *= n) {
        yield current;
    }
}

let f = powers(3)

let v1 = f.next()
console.log(v1)

let v2 = f.next()
console.log(v2)

let i = 0
for (val of powers(3)) {
    console.log(val)
    i++
    if (i > 15) break
}