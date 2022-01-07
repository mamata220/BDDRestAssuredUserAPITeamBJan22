package com.lms.api.stepdef.skillmap;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class PutUserSkillMap extends BaseClass {
	
	RequestSpecification RequestSpec;
	Response response;
	//String userId;
	String path;
	String sheetPut;

	DataTable dataTable;
	Scenario scenario;

	// Before annotation from io cucumber
	// Scenario class will give us information at the runtime like the scenario
	// name, getid() or isFailed()
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPut = LoadProperties().getProperty("sheetPut");
		dataTable = new DataTable("src/test/resources/excel/data_UserSkillMap.xls");
		dataTable.createConnection(sheetPut);

	}
	
	
	@Given("User is on Put Method")
	public void user_is_on_put_method() {
		RestAssured.baseURI = LoadProperties().getProperty("base_uri");

		RequestSpec = RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				LoadProperties().getProperty("password"));
		// RequestSpec = RestAssured.given().auth().preemptive().basic("username","password");

		path = "/SkillsMap/";
	}

	@When("User sends request with input as valid User_skill_Id")
	public void user_sends_request_with_input_as_valid_user_skill_id() throws IOException {
		
		String UserSkillsId = dataTable.getDataFromExcel(scenario.getName(), "UserSkills");
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(bodyExcel).log().all();

		// Validation of requestBody with User schema
		assertThat(bodyExcel, matchesJsonSchemaInClasspath("userSkillMap_schema.json"));
		System.out.println("Validated the schema");
		
		response = RequestSpec.request(Method.PUT,path+UserSkillsId);
	}

	@When("User sends request with input as invalid User_skill_Id")
	public void user_sends_request_with_input_as_invalid_user_skill_id() throws IOException {
		
		requestSpecificationPut();
	}

	@Then("User should receive valid status code")
	public void user_should_receive_valid_status_code() throws IOException, SQLException {
		
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);
		
	    System.out.println("Response Status code is =>  " + response.statusCode());
		int statuscode = response.statusCode();
		Assert.assertEquals(Integer.parseInt(expStatusCode),statuscode);
		
	    String responseBody = response.prettyPrint();
		System.out.println("Response Body is =>  " + responseBody);
		
		assertThat(responseBody,matchesJsonSchemaInClasspath("userSkillMap_schema.json"));
		System.out.println("Validated the response body schema");
		
		String UserSkillsId = dataTable.getDataFromExcel(scenario.getName(), "UserSkills");
		dbvalidation(responseBody,UserSkillsId);
	   
		
		
		
	    	}
	
	
	@When("User sends request with input as invalid skill Id")
	public void user_sends_request_with_input_as_invalid_skill_id() throws IOException {
		requestSpecificationPut();
	}

	@When("User sends request with number of months as input")
	public void user_sends_request_with_number_of_months_as_input() throws IOException {
		requestSpecificationPut();
	}

	@When("User sends request with invalid input as boolean")
	public void user_sends_request_with_invalid_input_as_boolean() throws IOException {
		requestSpecificationPut();
	}
	
	public void requestSpecificationPut() throws IOException {
		
		String UserSkillsId = dataTable.getDataFromExcel(scenario.getName(), "UserSkills");
		String bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(bodyExcel).log().all();
		
		response = RequestSpec.request(Method.PUT,path+UserSkillsId);
		
	}
	
	@Then("User should receive Bad Requests")
	public void user_should_receive_bad_requests() throws IOException {
		
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);
		
	    System.out.println("Response Status code is =>  " + response.statusCode());
		int statuscode = response.statusCode();
		Assert.assertEquals(Integer.parseInt(expStatusCode),statuscode);
		
	    String responseBody = response.prettyPrint();
		System.out.println("Response Body is =>  " + responseBody);
	 
	}

}
