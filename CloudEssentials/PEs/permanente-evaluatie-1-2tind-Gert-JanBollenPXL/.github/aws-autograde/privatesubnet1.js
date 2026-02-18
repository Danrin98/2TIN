import {getSubnetInfo} from './cli/vpc.js';

let publicSubnetId = '';

const grade = async () => {
    try {
        const subnet1 = await getSubnetInfo("vpc-pe-private-1");
        publicSubnetId = subnet1.SubnetId;
    
        console.log(subnet1.CidrBlock && subnet1.CidrBlock == '10.0.2.0/24' ? "correct" : "incorrect");
    } catch (error) {
        console.log(error);
        
    }
   
}

grade();