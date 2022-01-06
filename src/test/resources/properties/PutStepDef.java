package StepDefinitionUser;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserPutStepDef extends TestBase{
	
	String url;
	RequestSpecification RequestSpec;
	Response response;
	String userId;
	
	public JSONObject userdata(String uid,String fName,String lName,Object phoneNo,String loc,String tzone,String linkedIn,String Ug,String Pg,String comm,String visaStatus) {
			      
		JSONObject userRecord = new JSONObject();
		
		userRecord.put("user_id",uid);
		userRecord.put("fristName",fName);
		userRecord.put("lastName",lName);
		userRecord.put("phoneNumber",phoneNo);
		userRecord.put("location",loc);
		userRecord.put("timeZone",tzone);
		userRecord.put("linkedInURL",linkedIn);
		userRecord.put("eduUg",Ug);
		userRecord.put("eduPg",Pg);
		userRecord.put("comments",comm);
		userRecord.put("visaStatus",visaStatus);
		
			
		return userRecord;
	}


	@Given("^User is on Put Method with endpoint(.*)$")
	public void user_is_on_put_method_with_endpoint_url_users(String id) {
			
	//baseURI = "http://localhost:";
		
		//httpRequest = RestAssured.given().auth().preemptive().basic("APIPROCESSING", "2xx@Success");
		
		//RestAssured.baseURI = LoadProperties().getProperty("base_uri");
		RestAssured.baseURI = LoadProperties().getProperty("base_uri");
		RequestSpec = RestAssured.given().auth().preemptive().basic(LoadProperties().getProperty("username"),
				LoadProperties().getProperty("password"));
						         
		userId = id;
		url =   "/Users/" + id;
	
		
	}

	@When("user sends request to update user name with Valid input")
	public void user_sends_request_to_update_user_name_with_valid_input() {	 
		
		JSONObject userRecord =userdata(userId,"Key","Matthew",1234778898,"New York","PST","https://www.linkedin.com/in/John Matthew/","Computer Science Engineering"," MBA","Hello","H4-EAD");
		
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(userRecord.toString());
	
		// Validation of  requestBody with  User schema 
		assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
				 
		//response = httpRequest.request(Method.PUT, url);
		response= RequestSpec.when().put(url);
		
	}

	@Then("User should receive 201 OK Created status code for put")
	public void user_should_receive_201_OK_status_code() {		
		String responseBody = response.prettyPrint();
		
		System.out.println("Response Status code is =>  " + response.statusCode());
		System.out.println("Response Body is =>  " + responseBody);
	
		
	}
	


	@When("user sends request to update phone number with alphanumeric input")
	public void user_sends_request_to_update_phone_number_with_alphanumeric_input() {
		
		
		JSONObject userRecord =userdata(userId,"Key","Matthew","1234@z7889","Boston","EST","https://www.linkedin.com/in/John Matthew/","Computer Science Engineering","MCom","lo","H4-EAD");	
	
	
		RequestSpec.header("Content-Type", "application/json");
		RequestSpec.body(userRecord.toString());		
		
		assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
		
		 
		response = RequestSpec.request(Method.PUT, url);
	}

		@Then("User should receive {int} Bad Request status code for put")
		public void user_should_receive_bad_request_status_code(Integer int1) {
			String responseBody = response.prettyPrint();
				
				System.out.println("Response Status code is =>  " + response.statusCode());
				System.out.println("Response Body is =>  " + responseBody);
		}


		@When("user sends request to update time zone with valid input")
		public void user_sends_request_to_update_time_zone_with_valid_input() {
			
			JSONObject userRecord =userdata(userId,"Key","Matthew",1234778898,"New York","PST","https://www.linkedin.com/in/John Matthew/","Computer Science Engineering"," MBA","Hello","H4-EAD");	
		
		
			RequestSpec.header("Content-Type", "application/json");
			RequestSpec.body(userRecord.toString());		
			
			assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
			
			response = RequestSpec.request(Method.PUT, url);
		}


		@Then("User should receive {int} OK Valid status code for put")
		public void user_should_receive_ok_valid_status_code(Integer int1) {
			String responseBody = response.prettyPrint();
			
			System.out.println("Response Status code is =>  " + response.statusCode());
			System.out.println("Response Body is =>  " + responseBody);
		}
		
		
		@When("user sends request to update time zone with invalid input")
		public void user_sends_request_to_update_time_zone_with_invalid_input() {
			
			
			JSONObject userRecord =userdata(userId,"Key","Matthew",1234778898,"New York","PTS","\"https://www.linkedin.com/in/Key Matthew/","Computer Science Engineering"," MBA","Hello","H4-EAD");
			
		
			RequestSpec.header("Content-Type", "application/json");
			RequestSpec.body(userRecord.toString());		
			
			assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
			
			response = RequestSpec.request(Method.PUT, url);
		}
		

		@Then("User should receive {int} Internal Server Error status code for put")
		public void user_should_receive_Internal_Server_Error_status_code(Integer int1) {
		
				System.out.println("Response Status code is =>  " + response.statusCode());
				
				System.out.println(response.prettyPrint());
		}
		
		@When("user sends request to update visa status with invalid input")
		public void user_sends_request_to_update_visa_status_with_invalid_input() {
			
			JSONObject userRecord =userdata(userId,"Key","Matthew",1234778898,"New York","EST","https://www.linkedin.com/in/Key Matthew/","Computer Science Engineering"," MBA","Hello","H4EAD");
			
			RequestSpec.header("Content-Type", "application/json");
			RequestSpec.body(userRecord.toString());		
			
			assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
			
			response = RequestSpec.request(Method.PUT, url);
		}
		
		
		
		
		@When("user sends request to update Linkedin with valid input")
		public void user_sends_request_to_update_Linkedin_with_valid_input() {
			
			JSONObject userRecord =userdata(userId,"Maria","Peterson",1512391300,"Ontario","EST","https://www.linkedin.com/in/MariaPeterson/","Information Sciences","MBA","Hello","Canada-Citizen");
			
		
			RequestSpec.header("Content-Type", "application/json");
			RequestSpec.body(userRecord.toString());		
			
			assertThat(userRecord.toString(), matchesJsonSchemaInClasspath("put_schema.json"));
			
			response = RequestSpec.request(Method.PUT, url);
		}
		
		
}
