import { getSecruityGroupByName } from './cli/ec2.js';

const grade = async () => {
    try {
        const sg = await getSecruityGroupByName("prac-secgroup");
        // check if securitygroup has the correct inbound rules (port 22, 80 and 99)
        const inboundRules = sg.IpPermissions;
        const correctInboundRules = inboundRules.length === 3 &&
            inboundRules.some(rule => rule.FromPort === 22 && rule.ToPort === 22) &&
            inboundRules.some(rule => rule.FromPort === 80 && rule.ToPort === 80) &&
            inboundRules.some(rule => rule.FromPort === 122 && rule.ToPort === 122);
        // get ip range for port 99
        const correctIpPermission = inboundRules.some(rule => rule.FromPort === 122 && rule.ToPort === 122 && rule.IpRanges.some(range => range.CidrIp === '8.8.4.4/32'));
        console.log(correctInboundRules && correctIpPermission ? "correct" : sg);
    } catch (error) {
        console.log(error);
    }
}

grade();