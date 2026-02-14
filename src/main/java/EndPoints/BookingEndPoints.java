package EndPoints;

import Payloads.Booking_Payload;
import Routes.api_routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingEndPoints {

	public Response create_Booking(Booking_Payload payload) {

		Response response = RestAssured.given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        .header("Content-Type","application/json")
		        .header("Accept","application/json")
		        .body(payload)

		        .when()
		        .post(api_routes.Create_Booking_Url);


		return response;
	}

	public Response get_Booking(int BookingID) {

		Response response = RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Accept","application/json")
				
				.when()
				.get(api_routes.Get_Booking_Url+BookingID);
		
		
		return response;
	}
	
	public Response update_Booking(Booking_Payload payload, int BookingID,String token) {
		
		Response response = RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Cookie", "token=" + token)
				.header("Content-Type","application/json")
		        .header("Accept","application/json")
				.body(payload)
				
				.when()
				.put(api_routes.Update_Booking_Url+BookingID);
		
		
		return response;
	}
	
	public Response delete_Booking(int BookingID,String token) {
		
		Response response = RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Content-Type","application/json")
		        .header("Accept","application/json")
				.header("Cookie", "token=" + token)
				
				.when()
				.delete(api_routes.Delete_Booking_Url+BookingID);
		
		
		return response;
	}
}
