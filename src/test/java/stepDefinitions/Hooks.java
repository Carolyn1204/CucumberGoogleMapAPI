package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		
		stepDefinition sd = new stepDefinition();
		if(stepDefinition.placeId==null) {
			sd.add_new_place_with_payload();
			sd.user_calls_with("post", "addPlaceAPI");
			sd.get_the_value_of_in_response("place_id");
		}
	}
}
