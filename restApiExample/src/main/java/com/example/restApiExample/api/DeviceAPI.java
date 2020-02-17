package com.example.restApiExample.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restApiExample.entity.Device;
import com.example.restApiExample.entity.Response;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/device" )
public class DeviceAPI {
	
	@GetMapping("/{stage}")
	public String getDeviceIds( HttpServletRequest request
									            , HttpServletResponse response
									            , @PathVariable String stage) {
		
		JsonObject result = new JsonObject();
		
		result.addProperty("KeyA", "valueA");
		result.addProperty("KeyB", "valueB");
		
		return result.toString();
	}
	
	@PostMapping("/")
	public Boolean setDeviceIds( HttpServletRequest request
					           , HttpServletResponse response
					           , Device device) {
		
		return true;
	}
	
	@PutMapping("/")
	public Boolean updateDeviceIds( HttpServletRequest request
					           	  , HttpServletResponse response
					           	  , Device device) {
		
		return true;
	}
	
	@DeleteMapping("/")
	public Boolean deleteDeviceIds( HttpServletRequest request
					              , HttpServletResponse response
					              , Device device) {
		
		return true;
	}
}
