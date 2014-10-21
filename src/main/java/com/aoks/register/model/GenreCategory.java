package com.aoks.register.model;

/**
 * Defines values for a person sex category
 * .
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public enum GenreCategory {

	NONE("general_empty"),
    MALE("model_genre_male"),
    FEMALE("model_genre_female");
	
	private String genre;
	
	GenreCategory(String genre){
		this.genre = genre;
	}
	
	public String getGenre() 			{ return genre; }
	public void setGenre(String genre)  { this.genre = genre; }
	
}
