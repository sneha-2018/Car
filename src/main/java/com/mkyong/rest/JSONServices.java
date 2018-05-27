package com.mkyong.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
		DatabaseManager db=new DatabaseManager();
		db.EnterCarDetails(car);
		String result = "Track saved : " + car;
		return Response.status(201).entity(result).build();
		
	}
	@PUT
	@Path("/put/{VIN}/{Brand}")
	public Response updateCarInJSON(@PathParam("VIN")String vin,@PathParam("Brand")String Brand) {
		DatabaseManager db=new DatabaseManager();
		boolean res=db.UpdateCarDetails(vin, Brand);
		if(res==true) {
			Car car=new Car();
			car=db.ReadCarDetails(vin);
			String result = "Track saved : " + car;
			return Response.status(200).entity(result).build();
		}
		else
		{
			return Response.status(404).build();		
		}
}
	@DELETE
	@Path("/delete/{VIN}")
	public Response deleteCarInJSON(@PathParam("VIN")String vin) {
		DatabaseManager db=new DatabaseManager();
		boolean res=db.DeleteCarRecord(vin);
		if(res==true) {
			return Response.status(204).build();
		}
		else
			return Response.status(404).build();
	}
}
