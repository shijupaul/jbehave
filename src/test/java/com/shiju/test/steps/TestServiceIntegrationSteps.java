package com.shiju.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class TestServiceIntegrationSteps {

    private TestRestTemplate restTemplate;

    public TestServiceIntegrationSteps() {
        restTemplate = new TestRestTemplate();
    }


    @Given("We add a new Person to the backend")
    public void givenWeAddNewPersonToBackend() throws Throwable {
        System.out.println("**** Received method invocation -- 'We add a new Person to the backend'");
    }

    @When("We retrieve the person using rest endpoint")
    public void whenWeRetrievePersonUsingRestEndpoint() throws Exception {
        System.out.println("**** We retrieve the person using rest endpoint'");
    }

    @Then("It should be the expected person")
    public void thenItShouldBeThePersonExpected() throws Throwable {
        System.out.println("**** It should be the expected person'");
    }
}
