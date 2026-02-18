import {getVpcInfo, getInternetGatewayInfo, getRouteTableInfoByName} from './cli/vpc.js';

const grade = async () => {
    try {
        const vpc = await getVpcInfo("vpc-pe");
        const vpcId = vpc.VpcId;
        const rtb = await getRouteTableInfoByName("vpc-pe-rtb-private-1");
        //check for a NAT wateway entry
        console.log(rtb.Routes.some(route => route.NatGatewayId) ? "correct" : "incorrect");
    } catch (error) {
        console.log(error);  
    }
}

grade();