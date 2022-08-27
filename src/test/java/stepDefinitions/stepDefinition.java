package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Base;
import resources.TestDataBuild;

public class stepDefinition extends Base {
	
	public static String placeId;
	RequestSpecification req;
	ResponseSpecification resp;
	Response response;
	TestDataBuild tdb = new TestDataBuild();
	
	@Given("Add new place with payload")
	public void add_new_place_with_payload() throws Exception {

		req = given().spec(reqSpecification()).body(tdb.addPlaceBuild());
	}
	    

	@When("user calls {string} with {string}")
	public void user_calls_with(String method, String APIResource) {
		
		if(method.equalsIgnoreCase("get"))
			response = req.when().get(getAPIResource(APIResource));
		else if(method.equalsIgnoreCase("put"))
			response = req.when().put(getAPIResource(APIResource));
		else if(method.equalsIgnoreCase("post")&&APIResource.equalsIgnoreCase("addPlaceAPI"))
			response = req.when().post(getAPIResource(APIResource));
		else if(method.equalsIgnoreCase("post")&&APIResource.equalsIgnoreCase("deletePlaceAPI"))
			response = req.when().post(getAPIResource(APIResource));
	}

	@Then("API call got successs with status code {int}")
	public void api_call_got_successs_with_status_code(int status_code) throws Exception {
		
		resp = req.then().spec(respSpecification(status_code));
	    
	}


	@Then("verify if {string} in response is {string}")
	public void verify_if_in_response_is(String status, String OK) {
	    
		assertEquals(getJsonPath(response,status),OK);
	}
	

	@Then("get the value of {string} in response")
	public void get_the_value_of_in_response(String place_id) {
		
		placeId = getJsonPath(response,place_id);
	    
	}
	
	@Given("Update place using payload and queryParam {string}")
	public void update_place_using_payload_and_query_param(String place_id) throws Exception {
		
		req = given().spec(reqSpecification()).body(tdb.updatePlaceBuild(placeId)).queryParam(place_id, placeId);
	    
	}

	
	@Given("Get place using queryParam {string}")
	public void get_place_using_query_param(String place_id) throws Exception {
	    
		req = given().spec(reqSpecification()).queryParam(place_id, placeId);
	}


	@Given("Delete place with payload")
	public void delete_place_with_payload() throws Exception {
		
		req = given().spec(reqSpecification()).body(tdb.deletePlaceBuild(placeId));
	    
	}

	@Then("verify {string} in response is {string}")
	public void verify_in_response_is(String status, String OK) {
	    
		assertEquals(getJsonPath(response, status), OK);	
	}

}
