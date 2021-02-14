package kma.topic5.package3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Package3Config {

    @Bean
    Package3Component package3Component() {
        return new Package3Component();
    }

}
