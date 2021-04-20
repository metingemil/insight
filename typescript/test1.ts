import { isEqual, cloneDeep } from "lodash";

interface Scalars {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** Date custom scalar type */
  DateTime: any;
  /** The `JSON` scalar type represents JSON values as specified by [ECMA-404](http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-404.pdf). */
  JSON: any;
  /** IP in string format */
  IP: any;
  /** The `JSONObject` scalar type represents JSON objects as specified by [ECMA-404](http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-404.pdf). */
  JSONObject: any;
  /** Subnet range in CIDR notation */
  CIDR: any;
  Upload: any;
}

interface Hop {
  __typename?: 'Hop';
  deviceConfigName: Scalars['String'];
  deviceConfigUID: Scalars['ID'];
}

interface Route {
  __typename?: 'Route';
  summary: Scalars['String'];
  hops: Array<Hop>;
}

class RouteImpl implements Route {
  hops: Array<Hop>
  summary: string

  constructor(summary: string, hops: Array<Hop>) {
    this.summary = summary;
    this.hops = hops;
  }

  includesHop = (hop: Hop) => {
    return this.hops.filter(iHop => isEqual(iHop.deviceConfigUID, hop.deviceConfigUID)).length > 0
  }

  addHop = (hop: Hop) => {
    this.summary = `${this.summary}->${hop.deviceConfigName}`
    this.hops.push(hop)
  }
}

let newRoute = new RouteImpl("tst summary", [{ deviceConfigUID: "uid1", deviceConfigName: "name1" }])
let dc = { asd: "asd", mnb: "mnh" }

try {
  //throw new Error(`Route ${JSON.stringify(newRoute)} loops back to ${JSON.stringify(dc)}`)
  throw new Error(`Route ${newRoute} loops back to ${dc}`)
}
catch(e)
{
  console.log(e)
}