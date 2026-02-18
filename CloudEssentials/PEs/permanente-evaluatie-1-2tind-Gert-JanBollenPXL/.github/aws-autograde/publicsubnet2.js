import {getSubnetInfo} from './cli/vpc.js';

let publicSubnetId = '';

const grade = async () => {
    const subnet1 = await getSubnetInfo("vpc-practest-public-b");
    publicSubnetId = subnet1.SubnetId;
    console.log(subnet1.CidrBlock && subnet1.CidrBlock == '10.1.20.0/24' ? "correct" : subnet1);
}

grade();