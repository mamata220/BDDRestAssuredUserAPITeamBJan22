#@UserApiGET
Feature: GetUser scenario
   Verify User GET operations on User APIs using REST-assured
    
Scenario Outline: To Check Authorization without providing username and password Negative Scenario
    Given User sends a username and password using basic authentication to the endpoint "/Users"
    When User sends an empty string for both username as "<Username>" and for password as "<Password>" using a GET Request to the api endpoint
    Then User receives an exception from the get api endpoint
    
    Examples:  
    	| Username || Password  |
    	|  ||   |
    	
    	
Scenario: To Check Authorization without providing username and password Negative Scenario
    Given User sends a username and password using basic authentication to the endpoint "/Users"
    When User sends null for both username as "" and for password as "" using a GET Request to the api endpoint
    Then User receives an exception from the get api endpoint
   
    
Scenario Outline: Check the Authorization using Basic auth by passing an incorrect username or password combination
    Given User sends a username and password using basic authentication to the endpoint "/Users"
    When User sends incorrect username "<Username>" or incorrect password "<Password>" as a GET HTTP Request using the endpoint
    Then User receives statuscode <StatusCode> from the get api endpoint
    
    Examples:
    | Username || Password  | |StatusCode|
    | Hello || 2xx@Success | | 401 |
    | APIPROCESSING || paswrd | | 401 | 
      
      
Scenario Outline: Do a schema validation for the api Get call
    Given User wants to do a schema validation using basic authentication using the GET endpoint "<ENDPOINT>"
    When User sends correct username "<Username>" and correct password "<Password>" as a GET HTTP Request using the endpoint to do a schema validation using schema "<USERSCHEMAFILE>"
    Then Schema validation succeeds
    
    Examples:
    |ENDPOINT| | Username || Password | |USERSCHEMAFILE|
    |/Users| | APIPROCESSING || 2xx@Success || user_schema2.json |
    |/Users/U02| | APIPROCESSING || 2xx@Success || user_schema3.json |
    
    
Scenario Outline: Retrieve all records using get method in User api for positive scenario
    Given User sends a username and password using basic authentication to the endpoint "/Users"
    When User sends correct username "<Username>" and correct password "<Password>" as a GET HTTP Request using the endpoint
    Then user retrieves all users
    And User receives statuscode 200 from the get api endpoint
    
    Examples:
    | Username || Password |
    | APIPROCESSING || 2xx@Success |

Scenario Outline: Test to get a single user record when a correct userId is passed as path variable for positive scenario
    Given User sends a username and password using basic authentication to the endpoint "/Users/<USERID>"
    When User sends correct username "<Username>" and correct password "<Password>" as a GET HTTP Request using the endpoint to fetch a single user
    Then user receives a single record of the requested userid as a response with name as "<USERNAME>" and phone <PHONENUMBER>
    And User receives statuscode 200 from the get api endpoint
    
    Examples: 
    | Username || Password  || USERID | | USERNAME||PHONENUMBER|
    | APIPROCESSING || 2xx@Success | | U02 | | Dayal | |893456789|
    | APIPROCESSING || 2xx@Success | | U03 | | Shanti | |783456743|
    
Scenario Outline: Test to get a single user record when a incorrect userId is passed as path variable for negative scenario
    Given User sends a username and password using basic authentication to the endpoint "/Users/<USERID>"
    When User sends correct username "<Username>" and correct password "<Password>" as a GET HTTP Request using the endpoint to fetch a single user
    Then user receives error message as "No value present" in the response
    And User receives statuscode <StatusCode> from the get api endpoint
    
     Examples:
    | Username || Password || USERID | |StatusCode|   
     | APIPROCESSING || 2xx@Success | | X06 || 400 |
     | APIPROCESSING || 2xx@Success | | 0 || 400 |
     | APIPROCESSING || 2xx@Success | | 106 || 400 |
     | APIPROCESSING || 2xx@Success | | .07 || 400 |
     | APIPROCESSING || 2xx@Success | | $01 || 400 |
     | APIPROCESSING || 2xx@Success | | Null || 400 |
     | APIPROCESSING || 2xx@Success | | null || 400 |
     # | APIPROCESSING || 2xx@Success | |  || 200 |
   