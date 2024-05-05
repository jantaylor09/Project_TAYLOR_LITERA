package Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Databaze {
	

	Databaze() {
		prvkyDatabaze = new HashMap<String, Kniha>();
	}

	public Kniha getKniha(String jmenoKnihy) {
		return prvkyDatabaze.get(jmenoKnihy);
	}
	
	public Kniha getAutor(String autor) {
		return prvkyDatabaze.get(autor);
	}

	public boolean setUcebnice(String jmenoKnihy, int rok, int typ, String autor, int trida) {
		if (prvkyDatabaze.put(jmenoKnihy, new Ucebnice(jmenoKnihy, rok, typ, autor, trida)) == null)
			return true;
		else
			return false;
	}
	


	public boolean setRoman(String jmenoKnihy, int rok, int typ, String autor, String zanr) {
		if (prvkyDatabaze.put(jmenoKnihy, new Roman(jmenoKnihy, rok, typ, autor, zanr)) == null)
			return true;
		else
			return false;
	}
	

	
	public boolean vymazKnihu (String jmenoKnihy)
	{
		if (prvkyDatabaze.remove(jmenoKnihy)!=null)
			return true;
		return false;
	}
	
	public void vypisKnihy()
	{
		Set<String> seznamJmen= prvkyDatabaze.keySet();

		for(String jmeno:seznamJmen)
			System.out.println(jmeno);
	}
	
	private Map<String, Kniha> prvkyDatabaze;
	
	public void vypsatKnihu(String jmenoKnihy)
	{
		
		Kniha info=null;
		info=prvkyDatabaze.get(jmenoKnihy);
		String stav_2=null;
		
		if(info.isStav()){
			stav_2="vracene";
		}
		else
			stav_2="vypujcene";
		
		if (info instanceof Roman)
		{
			System.out.println("jmeno: " + info.getJmenoKnihy()+" rok: "+info.getRok()+" autor: "+info.getAutor()+" typ: "+info.getTyp()+" zanr "+((Roman)info).getZanr()+" stav: "+stav_2);	
		}
		if (info instanceof Ucebnice) 
		{
			System.out.println("jmeno: " + info.getJmenoKnihy()+" rok: "+info.getRok()+" autor: "+info.getAutor()+" typ: "+info.getTyp()+" trida "+((Ucebnice)info).getTrida()+" stav: "+stav_2);
		}
		
		
	}
	
	public void vypisDatabaze()
	{
		Set<String> seznamJmen= prvkyDatabaze.keySet();

		for(String jmenoKnihy:seznamJmen)
			System.out.println(jmenoKnihy);
		
	}
	
	public void vypisKnihAutora (String jmeno)
	{
		List<Kniha> vsechnyKnihy = new ArrayList<>();
		vsechnyKnihy.addAll(prvkyDatabaze.values());
		
		List<Kniha> autorKnihy = new ArrayList<>();
		for(Kniha ab: vsechnyKnihy )
		{
			if (ab.getAutor().equals(jmeno))
				autorKnihy.add(ab);
		}
	/*	if (autorKnihy.isEmpty()) {
            throw new IllegalArgumentException("Autor '" + jmeno + "' nebyl nalezen.");
		}*/
		Collections.sort(autorKnihy, Comparator.comparingInt(Kniha::getRok));
		System.out.println("Knihy autora " + jmeno + " v chronologickém pořadí:");
        for (Kniha ac : autorKnihy) {
            System.out.println(ac.getJmenoKnihy() + " (" + ac.getRok() + ")");
        }
	}
	
	public void vypisPodleZanru(int volba) {

		List<Kniha> knihy = new ArrayList<Kniha>(prvkyDatabaze.size());
		knihy.addAll(prvkyDatabaze.values());
		for (Kniha e : knihy) {

			if (e instanceof Roman) {
				String zanr = ((Roman) e).getZanr();
				int zanry = 0;
				switch (zanr) {
				case "Rytirsky":
					zanry = 1;
					break;
				case "Biograficky":
					zanry = 2;
					break;
				case "Historicky":
					zanry = 3;
					break;
				case "Profesni":
					zanry = 4;
					break;
				case "Pikaresni":
					zanry = 5;
					break;
				}
				if (volba == zanry)
					System.out.println("Nazev: " + e.getJmenoKnihy());
			}
		}
	}
	
	public boolean vypisPodleDostupnosti() {

		List<Kniha> knihy = new ArrayList<Kniha>(prvkyDatabaze.size());
		knihy.addAll(prvkyDatabaze.values());
		boolean vypujcene_neninull = false;
		for (Kniha e : knihy) {

			boolean stav = e.isStav();

			if (stav == false) {
				System.out.print("Nazev: " + e.getJmenoKnihy());
				if (e instanceof Roman)
					System.out.println("\t Román");
				if (e instanceof Ucebnice)
					System.out.println("\t Učebnice");

				vypujcene_neninull = true;
			}

		}
		return vypujcene_neninull;
	}
	
	public void ulozSoubor (String jmenoKnihy)
	{
		String zvolenaKniha=jmenoKnihy;
		String nazevSouboru = "kniha_" + zvolenaKniha + ".txt";
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(nazevSouboru)))
		{
			writer.println("Kniha: " + zvolenaKniha + "");
			Kniha a= prvkyDatabaze.get(jmenoKnihy);
			if (a instanceof Roman) {
				writer.println("Název autora: " + a.getAutor());
				writer.println("Rok vydání: " + a.getRok());
				writer.println("Typ knihy: " + "Roman");
				writer.println("Zanr: " + ((Roman)a).getZanr());
				Kniha info=null;
				info=prvkyDatabaze.get(jmenoKnihy);
				String stav_2=null;
				
				if(info.isStav()){
					stav_2="vracene";
				}
				else
					stav_2="vypujcene";
				writer.println("Dostupnost: " + stav_2);
			}
			if (a instanceof Ucebnice) {
				writer.println("Název autora: " + a.getAutor());
				writer.println("Rok vydání: " + a.getRok());
				writer.println("Typ knihy: " + "Ucebnice");
				writer.println("Trida: " + ((Ucebnice)a).getTrida());
				Kniha info=null;
				info=prvkyDatabaze.get(jmenoKnihy);
				String stav_2=null;
				
				if(info.isStav()){
					stav_2="vracene";
				}
				else
					stav_2="vypujcene";
				writer.println("Dostupnost: " + stav_2);
			}
			
				 System.out.println("Informace o knize " + zvolenaKniha 	+ " byly úspěšně uloženy do souboru " + nazevSouboru);
		       
		}
		catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
		}
		
	}
	
	
}
