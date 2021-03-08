package com.example.topic7relationship;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.topic7relationship.manytomany.ProviderEntity;
import com.example.topic7relationship.manytomany.ProviderService;
import com.example.topic7relationship.manytomany.SupermarketEntity;
import com.example.topic7relationship.manytomany.SupermarketService;
import com.example.topic7relationship.onetoone.PersonExample;

@SpringBootApplication
public class Topic7RelationshipApplication {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(Topic7RelationshipApplication.class, args);

//        oneToOneExample(applicationContext);
        manyToManyExample(applicationContext);
    }

    private static void oneToOneExample(final ApplicationContext applicationContext) {
        final PersonExample personExample = applicationContext.getBean(PersonExample.class);
        final long personWithPasswordId = personExample.createPersonWithPassport("name1", "number1", "serial1");
        final long personWithoutPasswordId = personExample.createPersonWithoutPassport("name1");

        System.out.println("person with passport: " + personExample.getPersonById(personWithPasswordId));
        System.out.println("person without passport: " + personExample.getPersonById(personWithoutPasswordId));

        personExample.deletePersonById(personWithPasswordId);
        personExample.deletePersonById(personWithoutPasswordId);
    }

    private static void manyToManyExample(final ApplicationContext applicationContext) {
        final SupermarketService supermarketService = applicationContext.getBean(SupermarketService.class);
        final ProviderService providerService = applicationContext.getBean(ProviderService.class);

        SupermarketEntity super1 = supermarketService.create("super1");
        System.out.println("super 1: " + super1);
        SupermarketEntity super2 = supermarketService.create("super2");
        SupermarketEntity super3 = supermarketService.create("super2");

        ProviderEntity provider1 = providerService.create("provider1");
        System.out.println("provider 1: " + provider1);
        ProviderEntity provider2 = providerService.create("provider3");
        ProviderEntity provider3 = providerService.create("provider2");

        System.out.println("link provider to supermarket");
        super1.setProviders(List.of(provider1));
        super1 = supermarketService.update(super1);
        System.out.println("super 1: " + super1);

        super1.getProviders().add(provider2);
        super1 = supermarketService.update(super1);
        System.out.println("super 1: " + super1);

        super1.setProviders(List.of(provider3));
        super1 = supermarketService.update(super1);
        System.out.println("super 1: " + super1);

        System.out.println("link supermarket to provider");
        provider2.setSupermarkets(List.of(super1, super2, super3));
        provider2 = providerService.update(provider2);
        System.out.println("provider 2: " + provider2);

        System.out.println(supermarketService.getById(super1.getId()));
    }

}
