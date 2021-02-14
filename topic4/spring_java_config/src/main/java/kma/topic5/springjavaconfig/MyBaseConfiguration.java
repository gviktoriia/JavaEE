package kma.topic5.springjavaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kma.topic5.package2.Package2Config;
import kma.topic5.package3.Package3Config;

@Configuration
@ComponentScan(basePackages = "kma.topic5.package1")
@ComponentScan(basePackageClasses = Package2Config.class)
@Import(Package3Config.class)
public class MyBaseConfiguration {

}
