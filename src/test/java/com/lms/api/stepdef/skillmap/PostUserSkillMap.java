package com.lms.api.stepdef.skillmap;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONObject;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostUserSkillMap extends BaseClass {

	RequestSpecification RequestSpec;
	Response response;
	String userId;
	String path;
	String sheetPost;

	DataTable dataTable;
	Scenario scenario;
	
	String bodyExcel;

	// Before annotation from io cucumber
	// Scenario class will give us information at the runtime like the scenario
	// name, getid() or isFailed()
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPost = LoadProperties().getProperty("sheetPost");
		dataTable = new DataTable("src/test/resources/excel/data_UserSkillMap.xls");
		dataTable.createConnection(sheetPost);

	}

	@Given("User is on Post Method with endpoint url\\UserSkills with valid JSON schema")
	public void user_is_on_post_method_with_endpoint_url_user_skills_with_valid_json_schema() throws IOException{
		
		RestAssured.baseURI = LoadProperties().getProperty("base_uri");

		RequestSpec = RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				LoadProperties().getProperty("password"));
		// RequestSpec = RestAssured.given().auth().preemptive().basic("username","password");

		path = "/SkillsMap";

		// response = RequestSpec.given().when().get(path);
		// System.out.println(response.asString());

	}

	@When("User sends request with valid input")
	public void user_sends_request_with_valid_input() throws IOException {
		
		requestSpecificationPOST();

	}
	
	@Then("User should receive Bad Request status codes")
	public void user_should_receive_bad_request_status_codes() throws IOException {
		
		thenMethodSpecificationPOST();
		
		  
	}
	

	


	
	@When("User sends request with inputs where skill id is alphanumeric")
	public void user_sends_request_with_inputs_where_skill_id_is_alphanumeric() throws IOException {
		//requestSpecificationPOST();
		requestSpecificationPOSTWhenExceptionExpected();

	}
    
	@Then("User should receive valid status codes")
	public void user_should_receive_valid_status_codes() throws IOException, SQLException {
		
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);
		
	    System.out.println("Response Status code is =>  " + response.statusCode());
		int statuscode = response.statusCode();
		Assert.assertEquals(Integer.parseInt(expStatusCode),statuscode);
		
	    String responseBody = response.prettyPrint();
		System.out.println("Response Body is =>  " + responseBody);
		JSONObject obj = new JSONObject(responseBody);
		String user_skill_id = obj.get("user_skill_id").toString();
		dbvalidation(responseBody, user_skill_id);
		
	}
	
	

	@When("User sends request with inputs where skill id is null")
	public void user_sends_request_with_inputs_where_skill_id_is_null() throws IOException {
		//requestSpecificationPOST();
		requestSpecificationPOSTWhenExceptionExpected();
	}

	@When("User sends request with inputs where user id is null")
	public void user_sends_request_with_inputs_where_user_id_is_null() throws IOException {
		//requestSpecificationPOST();
		requestSpecificationPOSTWhenExceptionExpected();

	}

	@When("User sends request with inputs where month of experience is alphanumeric")
	public void user_sends_request_with_inputs_where_month_of_experience_is_alphanumeric() throws IOException {
	//	requestSpecificationPOST();
		requestSpecificationPOSTWhenExceptionExpected();

	}

	@When("User sends request with inputs where months of experience as null")
	public void user_sends_request_with_inputs_where_months_of_experience_as_null() throws IOException {
		
	//	requestSpecificationPOST();
		requestSpecificationPOSTWhenExceptionExpected();

	}
	
	
	public void requestSpecificationPOST() throws IOException {
		
		bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(bodyExcel).log().all();

		// Validation of requestBody with User schema
		assertThat(bodyExcel, matchesJsonSchemaInClasspath("userSkillMapPost_schema.json"));
		System.out.println("Validated the schema");
		
		 //RequestSpec.log().all();
		
		 response = RequestSpec.post(path);
			// response = RequestSpec.request(Method.POST, path);
			
		}
	
	private void requestSpecificationPOSTWhenExceptionExpected() throws IOException {
		
		bodyExcel = dataTable.getDataFromExcel(scenario.getName(), "Body");
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(bodyExcel).log().all();

		 Assert.assertThrows(JsonSchemaValidationException.class, () -> {
			 
			
		// Validation of requestBody with User schema
			 assertThat(bodyExcel, matchesJsonSchemaInClasspath("userSkillMapPost_schema.json"));
		//	 System.out.println("Validated the schema");
		
		 //RequestSpec.log().all();
		
		 
			// response = RequestSpec.request(Method.POST, path);
		  });
		 
		 response = RequestSpec.post(path);
			
		}
	
	public void thenMethodSpecificationPOST() throws IOException {
		
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
