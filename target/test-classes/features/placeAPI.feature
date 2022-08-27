Feature: Place API

@AddPlace
Scenario: Verify if place is being added succesfully
Given Add new place with payload
When user calls "post" with "addPlaceAPI"
Then API call got successs with status code 200
And verify if "status" in response is "OK"
And get the value of "place_id" in response

@UpdatePlace
Scenario: Verify if place is updated succesfully
Given Update place using payload and queryParam "place_id"
When user calls "put" with "updatePlaceAPI"
Then API call got successs with status code 200

@GetPlace
Scenario: Verify if Get Place API functionality is succesful
Given Get place using queryParam "place_id"
When user calls "get" with "getPlaceAPI"
Then API call got successs with status code 200
And verify if "address" in response is "70 winter walk, USA"

@DeletePlace
Scenario: Verify if Delete Place API functionality is succesful
Given Delete place with payload
When user calls "post" with "deletePlaceAPI"
Then API call got successs with status code 200
And verify "status" in response is "OK"
