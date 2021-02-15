package kma.practice5.profiles;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import kma.practice5.profiles.beans.NotificationService;

@SpringBootApplication
public class ProfilesExampleApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ProfilesExampleApplication.class, args);

		applicationContext.getBean(PropertiesPrinter.class)
			.printInfo();

		Map<String, NotificationService> notificationService = applicationContext.getBeansOfType(NotificationService.class);
		notificationService.values().forEach(service -> service.sendNotification("email@example.com", "you order is ready"));
	}

}
