package TPSP16S2;

import java.util.*;
import java.text.*;

public class DateFormat {
	private String dateStr;
	private Date date;
	private static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

	public DateFormat() {
		dateStr = null;
		date = null;

	}

	public DateFormat(String s) {// string-->date,check if the String format is
									// valid to be a date.
		dateStr = s;
		String[] datepart;

		if (dateStr.trim().matches("\\d+\\D\\d+\\D\\d+")) {

			datepart = dateStr.trim().split("\\D");
			if (datepart.length == 3) {
				for (int k = 0; k < 3; k++) {
					datepart[k] = datepart[k].trim();
				}
				for (int i = 0; i < 2; i++) {
					if (datepart[i].length() < 2) {
						if (Integer.parseInt(datepart[i]) != 0) {
							datepart[i] = "0" + datepart[i];
						} else {
							dateStr = null;
						}
					}
					dateStr = datepart[0] + "/" + datepart[1] + "/" + datepart[2];
				}

			} else {
				dateStr = null;
			}

		} else {
			dateStr = null;
		}

		try {
			date = dateformat.parse(dateStr);
			System.out.println("add====+===???***" + dateStr);// --test
			System.out.println("add====+===???***" + dateformat.format(date));// --test
			if (!(dateformat.format(date).equals(dateStr))) {
				date = null;
				System.out.println("add====+===???***");// --test
			}

		} catch (Exception e) {
			date = null;
		}

	}

	public boolean isValid() {
		if (date == null) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {// string, when null is possible
		if (date != null)
			return dateformat.format(date);
		else
			return null;

	}

	public String getdateStr() {
		return dateStr;
	}

	public Date getdate() {// Date type
		return date;
	}
}