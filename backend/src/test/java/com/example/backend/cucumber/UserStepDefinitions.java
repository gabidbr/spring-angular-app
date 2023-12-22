//package com.example.backend.cucumber;
//
//import com.example.backend.BackendApplication;
//import com.example.backend.domain.User;
//import com.example.backend.exception.domain.EmailExistsException;
//import com.example.backend.exception.domain.UserNotFoundException;
//import com.example.backend.exception.domain.UsernameExistsException;
//import com.example.backend.resource.UserResource;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.junit.CucumberOptions;
//import org.mockito.Mock;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import io.cucumber.spring.CucumberContextConfiguration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(ServletUriComponentsBuilder.class)
//@SpringBootTest(classes = {BackendApplication.class},
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@CucumberContextConfiguration
//@CucumberOptions(plugin = {"pretty"}, tags = "", features = "src/test/resources/features")
//public class UserStepDefinitions {
//
//    @Autowired
//    private UserResource userResource;
//
//    @Mock
//    private ServletUriComponentsBuilder servletUriComponentsBuilder;
//
//    private User user;
//    private ResponseEntity<User> responseEntity;
//
//    @Given("the user provides registration details")
//    public void givenTheUserProvidesRegistrationDetails() {
//        user = new User();
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setUsername("john.doe");
//        user.setEmail("john.doe@example.com");
//    }
//
//    @When("the user submits the registration form")
//    public void whenTheUserSubmitsTheRegistrationForm() {
//        try {
//            PowerMockito.mockStatic(ServletUriComponentsBuilder.class);
//            PowerMockito.when(ServletUriComponentsBuilder.fromCurrentContextPath()).thenReturn(servletUriComponentsBuilder);
//            responseEntity = userResource.register(user);
//        } catch (UserNotFoundException | UsernameExistsException | EmailExistsException e) {
//            // Handle exceptions if needed
//            e.printStackTrace();
//        }
//    }
//
//    @Then("the user should be registered successfully")
//    public void thenTheUserShouldBeRegisteredSuccessfully() {
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        User registeredUser = responseEntity.getBody();
//        assertNotNull(registeredUser);
//        assertEquals(user.getFirstName(), registeredUser.getFirstName());
//        assertEquals(user.getLastName(), registeredUser.getLastName());
//        assertEquals(user.getUsername(), registeredUser.getUsername());
//        assertEquals(user.getEmail(), registeredUser.getEmail());
//    }
//}
