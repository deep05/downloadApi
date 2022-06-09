package com.vnit.api.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import com.vnit.api.entity.customerMst;
import com.vnit.api.repo.customerRepo;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class customerController{
@Autowired
customerRepo repo;
Map<String, String> map = new HashMap<>();
@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_customer", produces = "application/json")
	@ApiOperation(value = "Create or Update customer entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createcustomer(@RequestBody customerMst body) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {

if (body.getcname() == null) {
				error.addProperty("cname", "cname is required");
			}
				if (error.entrySet().isEmpty()) {
				status = repo.postcustomer(body);
			}
	} catch (Exception ex) {
	 ex.printStackTrace();
	}

	if (status > 0) {
	response.addProperty("id", status);
	response.addProperty("code", 200);
	response.addProperty("status", "Success");
	response.addProperty("message", "Save Successfully");
	} else {
	response.addProperty("code", 400);
	response.addProperty("status", "Failed");
	 response.addProperty("message", "Unable to save");
	}

	return response.toString();
		}
@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = "/delete_customer/{customerID}", produces = "application/json")
	@ApiOperation(value = "Delete customer entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deletecustomer(@PathVariable(name = "customerID") Integer id) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (id == null) {
				error.addProperty("id", "customerID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deletecustomer(id);
			}
	} catch (Exception ex) {
	ex.printStackTrace();
}

	if (status > 0) {
response.addProperty("code", 200);
	response.addProperty("status", "Success");
	response.addProperty("message", "Deleted Successfully");
	} else {
	response.addProperty("code", 400);
	response.addProperty("status", "Failed");
	response.addProperty("message", "Unable to delete");
	}

	return response.toString();
		}
@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_customer/{customerID}", produces = "application/json")
	@ApiOperation(value = "Get customer entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getcustomer(@PathVariable(name = "customerID") Integer id) {
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (id == null) {
				error.addProperty("id", "customerID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getcustomer(id));
			}
	} catch (Exception ex) {
	ex.printStackTrace();
	}

	response.addProperty("code", 400);
	response.addProperty("status", "Failed");
	response.addProperty("message", "Unable to get data");

	return response.toString();
		}
}
