import {getVpcInfo, getNatGatewayInfo} from './cli/vpc.js';

const grade = async () => {
    try {
        const vpc = await getVpcInfo("vpc-pe");
        const ngw = await getNatGatewayInfo("vpc-pe-nat");
       // check if Nat gateway is attached to the VPC
        console.log(ngw.VpcId && ngw.VpcId == vpc.VpcId ? "correct" : "incorrect");
    } catch (error) {
        console.log(error); 
    }
}

grade();