package com.Utility;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


public class DynamicJson {

	@Test(dataProvider="booksData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json")
				.body(Payload.addBooks(isbn,aisle))
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String id = jsonPath.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name ="booksData")
	public Object[][] getData() {
		
		return new Object[][] {{"oghthf","9636"},{"tyre","7468"},{"kufc","4123"}};
		
	}
	
	
}
