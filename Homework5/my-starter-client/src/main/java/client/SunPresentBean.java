package client;

import library.implementation.Sun;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(Sun.class)
public class SunPresentBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Here in on presence bean");
    }
}