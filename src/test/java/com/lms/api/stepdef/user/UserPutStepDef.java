package com.lms.api.stepdef.user;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserPutStepDef extends TestBase {

	RequestSpecification RequestSpec;
	Response response;
	String userId;
	String path;
	String sheetPut;

	DataTable dataTable;
	Scenario scenario;

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPut = LoadProperties().getProperty("sheetPut");
		// System.out.println(sheetPost);
		dataTable = new DataTable("src/test/resources/excel/data.xls");
		dataTable.createConnection(sheetPut);

	}

	public void requestSpecification(String pbodyExcel) {
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(pbodyExcel).log().all();

		// Validation of requestBody with User schema
		assertThat(pbodyExcel, matchesJsonSchemaInClasspath("before_put_schema.json"));
		// System.out.println(path);
		response = RequestSpec.when().put(path);
	}

	@Given("^User is on Put Method with endpoint")
	public void user_is_on_put_method_with_endpoint() throws IOException {

		RestAssured.baseURI = LoadProperties().getProperty("base_uri");
		RequestSpec = RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				LoadProperties().getProperty("password"));

		String userId = dataTable.getDataFromExcel(scenario.getName(), "UserId");
		path = LoadProperties().getProperty("endpointGet") + userId;
		// System.out.println(path);

	}

	@When("User sends request to update user name with valid input")
	public void user_sends_request_to_update_user_name_with_valid_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);

	}

	@Then("User should receive status code and message for put")
	public void user_should_receive_status_code_and_message_for_put() throws Exception {
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);

		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println(response.statusCode());
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		// String resp_msg = js.get("message");
		System.out.println("Response Status code is =>  " + response.statusCode());
		// System.out.println("Resposne Message is => " + resp_msg);
		System.out.println("Response Body is =>  " + responseBody);
	}

	/*
	 * @Then("Validate response for status code and message for put") public void
	 * validate_response_for_status_code_and_message_for_put() throws IOException {
	 * String expStatusCode = dataTable.getDataFromExcel(scenario.getName(),
	 * "StatusCode"); String expMessage =
	 * dataTable.getDataFromExcel(scenario.getName(), "Message");
	 * System.out.println("Expected response code: " + expStatusCode +
	 * "Expected message is: " + expMessage);
	 * 
	 * String responseBody = response.prettyPrint(); JsonPath js =
	 * response.jsonPath(); // System.out.println(response.statusCode());
	 * assertEquals(Integer.parseInt(expStatusCode), response.statusCode()); String
	 * resp_msg = js.get("message");
	 * System.out.println("Response Status code is =>  " + response.statusCode());
	 * System.out.println("Resposne Message is =>  " + resp_msg);
	 * System.out.println("Response Body is =>  " + responseBody);
	 * 
	 * }
	 */

	@When("User sends request to update user name with alphanumeric input")
	public void user_sends_request_to_update_user_name_with_alphanumeric_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@When("User sends request to update phone number with invalid input")
	public void user_sends_request_to_update_phone_number_with_invalid_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@When("User sends request to update location and comments with alphanumeric input.")
	public void user_sends_request_to_update_location_and_comments_with_alphanumeric_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@When("User sends request to update time zone with valid input")
	public void user_sends_request_to_update_time_zone_with_valid_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);

		/*
		 * RequestSpec.header("Content-Type", "application/json");
		 * RequestSpec.body(bodyExcel).log().all();
		 * 
		 * System.out.println("In When"); // Validation of requestBody with User schema
		 * assertThat(bodyExcel, matchesJsonSchemaInClasspath("put_schema.json"));
		 * response = RequestSpec.when().put(path); System.out.println("In When");
		 */
	}

	@When("User sends request to update time zone with invalid input")
	public void user_sends_request_to_update_time_zone_with_invalid_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@When("User sends request to update Linkedin id with valid input")
	public void user_sends_request_to_update_linkedin_id_with_valid_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@When("user sends request to update visa status with numeric input")
	public void user_sends_request_to_update_visa_status_with_numeric_input() throws IOException {
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		requestSpecification(bodyExcel);
	}

	@Then("User should receive error status code and message for put")
	public void user_should_receive_error_status_code_and_message_for_put() throws Exception {
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);

		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		// System.out.println(response.statusCode());
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		String resp_msg = js.get("message");
		System.out.println("Response Status code is =>  " + response.statusCode());
		System.out.println("Resposne Message is =>  " + resp_msg);
		System.out.println("Response Body is =>  " + responseBody);
	}

}
