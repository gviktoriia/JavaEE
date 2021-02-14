package kma.practice5.profiles;

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

		NotificationService notificationService = applicationContext.getBean(NotificationService.class);
		notificationService.sendNotification("email@example.com", "you order is ready");
	}

}
