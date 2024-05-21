package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.User;

public class UserEndPoints {
	public static Response createUser(User payload) {
		Response res = 
				given().
					contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).
				when().
					post(Routes.post_url);	
		return res;
	}
	
	public static Response getUser(String username) {
		Response res = 
				given().
					pathParam("username", username).
				when().
					get(Routes.get_url);
		return res;
	}
	
	public static Response updateUser(User payload, String username) {
		Response res = 
				given().
					contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).pathParam("username", username).
				when().
					put(Routes.update_url);
		return res;
	}
	
	public static Response deleteUser(String username) {
		Response res = 
				given().
					pathParam("username", username).
				when().
					delete(Routes.delete_url);
		return res;
	}
}
