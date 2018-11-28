package TPSP16S2;

import java.io.*;

import java.util.Scanner;

public class Processor {
	private CardList cardlist;
	private File cardsFile;
	private File instruction;
	private File resultFile;
	private File reportFile;

	public Processor(String[] s) {
		cardlist = new CardList();

		cardsFile = new File(s[0]);

		instruction = new File(s[1]);

		resultFile = new File(s[2]);

		reportFile = new File(s[3]);

	}

	public CardList getcardlist() {
		return cardlist;
	}

	public void readcardsFile() {
		try {
			Scanner cardsRead = new Scanner(cardsFile);

			String[] a;
			String parameter = "";

			while (cardsRead.hasNext()) {
				try {

					String oneline = cardsRead.nextLine();

					if (oneline != null && !(oneline.isEmpty())) {
						a = oneline.trim().split("\\s+");
						a[0] = a[0].trim();
						if (!(a[0].equalsIgnoreCase("ID") || a[0].equalsIgnoreCase("name")
								|| a[0].equalsIgnoreCase("birthday") || a[0].equalsIgnoreCase("address")
								|| a[0].equalsIgnoreCase("height") || a[0].equalsIgnoreCase("Spiderman")
								|| a[0].equalsIgnoreCase("SpidermanEscape") || a[0].equalsIgnoreCase("Ice")
								|| a[0].equalsIgnoreCase("IceAgeAdventure") || a[0].equalsIgnoreCase("Canyon")
								|| a[0].equalsIgnoreCase("CanyonBlaster") || a[0].equalsIgnoreCase("4D")
								|| a[0].equalsIgnoreCase("4DTheatre") || a[0].equalsIgnoreCase("Flow")
								|| a[0].equalsIgnoreCase("FlowRider") || a[0].equalsIgnoreCase("Carousel"))) {
							parameter = parameter + oneline;
							parameter = parameter.trim();
							System.out.println("parameter: " + parameter);// --test
						} else {

							parameter = parameter + ";" + oneline;
							parameter = parameter.trim();
							System.out.println("parameter: " + parameter);// --test
						}
					} else {
						if (parameter.charAt(0) == ';')
							parameter = parameter.trim().substring(1);
						System.out.println("parameter: " + parameter);// --
						cardlist.addcardinfo(parameter);
						parameter = "";

						continue;
					}

				} catch (Exception e) {
					System.out.println("This item is not valid");
					continue;
				}
			}
			cardsRead.close();
			if (parameter.charAt(0) == ';')
				parameter = parameter.substring(1);
			System.out.println("parameter: " + parameter);// --
			cardlist.addcardinfo(parameter);
			System.out.println("size " + cardlist.getcardlist().size());
		}

		catch (FileNotFoundException e) {
			System.out.println(" file not found");
		}
		System.out.println("###!!?>>-->>>>>>>>>>>>>>>>>>>>>>>>"+cardlist.getcardlist().get(0).getheight());//0
	}

	public void readInstruction() {
		try {
			Scanner insRead = new Scanner(instruction);
			while (insRead.hasNextLine()) {
				String oneins = insRead.nextLine();
				Scanner oneinsRead = new Scanner(oneins);
				String keyword, parameter;
				if (oneinsRead.hasNext()) {
					keyword = oneinsRead.next().trim();
					if (oneinsRead.hasNextLine()) {
						parameter = oneinsRead.nextLine().trim();
						if (keyword.equalsIgnoreCase("add")) {
							System.out.println("add!" + parameter);// --test
							cardlist.addcardinfo(parameter);
							System.out.println("!!!!" + cardlist.getcardlist().get(0).getStrHistory());// --for
																										// test
						} else if ((keyword.equalsIgnoreCase("query"))) {
							String splitpara = parameter;
							String[] a, b;
							a = splitpara.trim().split("\\s");

							if (a.length > 2 && a[0].trim().equalsIgnoreCase("name")) {

								cardlist.query(parameter);// pass
								try {
									PrintWriter writeResult = new PrintWriter(new FileWriter(reportFile, true));
									writeResult.printf("-----%s-----\n", oneins);
									System.out.println("add====+===query " + cardlist.query(parameter).size());// --test

									for (int i = 0; i < cardlist.query(parameter).size(); i++) {
										writeResult.printf("%s\n", cardlist.query(parameter).get(i));

									}
									writeResult.print("-------------------------------------" + "\n" + "\n");
									writeResult.close();
								} catch (FileNotFoundException e) {
									System.out.println("File Not Found.failed to query");
								}
							} else {
								b = splitpara.trim().split(";");
								for (int i = 0; i < b.length; i++)
									System.out.println("add====+===" + b[i]);// --test
								if (b.length >= 3 && b[2].trim().equalsIgnoreCase("age")) {
									String report = cardlist.queryAge(parameter);

									try {
										PrintWriter writeResult = new PrintWriter(new FileWriter(reportFile, true));
										writeResult.print(report);
										writeResult.close();
									} catch (FileNotFoundException e) {
										System.out.println("FileNotFound");
									}
								}

								else if (b.length >= 3 && b[2].trim().substring(0, 2).equalsIgnoreCase("ID")) {

									String report = cardlist.queryid(parameter);
									try {

										PrintWriter writeResult = new PrintWriter(new FileWriter(reportFile, true));
										writeResult.print(report);
										writeResult.close();
									} catch (FileNotFoundException e) {
										System.out.println("FileNotFound");
									}
								}
							}

						}

						else if (keyword.trim().equalsIgnoreCase("request")) {

							String[] a = parameter.trim().split(";");

							if (cardlist.heightisPassed(parameter) && cardlist.ageisPassed(parameter)) {

								cardlist.addhis(parameter);

								System.out.println("###!!?>>" + cardlist.getcardlist().get(1).getvisitHistory().size());// test

							} else {
								int cheight = -1;
								String[] parts;
								String[] id;
								DateFormat requestDate;
								int cardage = -1;

								parts = parameter.split(";");
								if (parts.length == 3) {
									requestDate = new DateFormat(parts[2].trim());
									if (requestDate.isValid()) {

										id = parts[0].trim().split("\\s");
										System.out.println("id[0]" + id[0]);// ---
										if (id[0].trim().equalsIgnoreCase("ID") && id.length == 2) {

											id[1] = id[1].trim();
											for (int i = 0; i < cardlist.getcardlist().size(); i++) {
												if (cardlist.getcardlist().get(i).getcardID().equals(id[1])) {

													cheight = i;
													break;
												}
											}
											for (int i = 0; i < cardlist.getcardlist().size(); i++) {
												if (cardlist.getcardlist().get(i).getcardID().equals(id[1])) {

													cardage = i;

												}
											}
											System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + cardlist.getcardlist().get(0).getheight());// ---
											if (cardlist.heightisPassed(parameter) == false) {
												System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
												if (cardlist.getcardlist().get(cheight).getheight() == null) {
													if (cardlist.ageisPassed(parameter) == true) {
														try {
															PrintWriter writeResult = new PrintWriter(
																	new FileWriter(reportFile, true));
															writeResult.printf(
																	"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: height information is missing\n---------------------------------------------------\n\n",
																	a[0], a[1], a[2], a[1], a[2]);
															writeResult.close();
														} catch (FileNotFoundException e) {
															System.out.println("File Not Found");
														}
													} else// age not true
													{
														if (cardlist.getcardlist().get(cardage).getbirthday() == null
																&& (parts[1].trim().equalsIgnoreCase("Spiderman Escape")
																		|| parts[1].trim()
																				.equalsIgnoreCase("Ice Age Adventure")
																		|| parts[1].trim()
																				.equalsIgnoreCase("Canyon Blaster"))) {
															try {
																PrintWriter writeResult = new PrintWriter(
																		new FileWriter(reportFile, true));
																writeResult.printf(
																		"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: height information is missing\n\t birthday is missing\n---------------------------------------------------\n\n",
																		a[0], a[1], a[2], a[1], a[2]);
																writeResult.close();
															} catch (FileNotFoundException e) {
																System.out.println("File Not Found");
															}
														} else {
															try {
																PrintWriter writeResult = new PrintWriter(
																		new FileWriter(reportFile, true));
																writeResult.printf(
																		"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: height information is missing\n\t age requirement is not met\n---------------------------------------------------\n\n",
																		a[0], a[1], a[2], a[1], a[2]);
																writeResult.close();
															} catch (FileNotFoundException e) {
																System.out.println("File Not Found");
															}
														}
													}
												}

												else {// not met not null
													if (cardlist.ageisPassed(parameter) == true)// 4
													{

														try {
															PrintWriter writeResult = new PrintWriter(
																	new FileWriter(reportFile, true));
															writeResult.printf(
																	"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: height requirement is not met\n---------------------------------------------------\n\n",
																	a[0], a[1], a[2], a[1], a[2]);
															writeResult.close();
															System.out.println("###!!yeah");// --***!!
														} catch (FileNotFoundException e) {
															System.out.println("File Not Found");
														}
													} else {// 2+3
														if (cardlist.getcardlist().get(cardage).getbirthday() == null
																&& (parts[1].trim().equalsIgnoreCase("Spiderman Escape")
																		|| parts[1].trim()
																				.equalsIgnoreCase("Ice Age Adventure")
																		|| parts[1].trim()
																				.equalsIgnoreCase("Canyon Blaster"))) {
															try {
																PrintWriter writeResult = new PrintWriter(
																		new FileWriter(reportFile, true));
																writeResult.printf(
																		"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: birthday is missing\n\t height requirement is not met\n---------------------------------------------------\n\n",
																		a[0], a[1], a[2], a[1], a[2]);
																writeResult.close();
															} catch (FileNotFoundException e) {
																System.out.println("File Not Found");
															}

														} else {
															try {
																PrintWriter writeResult = new PrintWriter(
																		new FileWriter(reportFile, true));
																writeResult.printf(
																		"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: age requirement is not met\n\t height requirement is not met\n---------------------------------------------------\n\n",
																		a[0], a[1], a[2], a[1], a[2]);
																writeResult.close();
															} catch (FileNotFoundException e) {
																System.out.println("File Not Found");
															}
														}
													}
												}
											}
											if (cardlist.heightisPassed(parameter) == true) {
												if (cardlist.ageisPassed(parameter) == false) {
													if (cardlist.getcardlist().get(cardage).getbirthday() == null
															&& (parts[1].trim().equalsIgnoreCase("Spiderman Escape")
																	|| parts[1].trim()
																			.equalsIgnoreCase("Ice Age Adventure")
																	|| parts[1].trim()
																			.equalsIgnoreCase("Canyon Blaster"))) {
														try {
															PrintWriter writeResult = new PrintWriter(
																	new FileWriter(reportFile, true));
															writeResult.printf(
																	"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: birthday is missing\n---------------------------------------------------\n\n",
																	a[0], a[1], a[2], a[1], a[2]);
															writeResult.close();
														} catch (FileNotFoundException e) {
															System.out.println("File Not Found");
														}
													} else {
														try {
															PrintWriter writeResult = new PrintWriter(
																	new FileWriter(reportFile, true));
															writeResult.printf(
																	"----requset %s; %s; %s----\nRequest Denied:%s %s\nReasons: age requirement is not met\n---------------------------------------------------\n\n",
																	a[0], a[1], a[2], a[1], a[2]);
															writeResult.close();
														} catch (FileNotFoundException e) {
															System.out.println("File Not Found");
														}
													}
												}
											}
										}
									}
								} // ====end

							}

						} else if (keyword.equalsIgnoreCase("delete")) {
							cardlist.delete(parameter);
						}
					} else {
						continue;
					}
					oneinsRead.close();

				} else {
					continue;
				}

			}
			insRead.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveResult() {
		try {
			PrintWriter result = new PrintWriter(resultFile);
			for (int i = 0; i < cardlist.toStringlist().size(); i++) {
				System.out.println(cardlist.toStringlist().get(i));
				result.println(cardlist.toStringlist().get(i));
			}
			result.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found, failed to save");
		}
	}

}
