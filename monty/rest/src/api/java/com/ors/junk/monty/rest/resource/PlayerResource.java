package com.ors.junk.monty.rest.resource;

import javax.ws.rs.DELETE;
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
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Player read(@PathParam("name") String name);

	@POST
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Player create(@PathParam("name") String name);

	
	@DELETE
	@Path("{name}")	
	void deletePlayer(String name);

}
