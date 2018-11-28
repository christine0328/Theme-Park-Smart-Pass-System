package TPSP16S2;

import java.util.*;
import java.text.*;

public class CardList // reserve card records
{
	private ArrayList<Card> cardlist;

	public CardList() {
		cardlist = new ArrayList<Card>();

	}

	public ArrayList<Card> getcardlist() {
		return cardlist;
	}

	public void addhis(String s) {// this is to decide whether to add a total
									// new his
									// or just append a date.then operate.
		// instruction is valid.all input need to be valid when read in.

		String[] newhis = new String[3];
		newhis = s.trim().split(";");
		int countid = 0, countattr = 0;
		String[] idNum = newhis[0].trim().split("\\s");
		History newone = new History(newhis[1].trim());
		newone.addDate(newhis[2].trim());
		if (newone.getvisitdate().size() != 0) {
			for (int i = 0; i < newone.getvisitdate().size(); i++)
				for (int j = 0; j < cardlist.size(); j++) {
					if (idNum.length == 2 && idNum[0].trim().equalsIgnoreCase("ID")
							&& idNum[1].trim().equals(cardlist.get(j).getcardID())) {
						countid++;

						if (cardlist.get(j).getvisitHistory().size() > 0) {

							for (int r = 0; r < cardlist.get(j).getvisitHistory().size(); r++) {

								if (newhis[1].trim()
										.equals(cardlist.get(j).getvisitHistory().get(r).getattractname())) {

									cardlist.get(j).getvisitHistory().get(r).addDate(newhis[2].trim());
									countattr++;

									for (int k = 0; k < cardlist.get(j).getvisitHistory().get(r).getvisitdate()
											.size(); k++)
										System.out.println("*" + cardlist.get(j).getvisitHistory().get(r).getvisitdate()
												.get(k).toString());// ---no
																	// problem
								}

							}
							if (countattr == 0) {

								cardlist.get(j).getvisitHistory().add(newone);

								continue;

							}
						} else {
							cardlist.get(j).getvisitHistory().add(newone);
							for (int l = 0; l < cardlist.get(j).getvisitHistory().size(); l++) {
								System.out.println(
										"chceck!!!!!" + cardlist.get(j).getvisitHistory().get(l).getattractname());
								System.out.println(
										"chceck!!!!!" + cardlist.get(j).getvisitHistory().get(l).getvisitdate().get(0));
							} // ==--test
						}

					}
				}
			if (countid == 0) {
				System.out.println(
						"this card id is invalid, please check the input and check if the card has access to the park.");
			}
		}
	}

	public void setcardlist(ArrayList<Card> c) {
		cardlist = c;
	}

	public void addcardinfo(String info) {
		// if ID is new, add a new card, with the invalid value being neglected.
		// require at least ID, name, AND birthday.
		// if ID is existed, update the value with given info.
		int count = 0;

		System.out.println("addcard!");// --
		Card cardinfo = new Card(info);

		for (int i = 0; i < cardinfo.toStringlist().size(); i++) {
			System.out.println("$$$" + cardinfo.toStringlist().get(i));// ---test
		}

		if (cardlist.size() > 0) {

			for (int i = 0; i < cardlist.size(); i++) {

				if (cardlist.get(i).getcardID().equals(cardinfo.getcardID())) {
					count++;
					if (cardinfo.validtoset()) {
						if (cardinfo.getname() != null) {
							cardlist.get(i).setname(cardinfo.getname());
						}
						if (cardinfo.getbirthday() != null) {
							cardlist.get(i).setBday(cardinfo.getbirthday());
						}
						if (cardinfo.getStrheight() != null) {
							cardlist.get(i).setHeight(cardinfo.getStrheight());
						}
						if (cardinfo.getAddress() != null) {
							cardlist.get(i).setAddress(cardinfo.getAddress());
						}
						if (cardinfo.getvisitHistory().size() != 0) {
							System.out.println("what!");
							cardlist.get(i).setvisitHistory(cardinfo.getvisitHistory());
						}

					}

				}

			}
		}
		if (count == 0) {
			if (cardinfo.validtoadd()) {
				System.out.println("okb");// --
				cardlist.add(cardinfo);
				System.out.println("oka");// --
				System.out.println("aasize " + cardlist.size());
			}
		} else {
			System.out.println("failed to add");
		} /// try
	}

	public void delete(String id) {// delete the card record of given ID.
		String[] put = id.split("\\s");
		int count = 0;
		if (put.length == 2) {
			if (put[0].equalsIgnoreCase("ID")) {
				if (put[1].matches("\\d+")) {
					for (int i = 0; i < cardlist.size(); i++) {
						if (cardlist.get(i).getcardID().equals(put[1])) {
							cardlist.remove(i);
							count++;
						}
					}
				}
			}
		}
		if (count == 0) {
			System.out.println("delete is failed,please check input and make sure cardID is corrrect");
		}

	}

	public boolean heightisPassed(String s) {// to show whether the cardinfo can
												// meet the height requirement
												// of corresponding attraction.
		String[] parts;
		String[] id;
		boolean pass = false;

		double cardheight = 0.0;
		parts = s.split(";");
		if (parts.length == 3) {

			id = parts[0].trim().split("\\s");
			System.out.println("id[0]" + id[0]);// ---for test
			if (id[0].trim().equalsIgnoreCase("ID") && id.length == 2) {

				id[1] = id[1].trim();
				for (int i = 0; i < cardlist.size(); i++) {
					if (cardlist.get(i).getcardID().equals(id[1])) {

						cardheight = cardlist.get(i).getheightNum();
						break;
					}
				}
				System.out.println("cardheight " + cardheight);// ---test
				parts[1] = parts[1].trim();
				System.out.println("parts[1] " + parts[1]);// ---test
				if (parts[1].matches("\\d?[a-zA-Z]+\\s?[a-zA-Z]+")
						|| parts[1].matches("\\d?[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]+")) {
					System.out.println("aaaa!3 ");// ---
					if (parts[1].trim().equalsIgnoreCase("Spiderman Escape"))

					{

						if (cardheight >= 100) {
							pass = true;
						}
					} else if (parts[1].trim().equalsIgnoreCase("Ice Age Adventure")) {//>=0!
						if (cardheight <= 200 && cardheight >= 0) {
							pass = true;
						}

					} else if (parts[1].trim().equalsIgnoreCase("Canyon Blaster")) {
						if (cardheight >= 120) {
							pass = true;
						}

					} else if (parts[1].trim().equalsIgnoreCase("4D Theatre")) {
						System.out.println("aaaa! ");// ---
						pass = true;
					} else if (parts[1].trim().equalsIgnoreCase("Flow Rider")) {
						if (cardheight >= 100) {
							pass = true;
						}

					} else if (parts[1].trim().equalsIgnoreCase("Carousel")) {
						if (cardheight <= 200 && cardheight >= 0) {
							pass = true;
						}

					}
				} else
					System.out.println("please separate all parts of attraction name with only one space,"
							+ "and make sure the name doesn't include numeric or punctuation characters.");
			} else {
				pass = false;
				System.out.println("invalid cardID");
				System.out.println("no?");
			}

		}

		return pass;
	}

	public boolean ageisPassed(String s) {// to show whether the age requirement
											// is met.
		String[] parts;
		String[] id;
		boolean pass = false;
		int cardage;
		int count = 0;
		DateFormat requestDate;
		parts = s.trim().split(";");
		if (parts.length == 3) {
			requestDate = new DateFormat(parts[2].trim());

			id = parts[0].trim().split("\\s");
			if (id[0].equalsIgnoreCase("ID") && id.length == 2) {

				for (int i = 0; i < cardlist.size(); i++) {
					if (cardlist.get(i).getcardID().equals(id[1])) {
						count++;

						try {
							cardage = cardlist.get(i).getAge(requestDate.getdate());
							System.out.println("****" + cardage);// --test

						} catch (Exception e) {
							System.out.println("error about age");
							break;
						}

						if (parts[1].trim().equalsIgnoreCase("Spiderman Escape"))

						{

							if (cardage >= 8) {
								pass = true;
							}
						} else if (parts[1].trim().equalsIgnoreCase("Ice Age Adventure")) {
							if (cardage >= 8) {
								pass = true;
							}

						} else if (parts[1].trim().equalsIgnoreCase("Canyon Blaster")) {
							if (cardage >= 8) {
								pass = true;
							}

						} else if (parts[1].trim().equalsIgnoreCase("4D Theatre")) {
							pass = true;
						} else if (parts[1].trim().equalsIgnoreCase("Flow Rider")) {

							pass = true;

						} else if (parts[1].trim().equalsIgnoreCase("Carousel")) {

							pass = true;

						}
						break;
					}
				}
				if (count == 0) {
					pass = false;
					System.out.println("invalid cardID");
				}

			} else {
				System.out.println("wrong instruction");
			}
		}

		return pass;
	}

	public boolean requestisPassed(String s) {// age and height request all
												// passed or not
		return (heightisPassed(s) && ageisPassed(s));
	}

	public ArrayList<String> toStringlist() {// for print
		ArrayList<String> put = new ArrayList<String>();
		for (int i = 0; i < cardlist.size(); i++) {
			for (int j = 0; j < cardlist.get(i).toStringlist().size(); j++) {
				put.add(cardlist.get(i).toStringlist().get(j));
			}
		}
		return put;
	}

	public ArrayList<String> query(String findname) {// list the all ID and
														// corresponding visit
														// history of given
														// name,ID in order

		String[] a;
		ArrayList<String> putid = new ArrayList<String>();
		ArrayList<String> sortedid = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		Integer[] id;
		a = findname.trim().split("\\s");

		String namepart;
		if (a.length >= 2) {

			for (int i = 0; i < a.length; i++) {
				a[i] = a[i].trim();
			}
			if (a[0].equalsIgnoreCase("name")) {
				namepart = a[1];
				if (a.length > 2) {
					for (int j = 2; j < a.length; j++) {
						namepart = namepart + " " + a[j];

					}
				}
				for (int i = 0; i < cardlist.size(); i++) {

					if (cardlist.get(i).getname().equalsIgnoreCase(namepart.trim())) {
						putid.add(cardlist.get(i).getcardID());

					}
				}

				id = new Integer[putid.size()];
				for (int k = 0; k < putid.size(); k++) {
					try {
						id[k] = Integer.parseInt(putid.get(k).trim().substring(0));
						Arrays.sort(id);
						for (int h = 0; h < id.length; h++) {
							System.out.println("add====+===#*__" + id[h]);// --test
						}

					}

					catch (Exception e) {
						System.out.println("wrong input,failed to sort");
					}
				}
				for (int n = 0; n < putid.size(); n++)
					for (int m = 0; m < putid.size(); m++) {
						if (id[n] == Integer.parseInt(putid.get(m).trim().substring(0))) {
							sortedid.add(putid.get(m));
							System.out.println("add====+===#*" + putid.get(m));// --test
						}
					}

				for (int x = 0; x < sortedid.size(); x++)
					for (int y = 0; y < cardlist.size(); y++) {
						if (sortedid.get(x).equals(cardlist.get(y).getcardID())) {
							ArrayList<String> CrdHis = new ArrayList<String>();

							CrdHis.add(" ");
							CrdHis.add("ID " + cardlist.get(y).getcardID());
							for (int k = 0; k < cardlist.get(y).getStrHistory().size(); k++)
								CrdHis.add(cardlist.get(y).getStrHistory().get(k));

							// here
							for (int c = 0; c < CrdHis.size(); c++) {
								result.add(CrdHis.get(c));
								System.out.println("add====+===#*" + CrdHis.get(c));// --test
							}
							continue;
						}
					}
			}
		}
		if (result.size() > 0)
			result.remove(0);
		return result;
	}

	public String queryAge(String ins) {// age statistics, return string
		double size = 0;
		double[] scales = new double[4];
		String[] prcts = new String[4];
		String report;
		int age;
		String[] dates = ins.trim().split(";");

		if (dates.length == 3) {

			DateFormat startdate = new DateFormat(dates[0]);// problem
			DateFormat enddate = new DateFormat(dates[1]);

			for (int i = 0; i < cardlist.size(); i++)

			{
				Card onecard = cardlist.get(i);
				ArrayList<History> allhistory = onecard.getvisitHistory();
				int sign = 0;
				for (int k = 0; k < allhistory.size(); k++) {
					ArrayList<DateFormat> oneattrHis = allhistory.get(k).getvisitdate();
					if (sign > 0) {
						sign = 0;
						break;
					}
					for (int j = 0; j < oneattrHis.size(); j++) {
						if ((oneattrHis.get(j).getdate().after(startdate.getdate())
								|| (oneattrHis.get(j).getdate().equals(startdate.getdate())))
								&& (oneattrHis.get(j).getdate().before(enddate.getdate())
										|| (oneattrHis.get(j).getdate().equals(enddate.getdate())))) {

							size++;

							try {
								age = onecard.getAge(oneattrHis.get(j).getdate());

								if (age>=0 && age < 8) {
									scales[0]++;
								} else if (age >= 8 && age < 18) {
									scales[1]++;

								} else if (age >= 18 && age < 65) {
									scales[2]++;

								} else if (age > 65) {
									scales[3]++;

								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							sign++;
							break;
						}
					}
				}
			}

			NumberFormat prop = NumberFormat.getPercentInstance();
			prop.setMinimumFractionDigits(2);
			if (size != 0) {
				scales[0] = scales[0] / size;
				prcts[0] = prop.format(scales[0]);
				scales[1] = scales[1] / size;
				prcts[1] = prop.format(scales[1]);
				scales[2] = scales[2] / size;
				prcts[2] = prop.format(scales[2]);
				scales[3] = scales[3] / size;
				prcts[3] = prop.format(scales[3]);
			}

			// print format
			report = "----query " + ins + "----\n" + "Population size: " + (int) size + "\n" + "Age profile\n"
					+ "Below 8: " + prcts[0] + "\n" + "Over 8 and Below 18: " + prcts[1] + "\n"
					+ "Over 18 and Below 65: " + prcts[2] + "\n" + "Over 65: " + prcts[3] + "\n"
					+ "------------------------------------------\n\n";
		} else {
			report = "error! wrong  instruction";
			System.out.println("error! wrong  instruction");
		}
		return report;
	}

	public String queryid(String ins) {
		// to list the visithistory of a given ID,
		// including total visits, how many times the customer visit which
		// attraction,
		// and rank them
		int totalvisit = 0;

		int[] scales = new int[6];
		String[] dates = ins.trim().split(";");
		for (int i = 0; i < dates.length; i++)
			dates[i] = dates[i].trim();
		DateFormat startdate = new DateFormat(dates[0]);
		DateFormat enddate = new DateFormat(dates[1]);
		String id = dates[2].trim().substring(2).trim();

		for (int i = 0; i < cardlist.size(); i++)

		{
			Card onecard = cardlist.get(i);
			if (onecard.getcardID().equals(id)) {

				ArrayList<History> allhistory = onecard.getvisitHistory();

				for (int k = 0; k < allhistory.size(); k++) {
					ArrayList<DateFormat> oneattrHis = allhistory.get(k).getvisitdate();

					for (int j = 0; j < oneattrHis.size(); j++) {

						if ((oneattrHis.get(j).getdate().after(startdate.getdate())
								|| (oneattrHis.get(j).getdate().equals(startdate.getdate())))
								&& (oneattrHis.get(j).getdate().before(enddate.getdate())
										|| (oneattrHis.get(j).getdate().equals(enddate.getdate())))) {
							totalvisit++;

							if (allhistory.get(k).getattractname().trim().equalsIgnoreCase("Spiderman Escape")) {
								scales[0]++;

							} else if (allhistory.get(k).getattractname().trim()
									.equalsIgnoreCase("Ice Age Adventure")) {
								scales[1]++;
							} else if (allhistory.get(k).getattractname().trim().equalsIgnoreCase("Canyon Blaster")) {
								scales[2]++;
							} else if (allhistory.get(k).getattractname().trim().equalsIgnoreCase("4D Theatre"))// 8888888**
							{

								scales[3]++;
							} else if (allhistory.get(k).getattractname().trim().equalsIgnoreCase("Flow Rider")) {// r is missing!
								scales[4]++;
							} else if (allhistory.get(k).getattractname().trim().equalsIgnoreCase("Carousel")) {
								scales[5]++;
							}
						}

					}
				}
				if (totalvisit == 0) {
					System.out.println("No suitable visit record in this time period");
				}
			}
		} //

		int[] newcount = new int[scales.length];
		for (int i = 0; i < scales.length; i++) {
			newcount[i] = scales[i];
		}
		Arrays.sort(newcount);

		ArrayList<Integer> report = new ArrayList<Integer>();
		ArrayList<String> finalreport = new ArrayList<String>();
		String realreport;
		String[] attrname = new String[6];
		attrname[0] = "Spiderman Escape";
		attrname[1] = "Ice Age Adventure";
		attrname[2] = "Canyon Blaster";
		attrname[3] = "4D Theatre";
		attrname[4] = "Flow Rider";
		attrname[5] = "Carousel";
		if (totalvisit != 0) {
			for (int i = 5; i >= 0; i--) {
				if (newcount[i] != 0) {
					report.add(newcount[i]);

				}
			}

			String toadd = " ";

			System.out.println("++pkpkop=+++---" + scales[0] + " " + scales[1] + " " + scales[2] + " " + scales[3] + " "
					+ scales[4] + " " + scales[5] + " ");

			for (int m = 0; m < report.size(); m++) {
				for (int n = 0; n < scales.length; n++) {

					if (report.get(m) == scales[n]) {
						scales[n] = -1;

						if (m > 0 && report.get(m) == report.get(m - 1)) {

							toadd = attrname[n] + "; " + finalreport.get(finalreport.size() - 1);
							toadd = toadd.trim();
							finalreport.set(finalreport.size() - 1, toadd);

						} else {
							finalreport.add(attrname[n] + " " + report.get(m));

						}

						break;
					}
				}
			}
			for (int i = 0; i < finalreport.size(); i++) {
				System.out.println("%%&^*&(*ER^$*----" + finalreport.get(i));
			} // ===test
			realreport = "----query " + ins + "----\n" + "Total visits: " + totalvisit + "\n" + "Most-visited: "
					+ finalreport.get(0) + "\n";
			if (finalreport.size() > 1)
				realreport = realreport + "2nd-most-visited: " + finalreport.get(1) + "\n";
			if (finalreport.size() > 2)
				realreport = realreport + "3rd-most-visited: " + finalreport.get(2) + "\n";
			if (finalreport.size() > 3)
				realreport = realreport + "4th-most-visited: " + finalreport.get(3) + "\n";
			if (finalreport.size() > 4)
				realreport = realreport + "5th-most-visited: " + finalreport.get(4) + "\n";
			if (finalreport.size() > 5)
				realreport = realreport + "6th-most-visited: " + finalreport.get(5) + "\n";
			realreport = realreport + "----------------------------------------------" + "\n" + "\n";

		} else {
			realreport = "----query " + ins + "----\n" + "Total visits: " + totalvisit + "\n"
					+ "----------------------------------------------" + "\n";
		}
		return realreport;
	}
}
