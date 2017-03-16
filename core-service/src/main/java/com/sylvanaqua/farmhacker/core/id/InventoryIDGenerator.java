package com.sylvanaqua.farmhacker.core.id;

/**
 * Generates alphanumeric sequential IDs for inventory identification
 */
public class InventoryIDGenerator {

	private String currentID;
	private static char [] characterArray = 
		{'0','1','2','3','4','5','6','7','8','9','A','B','C','D'};
	
	public InventoryIDGenerator(String currentID) {
		this.currentID = currentID;
	}
	
	/**
	 * Returns the next ID in the sequence, starting with 0000.
	 * 
	 * @return Sequential ID
	 */
	public String getNextID() {
		
		if(invalidID(currentID)) {
			return "0";
		}
		else {
			currentID = incrementID("");
		}
		
		return currentID;
	}
	
	private String incrementID(String idSoFar) {
		
		int position = idSoFar.length();
		int currentIDPosition = currentID.length() - (position+1);
		
		// If the idSoFar is longer than the current ID, then we've added another digit
		// in the last iteration, so we're done (e.g. D becomes 01)
		if(idSoFar.length() > currentID.length()) {
			return idSoFar;
		}
		
		// If we're past the first digit, and the digit to the right didn't roll over,
		// then there's no need to process anything. So return the id so far plus the
		// balance of remaining digits.
		if(position >= 1 && idSoFar.charAt(0) != characterArray[0]) {
			return currentID.substring(0, currentIDPosition+1) + idSoFar;
		}
		
		// Get the next character in the current ID. This character will not exist if we're incrementing
		// to the next digit (e.g. from D -> 01), indicated by currentIDPosition being less than zero.
		// If that's the case, return the first character in the sequence as the next char.
		char nextChar = currentIDPosition < 0 ? 
						  (characterArray[0]) :
				          (currentID.charAt(currentIDPosition));
		
		if(position == 0 || idSoFar.charAt(0) == characterArray[0]) {
			nextChar = getNextChar(nextChar);
		}
		
		return incrementID((nextChar + idSoFar));
	}
	
	
	private char getNextChar(char charToIncrement) {
		
		int positionInArray = getPositionInArray(charToIncrement);

		return positionInArray == (characterArray.length-1) ?
								  characterArray[0] : characterArray[positionInArray+1];
	}
	
	private int getPositionInArray(char character) {
		for(int i = 0; i < characterArray.length; i++) {
			if(character == characterArray[i]) {
				return i;
			}
		}
		
		return 0;
	}
	
	private boolean invalidID(String id) {
		
		return (currentID == null || "".equals(currentID));
	}
}
