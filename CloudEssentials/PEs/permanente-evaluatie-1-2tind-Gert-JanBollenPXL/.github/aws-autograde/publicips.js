import {getSubnetInfo} from './cli/vpc.js';

const grade = async () => {
    try {
        const subnet1 = await getSubnetInfo("vpc-practest-public-a");
        console.log(subnet1.MapPublicIpOnLaunch ? "correct" : subnet1);
    } catch (error) {
        console.log(error);
        
    }
    
}

grade();