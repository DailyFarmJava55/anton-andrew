package telran.dailyfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DailyFarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyFarmApplication.class, args);
	}

}
