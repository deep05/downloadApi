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
import com.vnit.api.entity.screenMst;
import com.vnit.api.repo.screenRepo;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class screenController{
@Autowired
screenRepo repo;
Map<String, String> map = new HashMap<>();
@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(path = "/post_screen", produces = "application/json")
	@ApiOperation(value = "Create or Update screen entity", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String createscreen(@RequestBody screenMst body) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {

if (body.getscreenpurpose() == null) {
				error.addProperty("screenpurpose", "screenpurpose is required");
			}
if (body.getscreenname() == null) {
				error.addProperty("screenname", "screenname is required");
			}
if (body.getscreentype() == null) {
				error.addProperty("screentype", "screentype is required");
			}
if (body.getscreendate() == null) {
				error.addProperty("screendate", "screendate is required");
			}
				if (error.entrySet().isEmpty()) {
				status = repo.postscreen(body);
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
	@DeleteMapping(path = "/delete_screen/{screenID}", produces = "application/json")
	@ApiOperation(value = "Delete screen entity", httpMethod = "DELETE")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String deletescreen(@PathVariable(name = "screenID") Integer id) {
		Integer status = 0;
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (id == null) {
				error.addProperty("id", "screenID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				status = repo.deletescreen(id);
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
	@GetMapping(path = "/get_screen/{screenID}", produces = "application/json")
	@ApiOperation(value = "Get screen entity", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getscreen(@PathVariable(name = "screenID") Integer id) {
	JsonObject response = new JsonObject();
	JsonObject error = new JsonObject();
 		try {
				if (id == null) {
				error.addProperty("id", "screenID is required");
			}
			
			if (error.entrySet().isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(repo.getscreen(id));
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
