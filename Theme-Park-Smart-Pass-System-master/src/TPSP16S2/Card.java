package TPSP16S2;

import java.util.*;

public class Card {
	private String cardID;
	private String name;
	private DateFormat birthday;
	private String address;
	private String height;
	private ArrayList<History> visitHistory;

	public Card(String s) {// create one card if the format of input String is
							// valid to be divided and matched into the fields.

		cardID = null;
		name = null;
		address = null;
		birthday = null;
		height = null;
		visitHistory = new ArrayList<History>();

		String[] input;
		input = s.trim().split(";");

		for (int n = 0; n < input.length; n++) {
			System.out.println(input[n]);
		} // --test

		String[] item;
		int count = 0;
		for (int i = 0; i < input.length; i++) {

			item = input[i].trim().split("\\s");// all good

			if (item.length >= 2) {
				item[0] = item[0].trim();
				item[1] = item[1].trim();
				if (item[0].equalsIgnoreCase("ID")) {

					String Str = item[1];
					for (int y = 2; y < item.length; y++) {

						Str = Str + " " + item[y];
					}
					System.out.println("Str: " + Str);// --test
					cardID = Str;
					count++;

				} else if (item[0].equalsIgnoreCase("name")) {
					String Str = item[1];
					for (int y = 2; y < item.length; y++) {

						Str = Str + " " + item[y].trim();
					}
					name = Str;
					System.out.println("name " + name);// --test
				} else if (item[0].equalsIgnoreCase("height")) {
					String Str = item[1];
					for (int y = 2; y < item.length; y++) {

						Str = Str + " " + item[y].trim();
					}
					System.out.println("Str: " + Str);// --test
					height = Str;
				} else if (item[0].equalsIgnoreCase("birthday")) {
					String Str = item[1];
					for (int y = 2; y < item.length; y++) {

						Str = Str + " " + item[y].trim();
					}
					System.out.println("Str: " + Str);// --test
					birthday = new DateFormat(Str);
					System.out.println("bd: " + birthday.toString());// --
				}

				else if (item[0].equalsIgnoreCase("address")) {
					String Str = item[1];
					for (int y = 2; y < item.length; y++) {

						Str = Str + " " + item[y].trim();
					}
					System.out.println("address: " + Str);// --test
					address = Str;
				} else if (item[0].equalsIgnoreCase("Spiderman") && item[1].equalsIgnoreCase("Escape")) {
					History historylist = new History("Spiderman Escape");

					for (int j = 2; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					visitHistory.add(historylist);
				} else if (item[0].equalsIgnoreCase("Ice") && item[1].equalsIgnoreCase("Age")
						&& item[2].equalsIgnoreCase("Adventure")) {
					History historylist = new History("Ice Age Adventure");
					for (int j = 3; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					visitHistory.add(historylist);
				} else if (item[0].equalsIgnoreCase("Canyon") && item[1].equalsIgnoreCase("Blaster")) {
					History historylist = new History("Canyon Blaster");
					for (int j = 2; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					visitHistory.add(historylist);
				} else if (item[0].equalsIgnoreCase("4D") && item[1].equalsIgnoreCase("Theatre")) {
					System.out.println("ooo!");
					History historylist = new History("4D Theatre");
					for (int j = 2; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					for (int k = 0; k < historylist.getvisitdate().size(); k++) {
						System.out.println(historylist.getvisitdate().get(k));
					} // --test
					System.out.println(historylist.getvisitdate().size());// --test
					visitHistory.add(historylist);

				} else if (item[0].equalsIgnoreCase("Flow") && item[1].equalsIgnoreCase("Rider")) {
					History historylist = new History("Flow Rider");
					for (int j = 2; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					visitHistory.add(historylist);
				}

				else if (item[0].equalsIgnoreCase("Carousel")) {
					History historylist = new History("Carousel");
					for (int j = 1; j < item.length; j++) {
						historylist.addDate(item[j].trim());
					}
					visitHistory.add(historylist);
				}
			} else {
				break;
			}
		}
		if (count != 1) {
			cardID = null;
			name = null;
			address = null;
			birthday = null;
			height = null;
			if (visitHistory.size() > 0) {
				for (int h = 0; h < visitHistory.size(); h++) {
					visitHistory.remove(h);
				}
			}

			System.out.println("CardID is necessary");

		}
		System.out.println("cardid: " + cardID + "name " + name + "birthday " + birthday + "address" + address);// --
		for (int u = 0; u < visitHistory.size(); u++)
			System.out.println("visitHistory " + u + ":  " + visitHistory.get(u));// --test
	}

	public String getheight() {
		return height;
	}

	public boolean nameisValid() {// to see if name is valid

		if (name.trim().matches("([a-zA-Z]+\\s?+)+")) {
			return true;
		} else
			return false;
	}

	public boolean IDisValid() {// to see if the ID is valid
		if (cardID.matches("\\d+")) {
			return true;
		} else
			return false;
	}

	public boolean heightisValid() {// to see if the height is valid
		String[] h = height.trim().split("c");
		boolean see = false;
		if (h.length == 2) {
			if (h[1].trim().equals("m")) {
				System.out.println("okheight");
				h[0] = h[0].trim();
				if (h[0].matches("\\d+")) {
					if (Integer.parseInt(h[0].substring(0)) < 300 && Integer.parseInt(h[0].substring(0)) > 0)

						see = true;
				}
			}
		}
		return see;
	}

	public boolean bdayisValid() {// to see if the birthday is valid
		Date currentdate = new Date();
		if (birthday.isValid() && birthday.getdate().before(currentdate)) {
			return true;
		} else
			return false;
	}

	public boolean addressisValid() {// to see if the address is valid
		boolean see = false;
		String[] a;
		String[] b;
		if (!(address.matches("[^a-zA-Z\\s,\\d]"))) {
			address = address.trim();
			a = address.split(",");
			b = a[a.length - 1].trim().split("\\s");

			if (b[b.length - 1].trim().matches("\\d+")) {
				see = true;
			}

		}

		return see;
	}

	public Date getBday() {
		return birthday.getdate();
	}

	public DateFormat getbirthday() {
		return birthday;
	}

	public void setname(String n) {
		name = n;
	}

	public void setBday(DateFormat b) {
		birthday = b;

	}

	public void setAddress(String s) {
		address = s;
	}

	public void setvisitHistory(ArrayList<History> vh) {
		visitHistory = vh;
	}

	public void setHeight(String h) {
		height = h;
	}

	public boolean validtoset() {// to see if a cardinfo is valid to be used to
									// update an existing card and abandon the
									//invalid info. meanwhile, make sure ID is not changed.
		boolean see;
		see = true;
		if (cardID != null && !(IDisValid())) {

			see = false;
		}
		if (name != null && !(nameisValid())) {

			name = null;
			see = true;
		}
		if (height != null && !(heightisValid())) {

			height = null;
			see = true;
		}
		if (birthday != null && !(bdayisValid())) {

			birthday = null;

			see = true;
		}
		if (address != null && !(addressisValid())) {
			address = null;
			see = true;
		}
		if (cardID == null && name == null && height == null && birthday == null && address == null
				&& visitHistory == null)
			see = false;
		return see;

	}

	public boolean validtoadd() {
		// to check if the cardinfo is valid to be added as a new card,
		// make sure ID, name and birthday not null, on top of which, ignore
		// invalid info.
		boolean see;
		see = true;
		if (cardID == null || (cardID != null && !(IDisValid()))) {

			see = false;
		}
		if (name == null || (name != null && !(nameisValid()))) {

			see = false;
		}
		if (height != null && !(heightisValid())) {

			height = null;
			see = true;
		}
		if (birthday == null || (birthday != null && !(bdayisValid()))) {

			see = false;
		}
		if (address != null && !(addressisValid())) {

			address = null;
			see = true;
		}
		return see;

	}

	public ArrayList<History> getvisitHistory() {
		return visitHistory;
	}

	public ArrayList<String> getStrHistory() {// for writing to file
		ArrayList<String> StrHis = new ArrayList<String>();
		String attrname;
		String history = "";
		if (visitHistory != null) {
			for (int i = 0; i < visitHistory.size(); i++) {
				attrname = visitHistory.get(i).getattractname();
				history = "";
				for (int j = 0; j < visitHistory.get(i).getvisitdate().size(); j++) {
					history = history + " " + visitHistory.get(i).getvisitdate().get(j).toString();
					history = history.trim();

				}
				StrHis.add(attrname + "  " + history);
				for (int y = 0; y < StrHis.size(); y++)
					System.out.println("!!!!!!?--" + StrHis.get(y));// --test
			}
		} else {
			StrHis = null;
			System.out.println("no visit history recorded for this customer");
		}
		return StrHis;
	}

	public String getAddress() {
		return address;

	}

	public String getcardID() {
		return cardID;
	}

	public String getStrheight() {
		return height;
	}

	public String getname() {
		return name;
	}

	public double getheightNum() {
		double heightNum;
		if (height != null) {
			String[] h = height.trim().split("c");
			heightNum = Double.parseDouble(h[0]);
		} else {
			heightNum = -1;
		}
		return heightNum;
	}

	public int getAge(Date visitdate) throws Exception {// calculate the age of
														// card when visiting
														// the theme park
														// according to birthday
		int age = -1;
		if (birthday != null) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(visitdate);
			int visitYear = cal.get(Calendar.YEAR);
			int visitMonth = cal.get(Calendar.MONTH);
			int visitDay = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(birthday.getdate());
			int bYear = cal.get(Calendar.YEAR);
			int bMonth = cal.get(Calendar.MONTH);
			int bDay = cal.get(Calendar.DAY_OF_MONTH);

			age = visitYear - bYear;

			if (visitMonth < bMonth) {
				age--;
			} else if (visitMonth == bMonth) {
				if (visitDay < bDay)
					age--;
			}

		} else {
			System.out.println("No birthday info");
		}
		return age;
	}

	public ArrayList<String> toStringlist() {// for writing to file
		ArrayList<String> put = new ArrayList<String>();
		if (cardID != null) {
			put.add("ID" + "        " + cardID);
		}
		if (name != null) {
			put.add("name" + "      " + name);
		}
		if (birthday != null) {
			put.add("birthday" + "  " + birthday.toString());
		}

		if (height != null) {
			put.add("height" + "    " + height);

		}
		if (address != null) {
			put.add("address" + "   " + address);

		}
		if (visitHistory != null)

		{
			String attrname = null;
			String datesforone = "";
			for (int i = 0; i < visitHistory.size(); i++) {
				attrname = visitHistory.get(i).getattractname() + "  ";
				datesforone = "";
				for (int j = 0; j < visitHistory.get(i).getvisitdate().size(); j++) {

					datesforone = datesforone + " " + visitHistory.get(i).getvisitdate().get(j).toString();
					datesforone = datesforone.trim();
				}
				put.add(attrname + datesforone);

			}

		}
		put.add("");
		return put;

	}
}
