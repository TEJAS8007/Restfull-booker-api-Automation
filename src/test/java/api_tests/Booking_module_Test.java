package api_tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import EndPoints.BookingEndPoints;
import Payloads.Booking_Payload;

public class Booking_module_Test {

	Faker faker;
	ObjectMapper obj;
	Booking_Payload bookingPayload;
	BookingEndPoints endpoints;
	public int Boking_Id;
	static Logger log;
	static String payload;

	
	@BeforeClass
	public void SetUp() {
		
		faker = new Faker();
		obj = new ObjectMapper();
		bookingPayload  = new Booking_Payload();
		
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
			checkinDate = dateformat.parse("2026-01-04");
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
			System.out.println(payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
