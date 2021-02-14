package kma.topic5.package1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Package1Config {

    @Bean
    Package1Component package1Component() {
        return new Package1Component();
    }

}
