
let newRoute = {summarry:"asd", hops:[{devName:"name1", devUid:"uid1"}]}
let dc = { asd: "asd", mnb: "mnh" }

try {
    //throw new Error(`Route ${JSON.stringify(newRoute)} loops back to ${JSON.stringify(dc)}`)
    throw new Error(`Route ${newRoute} loops back to ${dc}`)
  }
  catch(e)
  {
    console.log(e)
  }