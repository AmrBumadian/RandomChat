package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Interests {
	private ArrayList<String> interests;
	private String unifiedInterestsString;

	public Interests(String interestsString) {
		setInterests(interestsString);
	}
	public void setInterests(String interestsString) {
		String[] interestsArray = interestsString.split(" ");
		interests = new ArrayList<>(Arrays.asList(interestsArray));
	}
	private void toUnifiedInterestsString() {
		StringBuilder builder = new StringBuilder();
		if (interests != null) {
			interests.sort(String::compareTo);
			for (String i:interests) {
				builder.append(i);
			}
		}
		unifiedInterestsString = new String(builder);
	}
	public String getUnifiedInterestsString() {
		if (unifiedInterestsString == null) {
			toUnifiedInterestsString();
		}
		return unifiedInterestsString;
	}
}
