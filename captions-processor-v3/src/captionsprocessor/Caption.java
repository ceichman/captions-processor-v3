package captionsprocessor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Caption {

	private final int number;
	private final String timing;
	private String content;
	
	public Caption(int number, String timing, String content) {
		this.number = number;
		this.timing = timing;
		this.content = content;
	}
	
	public int getNumber() {
		return number;
	}
	public String getTiming() {
		return timing;
	}
	public String getContent() {
		return content;
	}
		
	protected void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caption other = (Caption) obj;
		return Objects.equals(content, other.content) && number == other.number && Objects.equals(timing, other.timing);
	}
	
	/**
	 * Converts a Caption to a List of Strings representing each word in the Caption's content.
	 * @param caption The Caption to be broken up into words
	 * @return The List of words (space-delimited strings)
	 */
	public List<String> toWords() {
		List<String> words = new LinkedList<>();
		String[] wordArray = this.content.split(" "); //store the words in array temporarily
		for (String word : wordArray) {  //copy the array into a list of chars
			words.add(word);
		}
		return words;
	}
	
	public List<Character> toCharacters() {
		char[] temp = this.content.toCharArray();  //store the chars in an array temporarily
		List<Character> chars = new LinkedList<>();
		for (char c : temp) {    //copy the array into a list of chars
			chars.add(c);
		}
		return chars;
	}
	
	public String toString() {
		return number + "\n" + timing + "\n" + content;
	}
	
	/**
	 * Returns whether the specified string contains nothing but capital letters.
	 * @param string The String to be analyzed
	 * @return A boolean
	 */
	public static boolean isAllCaps(String string) {
		char[] chars = string.toCharArray();
		for (Character c : chars) {
			if (!Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns whether a specified String is an integer using Character.isDigit().
	 * @param string The String to be analyzed
	 * @return A boolean
	 */
	public static boolean isInteger(String string) {
		char[] chars = string.toCharArray();
		for (Character c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Changes all characters in the specified String lowercase and returns the new String.
	 * @param string The String to be decapitalized
	 * @return The decapitalized String
	 */
	public static String decapitalize(String string) {
		String newString = "";
		char[] chars = string.toCharArray();
		for (Character c : chars) {
			newString = newString + Character.toLowerCase(c);
		}
		return newString;
	}

}
