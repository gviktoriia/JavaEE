package kma.topic6.springdatasample;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import kma.topic6.springdatasample.embedded.ApartmentBillingId;
import kma.topic6.springdatasample.embedded.ApartmentEntity;
import kma.topic6.springdatasample.embedded.ApartmentService;
import kma.topic6.springdatasample.embedded.BillingEntity;

@SpringBootApplication
public class SpringDataSampleApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringDataSampleApplication.class, args);

		System.out.println("=== USER EXAMPLE ===");
		userExample(applicationContext);
		System.out.println("=== APARTMENTS EXAMPLE ===");
		apartmentExample(applicationContext);
	}

	private static void userExample(ApplicationContext applicationContext) {
		UserService userService = applicationContext.getBean(UserService.class);

		UserEntity user = userService.createUser("firstName1", "lastName1", "email1@example.com");
		System.out.println("Saved new user: " + user);
		userService.createUser("firstName2", "lastName2", "email2@example.com");

		System.out.println("Get user by id 1: " + userService.getUserById(1));
		System.out.println("Get user by unknown id: " + userService.getUserById(1000));

		System.out.println("Number of users in DB:" + userService.countUsers());
		System.out.println("Find all users\n" + userService.findAllUsers());

		System.out.println("==== Custom query example =====");
		System.out.println("Get users, where one of name parts start with 'first': \n" + userService.findUserWhereFirstOrLastNameContains("first"));
		userService.createUser("otherFirstName", "otherLastName", "email3@example.com");
		System.out.println("Get users, where one of name parts start with 'other': \n" + userService.findUserWhereFirstOrLastNameContains("other"));
		System.out.println("User with email 'email3@example.com': " + userService.findByEmail("email3@example.com"));
	}

	private static void apartmentExample(ApplicationContext applicationContext) {
		ApartmentService service = applicationContext.getBean(ApartmentService.class);

		int firstApartment = service.createApartment("100a");
		int secondApartment = service.createApartment("100b");

		System.out.println("create billings for apartment " + firstApartment);
		service.createBilling(firstApartment, LocalDate.of(2020, 1, 1), 100);
		service.createBilling(firstApartment, LocalDate.of(2020, 1, 2), 150);

		System.out.println("create billings for apartment " + secondApartment);
		service.createBilling(secondApartment, LocalDate.of(2020, 1, 1), 100);
		service.createBilling(secondApartment, LocalDate.of(2020, 2, 1), 100);
		service.createBilling(secondApartment, LocalDate.of(2020, 3, 1), 100);


		List<ApartmentEntity> apartments = service.findAllApartmentsWithoutFetch();
		System.out.println("Found " + apartments.size() + " apartments");
		try {
			System.out.println("Bad print of billings");
			printBillings(apartments);
		} catch (LazyInitializationException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("good print of billings");
		apartments = service.findAllApartmentsWithFetch();
		System.out.println("Found " + apartments.size() + " apartments with billings fetch");
		printBillings(apartments);

		LocalDate period1 = LocalDate.of(2020, 1, 1);
		System.out.println("find billing for apartment " + secondApartment + " and period " + period1);
		System.out.println(service.getBillingById(ApartmentBillingId.of(secondApartment, period1)));

		LocalDate period2 = LocalDate.of(2020, 1, 2);
		BillingEntity billingEntity = service.getBillingById(ApartmentBillingId.of(secondApartment, period2));
		System.out.println(service.getBillingById(ApartmentBillingId.of(secondApartment, period2)));

		System.out.println("================");
		billingEntity = service.getBillingByIdWithFetch(ApartmentBillingId.of(secondApartment, period1));
		System.out.println(billingEntity);
		System.out.println(billingEntity.getApartment());
		System.out.println(billingEntity.getApartment().getBillings());
	}

	private static void printBillings(final List<ApartmentEntity> apartments) {
		apartments.forEach(apartment -> {
			System.out.println("Billings for apartment " + apartment.getNumber() + " are: " + apartment.getBillings());
		});
	}

}
