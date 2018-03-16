package com.shiju.test.steps;

import com.shiju.test.dto.Address;
import com.shiju.test.dto.Person;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestServiceIntegrationSteps {

    private TestRestTemplate restTemplate;

    private String service_url;

    private static final String CREATE_PERSON_URL = "%s/person";

    private static final String GET_PERSON_URL = "%s/person/{personId}";

    private final Person testPerson;

    private Person personRetrieved;


    public TestServiceIntegrationSteps() {

        restTemplate = new TestRestTemplate();
        service_url = System.getProperty("serviceUrl");
        testPerson = new Person(UUID.randomUUID().hashCode(), "Test Person", new Address());
    }


    @Given("We add a new Person to the backend")
    public void givenWeAddNewPersonToBackend() throws Throwable {
        ResponseEntity<Person> responseEntity =  restTemplate.postForEntity(String.format(CREATE_PERSON_URL,service_url), testPerson, Person.class);
    }

    @When("We retrieve the person using rest endpoint")
    public void whenWeRetrievePersonUsingRestEndpoint() throws Exception {
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(String.format(GET_PERSON_URL,service_url), Person.class,testPerson.getId());
        personRetrieved = responseEntity.getBody();
        assertThat("Response code don't match", responseEntity.getStatusCode().value(), Matchers.is(200));
    }

    @Then("It should be the expected person")
    public void thenItShouldBeThePersonExpected() throws Throwable {
        assertTrue("Entity don't match", personRetrieved.equals(testPerson));
    }
}
