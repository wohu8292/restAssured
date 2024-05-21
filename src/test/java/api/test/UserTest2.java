package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("**********Creating user**********");
		Response response = UserEndPoints2.createUser(this.userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User is created**********");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		logger.info("**********Reading user info**********");
		Response response = UserEndPoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User is disdplayed**********");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("**********Updating user**********");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload, userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterupdate = UserEndPoints2.getUser(this.userPayload.getUsername());
		responseAfterupdate.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		logger.info("**********User info is updated**********");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("**********Deleting user**********");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User deleted**********");
	}
}
