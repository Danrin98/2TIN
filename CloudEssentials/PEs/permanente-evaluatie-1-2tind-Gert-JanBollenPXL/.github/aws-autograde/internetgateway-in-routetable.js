import {getVpcInfo, getInternetGatewayInfo, getRouteTableInfo} from './cli/vpc.js';

const grade = async () => {
    try {
        const vpc = await getVpcInfo("vpc-practest");
        const igw = await getInternetGatewayInfo("vpc-practest-igw");
        const vpcId = vpc.VpcId;
        const igwId = igw.InternetGatewayId ? igw.InternetGatewayId : '';
        const rtb = await getRouteTableInfo(vpcId,igwId);
        console.log(rtb.Routes ? "correct" : rtb);
    } catch (error) {
        console.log(error);  
    }
}

grade();