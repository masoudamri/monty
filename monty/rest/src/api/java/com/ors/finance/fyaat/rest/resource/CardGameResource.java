package com.ors.finance.fyaat.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface CardGameResource {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<?>  readAll();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object read(@PathParam("id") long id);

	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	public  void create(Object resource);
	
	@PATCH
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public  void update(@PathParam("id") long id, Object resource);
	
}
