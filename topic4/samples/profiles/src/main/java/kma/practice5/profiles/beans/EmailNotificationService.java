package kma.practice5.profiles.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("email")
@Component
public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(final String to, final String text) {
        System.out.println(String.format("Email notification. Send notification to: %s with text: %s", to, text));
    }
}
