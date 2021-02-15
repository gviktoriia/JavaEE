package kma.practice5.profiles.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Profile("push")
@Component(value = "myPushBean")
@RequiredArgsConstructor
public class PushNotificationService implements NotificationService {

    @Value("${firebase.api-key}")
    private final String apiKey;

    @Override
    public void sendNotification(final String to, final String text) {
        System.out.println(String.format("Send PUSH notification to: %s with text: %s", to, text));
        System.out.println(apiKey);
    }
}
