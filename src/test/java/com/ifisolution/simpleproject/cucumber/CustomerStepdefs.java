package com.ifisolution.simpleproject.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerStepdefs {
    private String uri = "http://localhost:8080/customer";
    public RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<String> response;
    private HttpHeaders headers;

    @Given("Set POST customer api endpoint")
    public void setPOSTCustomerApiEndpoint() {
        System.out.println("Add URL :" + uri);
    }

    @When("Set request HEADER")
    public void setRequestHEADER() {
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }

    @And("Send a POST HTTP request")
    public void sendAPOSTHTTPRequest() {
        String jsonBody = "{\"name\":\"nql\",\"phone\":111,\"address\":\"HN\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        System.out.println(headers);

        response = restTemplate.postForEntity(uri, entity, String.class);
    }

    @Then("Receive status code of {int}")
    public void receiveStatusCodeOf(int statusCode) {
        assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
    }

    @Given("customer id is {int}")
    public void customerIdIs(int id) {

    }

    @When("Delete this customer")
    public void deleteThisCustomer() {
    }

    @Then("revice a message {string}")
    public void reviceAMessage(String arg0) {
    }
}
