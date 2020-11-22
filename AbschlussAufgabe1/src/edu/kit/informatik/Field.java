package edu.kit.informatik;
/**
 * 
 * @author Bishal Maharjan
 *
 */
public class Field {
	/**
	 * Field contains only Token of String type.
	 */
	private String token;

	/**
	 * it constructs a player having certain token.
	 * @param token
	 */
	public Field( String token) {
		this.token = token;
	}

	 /**
	  * It is getter method to get token of player.
	  * @return token of player.
	  */
	public String getToken() {
		return token;
	}

	
	
	

}
