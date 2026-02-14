package API_Helpers;

import java.util.Properties;

import Utilites.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PasswordManager {

	static Properties pro = PropertyReader.init_prop();
	
	public static String init_Auth_Token() {
		
		Response response = RestAssured.given()
				.accept(ContentType.JSON)
				.header("Content-Type","application/json")
				.body(pro.getProperty("body").trim())
				
				.when()
				.post(PropertyReader.init_prop().getProperty("auth_api").trim());
		
		String token = response.body().jsonPath().getString("token");
		
		return token;
	}
	
//	public static void main(String[] args) {
//		
//		String token = init_Auth_Token();
//		System.out.println("Cookie"+" "+"token="+token+"");
//	}
}
