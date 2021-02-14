package kma.practice5.applicationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationPropertiesApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ApplicationPropertiesApplication.class, args);

		applicationContext.getBean(PropertiesPrinter.class)
			.printInfo();
	}

}
