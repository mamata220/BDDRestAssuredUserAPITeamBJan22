package com.lms.api.stepdef.skills;
/**@author:Tejaswi**/

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.sql.SQLException;


public class GetSkillStepDef extends TestBase{
	RequestSpecification requestSpec ;
	Response response;
	String path;
	String sheetGet;
	
	DataTable dataTable;
	Scenario scenario;
	
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
	this.scenario=scenario;
	sheetGet=LoadProperties().getProperty("sheetGet");	  
	dataTable =new DataTable("./src/test/resources/excel/testdata1.xls");
	dataTable.createConnection(sheetGet);
	
	}
	
	public void requestSpecificationGet() {
		requestSpec.header("Content-Type", "application/json");
		requestSpec.log();
		response = requestSpec.when().get(path);
	}
	@Given("User is on GET method with endpoint Skills")
	public void user_is_on_get_method_with_endpoint_skills() {
	   
		RestAssured.baseURI=LoadProperties().getProperty("base_uri");
		requestSpec=RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				    LoadProperties().getProperty("password"));
		path=LoadProperties().getProperty("endpointgetAll");
		System.out.println("Path for GetAll is "+ path);
 	}

	@When("User sends request from Skill API")
	public void user_sends_request() {
		requestSpecificationGet();
	}

	@Then("User receives list of all Skills")
	public void user_receives_list_of_all_skills() throws IOException {
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.asPrettyString();
		System.out.println("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  " + expStatusCode);
		System.out.println("Response Body is =>  " + responseBody);
		assertEquals(Integer.parseInt(expStatusCode),response.statusCode());
		
		
	}
	
	@Given("User is on GET method with endpoint Skills with Skill_id")
	public void user_is_on_get_method_with_endpoint_url_skills_with_skill_id() throws IOException {
		RestAssured.baseURI=LoadProperties().getProperty("base_uri");
		requestSpec=RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				    LoadProperties().getProperty("password"));
		String skill_id=dataTable.getDataFromExcel(scenario.getName(),"Skill_id"); 
		System.out.println("SkillId is : " +skill_id);
		path=LoadProperties().getProperty("endpoint") + skill_id;
		System.out.println("Path for Get is "+ path);
	}

	@When("User sends the request with specific Skill_Id")
	public void user_sends_the_request_with_specific_skill_id() {
		requestSpecificationGet();;
	}

	@Then("User receives the particular Skill_Id details")
	public void user_receives_the_particular_skill_Id_details() throws IOException, SQLException {
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.asPrettyString();
		System.out.println("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  " + expStatusCode);
		System.out.println("Response Body is =>  " + responseBody);
		assertEquals(Integer.parseInt(expStatusCode),response.statusCode());
		String skill_id=dataTable.getDataFromExcel(scenario.getName(),"Skill_id"); 
		dbvalidation(responseBody,skill_id);
		
	}
	@When("User sends the request with invalid Skill Id")
	public void user_sends_the_request_with_invalid_skill_id() {
		requestSpecificationGet();
	}

	@When("User sends the request with alphanumeric Skill Id")
	public void user_sends_the_request_with_alphanumeric_skill_id() {
		requestSpecificationGet();
	}

	@Given("User is on GET method with endpoint Skills and Skill id null")
	public void user_is_on_GET_method_with_endpoint_skills_and_skill_id_null() throws IOException {
	   
		RestAssured.baseURI=LoadProperties().getProperty("base_uri");
		requestSpec=RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				    LoadProperties().getProperty("password"));
		//path=LoadProperties().getProperty("endpoint");
		String skill_id=dataTable.getDataFromExcel(scenario.getName(),"Skill_id"); 
		System.out.println("SkillId is : " +skill_id);
		path=LoadProperties().getProperty("endpoint") + skill_id;
		System.out.println("Path for Get is "+ path);
 	}
	@When("User sends the request with skill id as null")
	public void user_sends_the_request_with_skill_id_as_null() {
		requestSpecificationGet();
	}

	@Then("User doesnot get the particular Skill_Id")
	public void user_doesnot_get_the_particular_skill_id() throws IOException {
		String expStatusCode = dataTable.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.asPrettyString();
		System.out.println("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  " + expStatusCode);
		System.out.println("Response Body is =>  " + responseBody);
		assertEquals(Integer.parseInt(expStatusCode),response.statusCode());
		
	}
}


