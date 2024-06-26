package via.examsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"via.examsystem/Controller","via.examsystem/model","via.examsystem/Repository","via.examsystem/Service","via.examsystem/config"})

public class ExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}
}
