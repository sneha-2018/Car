package com.mkyong.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.Car;
import com.mkyong.DatabaseManager;
@Path("/Car")
public class JSONServices {
	@GET
	@Path("/get/{VIN}")
	@Produces(MediaType.APPLICATION_JSON)
	//It will take VIN number of car and then find it in database n will display that record accordingly
	public Car getCarInJSON(@PathParam("VIN")String vin) {

		Car car = new Car();
		DatabaseManager db=new DatabaseManager();
		car= db.ReadCarDetails(vin);
		/*car.setVIN(vin);
		car.setBrand_Name("TATA");
		car.setHas_Hatchback(false);
		car.setLuxury_level("Medium");
		car.setModel_Name("indigo");
		car.setSeater_Type("4-seaters");*/
		return car;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCarInJSON(Car car) {

		String result = "Track saved : " + car;
		return Response.status(201).entity(result).build();
		
	}
	

}
