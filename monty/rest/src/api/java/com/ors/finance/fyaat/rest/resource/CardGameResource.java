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

import com.ors.junk.monty.domain.model.CardGame;


@Path("card-games")
public interface CardGameResource {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CardGame>  readAll();

	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardGame read(@PathParam("name") String name);

	@POST
	@Path("{name}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public CardGame create(@PathParam("name") String name);
	
	@PATCH
	@Path("{name}/shuffle")
	@Consumes(MediaType.APPLICATION_JSON) 
	public  void shuffle(@PathParam("name") String name);
	
	@PATCH
	@Path("{name}/deal/{playerName}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public  void deal(@PathParam("name") String name,@PathParam("playerName") String playerName);

	@PATCH
	@Path("{name}/add-deck")
	@Consumes(MediaType.APPLICATION_JSON) 
	public  void addDeck(@PathParam("name") String name);

}
