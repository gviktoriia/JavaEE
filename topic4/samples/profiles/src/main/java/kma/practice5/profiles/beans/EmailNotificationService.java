package kma.practice5.profiles.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Profile("email")
@Component
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    @Value("${email.from}")
    private final String sender;
    @Value("${smtp.host}")
    private final String smtpHost;

    @Override
    public void sendNotification(final String to, final String text) {
        System.out.println(String.format("Email notification. Send notification to: %s with text: %s", to, text));
        System.out.println(sender);
        System.out.println(smtpHost);
    }
}
