package com.hrmportal.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hrmportal.entity.Employee;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


/*
 * @author : sakshilavalkar
 * This class handles rest requests from backend server
 */
public class RestController {
	
	//baseURL
	 private static final String baseURL = "http://localhost:9090/api/v1";
	 
	 
	 /*
	  * This method fetch all the employees from the backend server
	  * @return JSONArray
	  */
	public static JSONArray getEmployees() {
		RestTemplate restTemplate = new RestTemplate();
		String employees = restTemplate.getForObject(baseURL+"/getEmployees",String.class);
		JSONParser parse = new JSONParser(); 
		
		  try {
			JSONArray arr = (JSONArray)parse.parse(employees);
			for(int i=0;i<arr.size();i++) {
				System.out.println("Employee - "+i);
				JSONObject obj = (JSONObject)arr.get(i);
				System.out.println("\tEmployee Code : "+obj.get("empCode"));
				System.out.println("\tEmployee Name : "+obj.get("empName"));
				System.out.println("\tEmployee Email : "+obj.get("empEmail"));
				System.out.println("\tEmployee DOB : "+obj.get("empDOB"));
				System.out.println("\tEmployee Location : "+obj.get("empLocation"));
				
			}
			
			return arr;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
	}
	
	 /*
	  * This method fetch particular employee by employee code from the backend server
	  * 
	  */
	public static void getEmployeeById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		String employee = restTemplate.getForObject(baseURL+"/getEmployee/"+id,String.class);
		JSONParser parse = new JSONParser(); 
		  try {
			JSONObject obj = (JSONObject)parse.parse(employee);
			System.out.println("Employee - Details");
			System.out.println("\tEmployee Code : "+obj.get("empCode"));
			System.out.println("\tEmployee Name : "+obj.get("empName"));
			System.out.println("\tEmployee Email : "+obj.get("empEmail"));
			System.out.println("\tEmployee DOB : "+obj.get("empDOB"));
			System.out.println("\tEmployee Location : "+obj.get("empLocation"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	 /*
	  * @param Employee employee
	  * This method update the data of particular employee on the backend server
	  *
	  */
	public static void updateEmployee(Employee employee) {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("in rest update");
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);
	      List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	      converters.add(new MappingJackson2HttpMessageConverter());
	      restTemplate.setMessageConverters(converters);
	      
	      System.out.println(restTemplate.exchange(
	         baseURL+"/updateEmployee", HttpMethod.PUT, entity, JSONObject.class).getBody());
		System.out.println("requested");
		
	}
	
	
	/*
	 * @param MultipartFile file
	 * This method uploads employee data from csv to backend server
	 */
	public static void uploadCSV(MultipartFile file) {
		System.out.println(file);
		
		if (file.isEmpty()) {                                                                                       
			System.err.println("fileee not found");
      } else {
          try{
        	  Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        	  
              @SuppressWarnings({ "unchecked", "rawtypes" })
				CsvToBean<Employee> csvToBean = new CsvToBeanBuilder(reader).withType(Employee.class).withIgnoreLeadingWhiteSpace(true).build();
		        System.out.println(csvToBean);
              	List<Employee> employees = csvToBean.parse();
              	System.out.println(employees.get(0));
              	RestTemplate restTemplate = new RestTemplate();
              	System.out.println("before request upload");
				String response = restTemplate.postForObject(baseURL+"/employees/import", employees, String.class);
	      		System.out.println(response);
          } catch (Exception ex) {
        	  System.err.println("an error occured "+ex);
          }
      }	
	}
}
