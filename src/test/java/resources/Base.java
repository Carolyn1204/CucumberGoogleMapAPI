package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;

public class Base {
	
	public static RequestSpecification reqSpec;
	public static ResponseSpecification respSpec;
	
	public RequestSpecification reqSpecification() throws Exception {
		
		if(reqSpec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqSpec;
		}
		return reqSpec;
	}
	
	public ResponseSpecification respSpecification(int status_code) throws Exception {
		
		if(respSpec==null) {
		respSpec = new ResponseSpecBuilder().expectStatusCode(status_code).expectContentType(ContentType.JSON).build();
		return respSpec;
		}
		return respSpec;
		
	}
	
	
	public static String getGlobalValue(String key) throws Exception
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public String getAPIResource(String APIResource) {
		
		APIResources apiR = APIResources.valueOf(APIResource);
		return apiR.getResource();
		
	}
	
	

}



















