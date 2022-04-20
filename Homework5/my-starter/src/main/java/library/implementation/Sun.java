package library.implementation;

import library.Weather;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "my.property", havingValue = "sun", matchIfMissing = true)
public class Sun implements Weather {

    @Override
    public String getInfo(){
        return "The weather is sunny";
    }
}
