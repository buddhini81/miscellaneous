/**
 * 
 */
package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 * @author buddhini
 *
 */
public class ValidationUtil {

	public static boolean validateArguments(String key, String value, Map<String, List<String>> configProperties) {
		boolean valid = false;

		List<String> arguments = configProperties.get("cmd.arguments");
		List<String> durations = configProperties.get("cmd.arguments.durations");

		if (arguments.contains(key)) {
			valid = true;
			if (key.equals("startDate") && !isValidDateString(value)) {
				valid = false;
				System.out.println("Invalid Date Format! startDate has to be in yyyy-MM-dd.HH:mm:ss format.");
			}

			if (key.equals("duration") && !durations.contains(value)) {
				valid = false;
				System.out.println("Invalid duration Value! duration can take only hourly, daily as inputs.");
			}

			if (key.equals("threshold")) {
				try {
					Integer.parseInt(value);
				} catch (NumberFormatException ne) {
					valid = false;
					System.out.println("Invalid threshold! threshold has to be an Integer.");
				}
			}
		} else {
			valid = false;
			System.out.println("Invalid Argument!");
		}

		return valid;
	}

	private static boolean isValidDateString(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");

		try {
			LocalDateTime.parse(dateStr, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
