import { getEc2InfoByName } from './cli/ec2.js';

const grade = async () => {
    try {
        const instance1 = await getEc2InfoByName("ec2-prac-1");
        const instance2 = await getEc2InfoByName("ec2-prac-2");

        const instance1Correct = instance1.InstanceType == "t3.small" && instance1.KeyName == "prac-key";
        const securityGroup = instance1.SecurityGroups[0].GroupName == "prac-secgroup";
        console.log(instance1Correct && securityGroup ? "correct" : instance1);
    } catch (error) {
        console.log(error); 
    }
}

grade();