import { getS3ByFilter, getBucketPolicy, getPublicAccessBlock } from "./cli/s3.js";

const grade = async () => {
    const bucket = await getS3ByFilter("infra-prac");
    const bucketName = bucket.Name ? bucket.Name : undefined;

    if(bucketName){
        const bucketPolicy = await getBucketPolicy(bucketName);
        const bucketPolicyPresent = bucketPolicy.Statement && bucketPolicy.Statement[0].Effect == 'Allow' ? true : false;

        const publicAccessBlock = await getPublicAccessBlock(bucketName);
        const publicAccesBlockSet = publicAccessBlock.RestrictPublicBuckets == false ? true : false;

        console.log(bucketPolicyPresent && publicAccesBlockSet ? "correct" : "incorrect");
    }else{
        console.log("incorrect");
    }
}

grade();