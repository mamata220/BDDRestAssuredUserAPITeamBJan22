package StepDefinitionUserSkillMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

public class DeleteUserSkillMap extends BaseClass {
	
	

	RequestSpecification RequestSpec;
	Response response;
	String userId;
	String path;
	String sheetDelete;

	DataTable dataTable;
	Scenario scenario;

	// Before annotation from io cucumber
	// Scenario class will give us information at the runtime like the scenario
	// name, getid() or isFailed()
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetDelete = LoadProperties().getProperty("sheetDelete");
		dataTable = new DataTable("src/test/resources/excel/data_UserSkillMap.xls");
		dataTable.createConnection(sheetDelete);

	}
	
	
	
	@Given("User is on DELETE Method")
	public void user_is_on_delete_method() {
		
		RestAssured.baseURI = LoadProperties().getProperty("base_uri");

		RequestSpec = RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				LoadProperties().getProperty("password"));
		// RequestSpec = RestAssured.given().auth().preemptive().basic("username","password");

		path = "/UserSkills/";
		
	
	}

	@When("User sends request with existing user_skill_ Id  as input")
	public void user_sends_request_with_existing_user_skill_id_as_input() throws IOException {
		
		requestSpecificationDelete();
	    
	}

	@Then("User gets Response")
	public void user_gets_response() throws IOException {
		
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = dataTable.getDataFromExcel(scenario.getName(), "Message");
		System.out.println("Expected response code: " + expStatusCode + "Expected message is: " + expMessage);
		
	    System.out.println("Response Status code is =>  " + response.statusCode());
		int statuscode = response.statusCode();
		Assert.assertEquals(Integer.parseInt(expStatusCode),statuscode);
		
	    String responseBody = response.prettyPrint();
		System.out.println("Response Body is =>  " + responseBody);
	 
	}

	@When("User sends request with nonexisting user_skill_ Id  as input")
	public void user_sends_request_with_nonexisting_user_skill_id_as_input() throws IOException {
	   
		requestSpecificationDelete();
	}

	@When("User sends request with user_skill_id as blank")
	public void user_sends_request_with_user_skill_id_as_blank() throws IOException {
		
		requestSpecificationDelete();
	  
	}

	@When("User sends request with  user_skill_id as alphanumeric")
	public void user_sends_request_with_user_skill_id_as_alphanumeric() throws IOException {
		requestSpecificationDelete();
	   
	}

	@When("User sends request with  user_skill_id as decimal no")
	public void user_sends_request_with_user_skill_id_as_decimal_no() throws IOException {
		
		requestSpecificationDelete();
	    
	}

	public void requestSpecificationDelete() throws IOException {
		
		String UserSkillsId = dataTable.getDataFromExcel(scenario.getName(), "UserSkills");
			
			//response = RequestSpec.get(path);
		    RequestSpec.log().all();
			response = RequestSpec.request(Method.DELETE,path+UserSkillsId);
		}

}
