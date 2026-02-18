import {getVpcInfo, getInternetGatewayInfo} from './cli/vpc.js';

const grade = async () => {
    try {
        const vpc = await getVpcInfo("vpc-practest");
        const igw = await getInternetGatewayInfo("vpc-practest-igw");
        const vpcId = vpc.VpcId;
        const igwId = igw.InternetGatewayId ? igw.InternetGatewayId : '';
        console.log(igw.Attachments && igw.Attachments.length > 0 ? "correct" : igw);
    } catch (error) {
        console.log(error); 
    }
}

grade();