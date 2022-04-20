package client;

import library.implementation.Sun;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(Sun.class)
public class SunMissingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Here in on missing bean");
    }
}