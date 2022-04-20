package library;

import org.springframework.stereotype.Component;

@Component
public interface Weather {
    String getInfo();
}