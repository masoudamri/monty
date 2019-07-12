package com.ors.junk.monty.rest.resource;

import javax.ws.rs.DELETE;
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
	@Path("{gameName}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardGame read(@PathParam("gameName") String gameName);

	@POST
	@Path("{gameName}")
	@Produces(MediaType.APPLICATION_JSON) 
	public CardGame create(@PathParam("gameName") String gameName);
	
	@PATCH
	@Path("{gameName}/shuffle")
	@Produces(MediaType.APPLICATION_JSON) 
	public  void shuffle(@PathParam("gameName") String gameName);

	@PATCH
	@Path("{gameName}/add-player/{playerName}")
	@Produces(MediaType.APPLICATION_JSON) 
	public  void addPlayer(@PathParam("gameName") String gameName,@PathParam("playerName") String playerName);

	
	@PATCH
	@Path("{gameName}/deal/{playerName}/{times}")
	@Produces(MediaType.APPLICATION_JSON) 
	public  void deal(@PathParam("gameName") String gameName,@PathParam("playerName") String playerName,@PathParam("times") Integer times);

	@PATCH
	@Path("{gameName}/add-deck")
	@Produces(MediaType.APPLICATION_JSON) 
	public  void addDeck(@PathParam("gameName") String gameName);


	@GET
	@Path("{gameName}/undealt")	
	@Produces(MediaType.APPLICATION_JSON)
	public Object undealt(@PathParam("gameName") String gameName);

	@GET
	@Path("{gameName}/deck-tally")	
	@Produces(MediaType.APPLICATION_JSON)
	public Object deckTally(@PathParam("gameName") String gameName);


	@GET
	@Path("{gameName}/player-scores")
	@Produces(MediaType.APPLICATION_JSON)
	public Object playerScores(@PathParam("gameName") String gameName);

	
	@DELETE
	@Path("{gameName}")	
	@Produces(MediaType.APPLICATION_JSON)
	void deleteCardGame(String gameName);

}
