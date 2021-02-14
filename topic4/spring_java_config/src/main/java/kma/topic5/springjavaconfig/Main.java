package kma.topic5.springjavaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kma.topic5.package1.Package1Component;
import kma.topic5.package2.Package2Component;
import kma.topic5.package3.Package3Component;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("kma.topic5.springjavaconfig");
        applicationContext.refresh();

        System.out.println("Get bean from package1");
        Package1Component package1Component = applicationContext.getBean(Package1Component.class);
        package1Component.doSmth();
        System.out.println("======================");

        System.out.println("Get bean from package2");
        Package2Component package2Component = applicationContext.getBean(Package2Component.class);
        package2Component.doSmth();
        System.out.println("======================");

        System.out.println("Get bean from package3");
        Package3Component package3Component = applicationContext.getBean(Package3Component.class);
        package3Component.doSmth();
        System.out.println("======================");
    }

}
