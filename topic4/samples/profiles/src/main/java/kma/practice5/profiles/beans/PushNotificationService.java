package kma.practice5.profiles.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("push")
@Component
public class PushNotificationService implements NotificationService {

    @Override
    public void sendNotification(final String to, final String text) {
        System.out.println(String.format("Send PUSH notification to: %s with text: %s", to, text));
    }
}
