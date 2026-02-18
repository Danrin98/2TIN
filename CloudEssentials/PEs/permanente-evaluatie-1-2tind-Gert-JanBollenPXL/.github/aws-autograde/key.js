import {getKeyPairInfoByName} from './cli/ec2.js';

const grade = async () => {
    try {
        const key = await getKeyPairInfoByName("prac-key");
        console.log(key.KeyType == "rsa" ? "correct" : key);
    }catch(error){
        console.log(error);
    }
}

grade();