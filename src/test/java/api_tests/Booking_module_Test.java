package api_tests;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import API_Helpers.PasswordManager;
import EndPoints.BookingEndPoints;
import Payloads.Booking_Payload;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Booking_module_Test {

	Faker faker;
	ObjectMapper obj;
	Booking_Payload bookingPayload;
	BookingEndPoints endpoints;
	public static int Boking_Id;
	static Logger log;
	static String payload;

	
	@BeforeClass
	public void SetUp() {
		
		faker = new Faker();
		obj = new ObjectMapper();
		bookingPayload  = new Booking_Payload();
		endpoints = new BookingEndPoints();
		
		Booking_Payload.BookingDates bookingDates = new Booking_Payload.BookingDates();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date checkinDate = null;
		
		try {
			checkinDate = dateformat.parse("2026-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date checkoutdate = null;
		
		try {
			checkoutdate = dateformat.parse("2026-01-04");
		} catch (ParseException e) {
			// TODO: handle exception
		}
		
		bookingDates.setCheckin(checkinDate);
		bookingDates.setCheckout(checkoutdate);
		
		bookingPayload.setFirstname(faker.name().firstName());
		bookingPayload.setLastname(faker.name().lastName());
		bookingPayload.setTotalprice(faker.number().numberBetween(111, 999));
		bookingPayload.setDepositpaid(true);
		bookingPayload.setBookingdates(bookingDates); 
		bookingPayload.setAdditionalneeds("Breakfast");
		
		
		try {
			payload = obj.writerWithDefaultPrettyPrinter().writeValueAsString(bookingPayload);
			//System.out.println(payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	public void create_Booking_Test() {
		
		Response response = endpoints.create_Booking(bookingPayload);
		
	    Assert.assertEquals(response.statusCode(), 200);
	    Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
	    
		Boking_Id = response.jsonPath().getInt("bookingid");
	}
	
	@Test(priority = 2)
	public void get_Booking_Test() {
		
		Response response = endpoints.get_Booking(Boking_Id);
		
		Assert.assertEquals(response.statusCode(), 200);
	    Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
		
	    
	    MatcherAssert.assertThat(response.body().asString(), 
	    		JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/Schema/booking.json")));
	}
	
	@Test(priority = 3)
	public void Update_Booking_Test() {
		
		Response response= endpoints.update_Booking(bookingPayload, Boking_Id, PasswordManager.init_Auth_Token());
          
		Assert.assertEquals(response.statusCode(), 200);
	    Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");              
	}
	

	@Test(priority = 4)
	public void Delete_Booking_Test() {
	
		Response response= endpoints.delete_Booking(Boking_Id, PasswordManager.init_Auth_Token());
		                  
		Assert.assertEquals(response.statusCode(), 201);
	    Assert.assertEquals(response.statusLine(),"HTTP/1.1 201 Created");                        
	}
}
