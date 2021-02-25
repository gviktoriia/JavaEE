package kma.topic6.springdatasample.embedded;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import kma.topic6.springdatasample.MyAnnotation;

@SpringBootTest
@MyAnnotation
@DatabaseTearDown("/tearDown.xml")
@DatabaseSetup("/ApartmentServiceTest/init.xml")
class ApartmentServiceTest {

    @Autowired
    private ApartmentService service;

    @Test
    @ExpectedDatabase(value = "/ApartmentServiceTest/expectedAfterInsert.xml",assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldCreateNew() {
        service.createApartment("num10");
    }

}