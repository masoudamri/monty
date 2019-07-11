package com.ors.finance.fyaat.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ors.junk.monty.domain.model.Player;


@Path("players")
public interface PlayerResource {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player>  readAll();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Player read(@PathParam("id") String name);

	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	public Player create(String name);
	
}
