package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * GUI Created by akeske on 07/06/2015.
 */
public class Process {

	private File file;
	private Controller controller;

	private final Map<String, String> GREEK_TO_ROMAN = new HashMap<>();
	private final ArrayList<String> names = new ArrayList<>();
	private final ArrayList<String> surNames = new ArrayList<>();
	private final ArrayList<String> newNames = new ArrayList<>();
	private final ArrayList<String> newSurNames = new ArrayList<>();

	public Process() {

	}

	public String getNameAndSurname() {
		String temp = "";
		if(newNames.size()>0){
			temp = newNames.get(newNames.size()-1)+" ";
		}
		if(newSurNames.size()>0){
			temp += newSurNames.get(newSurNames.size()-1);
		}
		return temp;
	}

	public void setNames(String name){
		names.clear();
		name = name.toLowerCase();
		names.add(name);
	}

	public void setSurNames(String surname){
		surNames.clear();
		surname = surname.toLowerCase();
		surNames.add(surname);
	}

	public void exec(int j) throws IOException {
		if (j == 0) {
			execProc(names, j);
		}else{
			execProc(surNames, j);
		}
	}

	public String getForAD() {
		String temp = "";
		if(newNames.size()>0){
			if(newNames.get(newNames.size()-1).length()>0)
				temp = newNames.get(newNames.size()-1).charAt(0)+".";
		}
		if(newSurNames.size()>0){
			temp += newSurNames.get(newSurNames.size()-1);
		}
		return temp;
	}

	public Process(File file, Controller controller) throws IOException {
		this.file = file;
		this.controller = controller;

		names.clear();
		surNames.clear();

		newNames.clear();
		newSurNames.clear();

		readFile(file);
		execProc(names, 0);
		execProc(surNames, 1);
		saveFile();
	}

	private void readFile(File file){
		InputStream ins = null;
		Reader r = null;
		BufferedReader br = null;
		try {
			String s;
			ins = new FileInputStream(file);
			r = new InputStreamReader(ins, "UTF-8");
			br = new BufferedReader(r);
			while ((s = br.readLine()) != null) {
				s = s.toLowerCase();
				String[] pieces;
				if(s.contains(";"))
					pieces = s.split(";");
				else if (s.contains(","))
					pieces = s.split(",");
				else
					pieces = s.split(" ");
				names.add(pieces[0]);
				surNames.add(pieces[1]);
			}
		} catch (Exception e) {
		//	System.err.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Throwable t) { }
			}
			if (r != null) {
				try {
					r.close();
				} catch (Throwable t) { }
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (Throwable t) { }
			}
		}
	}

	private void saveFile(){
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("converted_names.csv"), "utf-8"));
			for(int i=0; i<newNames.size(); i++){
				writer.write(newNames.get(i)+" "+newSurNames.get(i));
				writer.write(controller.getTextDelimiter().getText());
				writer.write(newNames.get(i).charAt(0)+"."+newSurNames.get(i));
				writer.write("\n");
			}
		} catch (IOException ex) {

		} finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}

	private void execProc(ArrayList<String> names, int j) throws IOException {

		Locale.setDefault(new Locale("el_GR"));

		String fonienta = "αεηιουω";
		String chars1 = "βγδζλμνρ";
		String chars2 = "θκξπστφχψ";

		GREEK_TO_ROMAN.put("α", "a");
		GREEK_TO_ROMAN.put("ά", "a");
		GREEK_TO_ROMAN.put("β", "v");
		GREEK_TO_ROMAN.put("γ", "g");
		GREEK_TO_ROMAN.put("δ", "d");
		GREEK_TO_ROMAN.put("ε", "e");
		GREEK_TO_ROMAN.put("έ", "e");
		GREEK_TO_ROMAN.put("ζ", "z");
		GREEK_TO_ROMAN.put("η", "i");
		GREEK_TO_ROMAN.put("ή", "i");
		GREEK_TO_ROMAN.put("θ", "th");
		GREEK_TO_ROMAN.put("ι", "i");
		GREEK_TO_ROMAN.put("ί", "i");
		GREEK_TO_ROMAN.put("ΐ", "i");
		GREEK_TO_ROMAN.put("κ", "k");
		GREEK_TO_ROMAN.put("λ", "l");
		GREEK_TO_ROMAN.put("μ", "m");
		GREEK_TO_ROMAN.put("ν", "n");
		GREEK_TO_ROMAN.put("ξ", "x");
		GREEK_TO_ROMAN.put("ο", "o");
		GREEK_TO_ROMAN.put("ό", "o");
		GREEK_TO_ROMAN.put("π", "p");
		GREEK_TO_ROMAN.put("ρ", "r");
		GREEK_TO_ROMAN.put("σ", "s");
		GREEK_TO_ROMAN.put("ς", "s");
		GREEK_TO_ROMAN.put("τ", "t");
		GREEK_TO_ROMAN.put("υ", "y");
		GREEK_TO_ROMAN.put("ύ", "y");
		GREEK_TO_ROMAN.put("ΰ", "y");
		GREEK_TO_ROMAN.put("ϋ", "y");
		GREEK_TO_ROMAN.put("φ", "f");
		GREEK_TO_ROMAN.put("χ", "ch");
		GREEK_TO_ROMAN.put("ψ", "ps");
		GREEK_TO_ROMAN.put("ω", "o");
		GREEK_TO_ROMAN.put("ώ", "o");
		GREEK_TO_ROMAN.put("μπ_στην_αρχη", "b");
		GREEK_TO_ROMAN.put("μπ_στο_τελος", "b");
		GREEK_TO_ROMAN.put("μπ_στη_μεση", "mp");
		GREEK_TO_ROMAN.put("ου_τονιζεται_το_ό", "οy");
		GREEK_TO_ROMAN.put("ου_δε_τονιζεται", "ou");
		GREEK_TO_ROMAN.put("γγ", "ng");
		GREEK_TO_ROMAN.put("γξ", "nx");
		GREEK_TO_ROMAN.put("γχ", "nch");
		GREEK_TO_ROMAN.put("ευ_πριν_απο_βγδζλμνρ_και_φωνηεντα", "ev");
		GREEK_TO_ROMAN.put("ευ_πριν_απο_θκξπστφχψ_και_τελος_λεξης", "ef");
		GREEK_TO_ROMAN.put("ευ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ", "ey");
		GREEK_TO_ROMAN.put("αυ_πριν_απο_βγδζλμνρ_και_φωνηεντα", "av");
		GREEK_TO_ROMAN.put("αυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης", "af");
		GREEK_TO_ROMAN.put("αυ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ", "ay");
		GREEK_TO_ROMAN.put("ηυ_πριν_απο_βγδζλμνρ_και_φωνηεντα", "iv");
		GREEK_TO_ROMAN.put("ηυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης", "if");
		GREEK_TO_ROMAN.put("ηυ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ", "iy");

		for (String n : names) {
		//	System.out.println(n);
				char[] chars = n.toCharArray();

				String newsurname = new String();
				String rep = "";
				for (int i = 0; i < chars.length; i++) {
					rep = GREEK_TO_ROMAN.get(String.valueOf(chars[i]));
					// mp stin arxi
					if (i == 0) {
						if (i+1 < chars.length) {
							if (chars[i] == 'μ') {
								if (chars[i + 1] == 'π') {
									rep = GREEK_TO_ROMAN.get("μπ_στην_αρχη");
									if (rep != null)
										newsurname += rep;
									rep = "";
									i++;
									continue;
								}
							}
						}
					}
					// mp sto telos
					if (i == chars.length - 2) {
						if (i+1 < chars.length) {
							if (chars[i] == 'μ') {
								if (chars[i + 1] == 'π') {
									rep = GREEK_TO_ROMAN.get("μπ_στην_αρχη");
									if (rep != null)
										newsurname += rep;
									rep = "";
									i++;
									continue;
								}
							}
						}
					}
					// ou de tonizetai to o i den exei dialytika
					if (chars[i] == 'ο') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'υ') {
								rep = GREEK_TO_ROMAN.get("ου_δε_τονιζεται");
								if (rep != null)
									newsurname += rep;
								rep = "";
								i++;
								continue;
							}
						}
					}
					// γγ -> ng
					if (chars[i] == 'γ') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'γ') {
								rep = GREEK_TO_ROMAN.get("γγ");
								if (rep != null)
									newsurname += rep;
								rep = "";
								i++;
								continue;
							}
						}
					}
					// γξ -> nx
					if (chars[i] == 'γ') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'ξ') {
								rep = GREEK_TO_ROMAN.get("γξ");
								if (rep != null)
									newsurname += rep;
								rep = "";
								i++;
								continue;
							}
						}
					}
					// γχ -> nch
					if (chars[i] == 'γ') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'χ') {
								rep = GREEK_TO_ROMAN.get("γχ");
								if (rep != null)
									newsurname += rep;
								rep = "";
								i++;
								continue;
							}
						}
					}
					// ευ->ev chars1 and fonienta
					if (chars[i] == 'ε') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'υ' || chars[i + 1] == 'ύ') {
								int tempI = i + 2;
								if (tempI < chars.length) {

									// ευ->ev fonienta
									if (fonienta.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ευ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// ευ->ev chars1
									} else if (chars1.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ευ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// ευ->ef chars2
									} else if (chars2.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ευ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								} else {
									// ευ->ef

									// ευ->ey
									if (chars[i + 1] == 'έ' || chars[i + 1] == 'ϋ') {
										rep = GREEK_TO_ROMAN.get("ευ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									} else if (chars[i + 1] == 'ύ' || chars[i + 1] == 'υ') {
										rep = GREEK_TO_ROMAN.get("ευ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								}
							}
						}
					}

					// αυ->αv chars1 and fonienta
					if (chars[i] == 'α') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'υ' || chars[i + 1] == 'ύ') {
								int tempI = i + 2;
								if (tempI < chars.length) {

									// αυ->αv fonienta
									if (fonienta.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("αυ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// αυ->αv chars1
									} else if (chars1.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("αυ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// αυ->αf chars2
									} else if (chars2.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("αυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								} else {
									// αυ->αf
									if (chars[i + 1] == 'ά' || chars[i + 1] == 'ϋ') {
										rep = GREEK_TO_ROMAN.get("αυ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// αυ->αy
									} else if (chars[i + 1] == 'ύ' || chars[i + 1] == 'υ') {
										rep = GREEK_TO_ROMAN.get("αυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								}
							}
						}
					}

					// ηυ->iv chars1 and fonienta
					if (chars[i] == 'η') {
						if (i+1 < chars.length) {
							if (chars[i + 1] == 'υ' || chars[i + 1] == 'ύ') {
								int tempI = i + 2;
								if (tempI < chars.length) {

									// ηυ->iv fonienta
									if (fonienta.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ηυ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// ηυ->iv chars1
									} else if (chars1.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ηυ_πριν_απο_βγδζλμνρ_και_φωνηεντα");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// ηυ->if chars2
									} else if (chars2.indexOf(chars[i + 2]) != -1) {
										rep = GREEK_TO_ROMAN.get("ηυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								} else {
									// ηυ->if
									if (chars[i + 1] == 'ή' || chars[i + 1] == 'ϋ') {
										rep = GREEK_TO_ROMAN.get("ηυ_οταν_τονιζεται_το_ε_ή_εχει_διαλυτικα_το_υ");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
										// ηυ->iy
									} else if (chars[i + 1] == 'ύ' || chars[i + 1] == 'υ') {
										rep = GREEK_TO_ROMAN.get("ηυ_πριν_απο_θκξπστφχψ_και_τελος_λεξης");
										if (rep != null)
											newsurname += rep;
										rep = "";
										i += 1;
										continue;
									}
								}
							}
						}
					}
					if (rep != null)
						newsurname += rep;

				}

				if(newsurname.length()>1) {
					newsurname = newsurname.substring(0, 1).toUpperCase(Locale.ENGLISH) +
							newsurname.substring(1);
				}
				if(j==0){
					newNames.add(newsurname);
					System.out.println(newNames.get(newNames.size()-1));
				}else{
					newSurNames.add(newsurname);
					System.out.println(newSurNames.get(newSurNames.size() - 1));
				}
		}


	}

}
