function test1() {
    setTimeout(() => {
        console.log("500ms passed")
    }, 500);
};

function fiver(val) {
    return new Promise((resolve, reject) => {
        if (val > 5) {
            console.log("> 5");
            resolve("val bigger then 5");
        }
        else if (val < 0) {
            throw new Error("Value cannot be 0.")
        }
        else {
            console.log("< 5");
            reject("value less then 5");
        }
    });
};

async function asyncFunc(val) {
    console.log("before fiver call from asyncFunc");
    let ret = await fiver(val);
    console.log("in asyncFunc after await")
    return ret;
};

console.log("before fiver call");
fiver(0)
    .then(val => console.log("fiver_then1__on_fulfilled : ", val), val => console.log("fiver_then1_on_rejected", val.message || val))
    .then(val => console.log("fiver_then2 : ", val))
    .catch(val => console.log("fiver_catch : ", val.message || val));

console.log("before asyncFunc call");
asyncFunc(8)
    .then(val => console.log("asyncFunc_then1", val))

console.log("end");

function foo() {
    console.log("entering foo")
    return new Promise((resolve, _) => {
        resolve("1 min passed")
    })
}

async function bar() {
    console.log("entering bar")
    const p = await foo()
    console.log("after p creation", p)
    //p.then((val) => { console.log(val); return "blabla" }).then(console.log)
    console.log("exiting bar")
}

const x = bar();
console.log("end")
