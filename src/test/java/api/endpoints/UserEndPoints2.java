package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	//property
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		//method is already specifying property file, so you don't need path
		return routes;
	}
	
	//post
	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");
		
		Response res = 
				given().
					contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).
				when().
					post(post_url);
		return res;
	}
	
	//get
	public static Response getUser(String username) {
		String get_url = getURL().getString("get_url");
		
		Response res = 
				given().
					pathParam("username", username).
				when().
					get(get_url);
		return res;
	}
	
	//put
	public static Response updateUser(User payload, String username) {
		String update_url = getURL().getString("update_url");
		
		Response res = 
				given().
					contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).pathParam("username", username).
				when().
					put(update_url);
		return res;
	}
	
	//delete
	public static Response deleteUser(String username) {
		String delete_url = getURL().getString("delete_url");
		
		Response res = 
				given().
					pathParam("username", username).
				when().
					delete(delete_url);
		return res;
	}
}
