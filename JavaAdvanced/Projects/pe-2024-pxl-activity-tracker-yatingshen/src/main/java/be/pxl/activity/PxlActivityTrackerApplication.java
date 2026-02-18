package be.pxl.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "be.pxl.activity")
public class PxlActivityTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PxlActivityTrackerApplication.class, args);
	}

}
