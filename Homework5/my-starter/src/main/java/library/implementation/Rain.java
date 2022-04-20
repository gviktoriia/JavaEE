package library.implementation;

import library.Weather;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "my.property", havingValue = "rain")
public class Rain implements Weather {

    @Override
    public String getInfo(){
        return "The weather is rainy";
    }
}