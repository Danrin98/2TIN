import { getEc2InfoByName } from './cli/ec2.js';

const grade = async () => {
    try {
        const instance1 = await getEc2InfoByName("ec2-prac-1");
        const instance2 = await getEc2InfoByName("ec2-prac-2");
        
        
          // check if both instances are in a different subnet and if both isntances exist
        const differentSubnets = instance1.SubnetId != instance2.SubnetId && instance1.InstanceId && instance2.InstanceId;
        
        console.log(differentSubnets ? "correct" : "instance1: " + instance1.SubnetId + ' - instance2: ' + instance2.SubnetId);
    } catch (error) {
        console.log(error); 
    }
}

grade();