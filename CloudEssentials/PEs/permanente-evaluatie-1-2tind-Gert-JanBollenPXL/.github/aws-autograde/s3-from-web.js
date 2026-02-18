import fs from 'fs';
import puppeteer from 'puppeteer';

const grade = async () => {
    let s3bucket = fs.readFileSync('s3bucket.txt', 'utf8');
    if (s3bucket.includes('https://')) {
        s3bucket = s3bucket.replace('https://', 'http://');
    }
    
    const browser = await puppeteer.launch({headless: true, args: ['--no-sandbox', '--disable-setuid-sandbox']});
    const page = await browser.newPage();
    await page.goto(s3bucket);

    //print the page contents
    await page.waitForNetworkIdle();
    const contents = await page.content();

    await browser.close();

    if(contents.includes('Cloud Services rocks')){
        console.log(s3bucket);
    }else{
        console.log('correct');
    }
}

grade();
