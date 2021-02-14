package kma.topic5.package2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Package2Config {

    @Bean
    Package2Component package2Component() {
        return new Package2Component();
    }

}
