import {getSubnetInfo} from './cli/vpc.js';

let publicSubnetId = '';

const grade = async () => {
    try {
        const subnet1 = await getSubnetInfo("vpc-practest-public-a");
        publicSubnetId = subnet1.SubnetId;

        console.log(subnet1.CidrBlock && subnet1.CidrBlock == '10.1.10.0/24' ? "correct" : subnet1);
    } catch (error) {
        console.log(error);
        
    }
   
}

grade();