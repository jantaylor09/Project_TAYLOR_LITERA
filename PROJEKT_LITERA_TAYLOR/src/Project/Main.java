package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static int pouzeCelaCisla(Scanner sc) {
		int cislo = 0;
		try {
			cislo = sc.nextInt();
			sc.nextLine();
			} catch (Exception e) {
			System.out.println("Nastala vyjimka typu " + e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;

	}

	/* enum StavDostupnpsti{VYPUJCENE,VRACENE,NONE}; */

	public static void main(String[] args) {	
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Databaze mojeDatabaze=new Databaze();
		int idx;
		float prumer;
		int volba;
		int zanr;
		int trida;
		String jmenoKnihy;
		String jmenoKnihy_2;
		String autor;
		String zanr_2 = null;
		String typ_2 = null;
		int typ;
		
		int rok;
		int uprava;
		int mezistav;
		boolean run=true;
		
		File file = new File("knihovna.txt");
		
		mojeDatabaze.setRoman("To", 1928, 1, "Steven K", "Horor");
		mojeDatabaze.setRoman("To2", 1929, 1, "Steven K", "Horor");
		mojeDatabaze.setUcebnice("Atlas", 19992, 2, "Yuan", 3);
		mojeDatabaze.setUcebnice("Historie", 2015, 2, "Steven", 5);

		
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni nove knihy");
			System.out.println("2 .. uprava knihy");
			System.out.println("3 .. smazani knihy");
			System.out.println("4 .. oznaceni knihy (vypujcene/vracene) ");
			System.out.println("5 .. vypis knih v databazi");
			System.out.println("6 .. vyhledani knihy ");
			System.out.println("7 .. vypis knih autora v chronologickém poradi ");
			System.out.println("8 .. vypis knih podle zanru");
			System.out.println("9 .. vypis vypujcenych knih (ucebnice/roman)");
			System.out.println("10 .. ulozeni knihy do souboru");
			System.out.println("11 .. nacteni knihy do souboru");
			System.out.println("12 .. ukonceni aplikace ");
			
			volba=pouzeCelaCisla(sc);
			 
			switch(volba)
			{
				case 1:
					System.out.println("zadejte nazev knihy");
					jmenoKnihy=sc.nextLine();
					
					System.out.println("Zadejte typ:");
					System.out.println("1 .. Romany");
					System.out.println("2 .. ucebnice");
					typ=Main.pouzeCelaCisla(sc);
					
					switch(typ)
					{
					case 1:
						typ_2="Roman";
						System.out.println("Zadejte zanr");
						System.out.println("1 .. Rytířský román");
						System.out.println("2 .. Biografický román");
						System.out.println("3 .. Historický román");
						System.out.println("4 .. Profesní román) ");
						System.out.println("5 .. Pikareskní román");
						
						
						
						zanr=Main.pouzeCelaCisla(sc);
						switch(zanr) {
						case 1:
							zanr_2="Rytirsky";
							break;
						case 2:
							zanr_2="Biograficky";
							break;
						case 3:
							zanr_2="Historicky";
							break;
						case 4:
							zanr_2="Profesni";
							break;
						case 5:
							zanr_2="Pikaresni";
							break;
						}
							
						System.out.println("Zadejte nazev autora");
						autor=sc.nextLine();
						System.out.println("Zadejte rok vydani");
						rok=Main.pouzeCelaCisla(sc);
						if (!mojeDatabaze.setRoman(jmenoKnihy, rok, typ, autor, zanr_2))
							System.out.println("Kniha jiz existuje");
						break;
						
					
					case 2:
						typ_2="Ucebnice";
					
						System.out.println("pro jake rocniky je vhodny: ");
						System.out.println("1 .. prvni trida");
						System.out.println("2 .. druha trida");
						System.out.println("3 .. treti trida");
						System.out.println("4 .. ctvrta trida");
						System.out.println("5 .. pata trida");
						trida=Main.pouzeCelaCisla(sc);
							
						System.out.println("Zadejte nazev autora");
						autor=sc.nextLine();
						System.out.println("Zadejte rok vydani");
						rok=Main.pouzeCelaCisla(sc);
						if (!mojeDatabaze.setUcebnice(jmenoKnihy, rok, typ, autor, trida))
							System.out.println("Kniha jiz existuje");
					
					
					
						break;
					}
				break;
						
					
				case 2:
					System.out.println("Zadejte jmeno Knihy pro upravu");
					jmenoKnihy=sc.nextLine();
					
					if (mojeDatabaze.getKniha(jmenoKnihy) != null) {
						
						mojeDatabaze.vypsatKnihu(jmenoKnihy);
						
						System.out.println("Co chcete upravit:");
						System.out.println("1 .. autor");
						System.out.println("2 .. rok vydani");
						System.out.println("3 .. stav dostupnosti");
						uprava=pouzeCelaCisla(sc);
						Kniha info=null;
						info=mojeDatabaze.getKniha(jmenoKnihy);
						switch(uprava) {
						case 1:
							
							System.out.println("zadejte nove jmeno autora: ");
							autor=sc.nextLine();
							info.setAutor(autor);
							break;
						case 2:
							System.out.println("zadejte novy rok vydani: ");
							rok=pouzeCelaCisla(sc);
							info.setRok(rok);
							break;
						case 3:
							System.out.println("zadejte novy stav dostupnosti: ");
							System.out.println("1 .. vracene");
							System.out.println("2 .. vypujcene");
							mezistav=pouzeCelaCisla(sc);
							
							switch(mezistav)
							{
							case 1:
								info.setStav(true);
								break;
							case 2:
								info.setStav(false);
								break;
							}
							break;
						}
							System.out.println("Upraveno: ");
							mojeDatabaze.vypsatKnihu(jmenoKnihy);
							
						break;
						
					}
					
					else {
						System.out.println("Kniha s tímto názvem neexistuje");
						break;
					}
					
					
				case 3:
					System.out.println("Napiste nazev knihy:");
					jmenoKnihy = sc.nextLine();
					
					mojeDatabaze.vypsatKnihu(jmenoKnihy);
					
					if (mojeDatabaze.vymazKnihu (jmenoKnihy))
						System.out.println(jmenoKnihy + " odstranen ");
					else
						System.out.println(jmenoKnihy + " neni v databazi ");
					break;
				
					
				case 4:
					System.out.println("Napiste nazev knihy:");
					jmenoKnihy = sc.nextLine();
					
					if (mojeDatabaze.getKniha(jmenoKnihy) != null) {
						mojeDatabaze.vypsatKnihu(jmenoKnihy);
						Kniha info =null;
						info=mojeDatabaze.getKniha(jmenoKnihy);
						
						System.out.println("zadejte novy stav dostupnosti: ");
						System.out.println("1 .. vracene");
						System.out.println("2 .. vypujcene");
						mezistav=pouzeCelaCisla(sc);
						
						switch(mezistav)
						{
						case 1:
							info.setStav(true);
							break;
						case 2:
							info.setStav(false);
							break;
						}
						
						System.out.println("Upraveno: ");
						mojeDatabaze.vypsatKnihu(jmenoKnihy);
						break;
					}
					
					else {
						System.out.println(jmenoKnihy + " neni v databazi ");
						break;
					}
					
				case 5:
					mojeDatabaze.vypisDatabaze();
					break;
					
				case 6:
					System.out.println ("Zadejte jmeno knihy pro vypis info:");
					jmenoKnihy = sc.nextLine();
					
					if (mojeDatabaze.getKniha(jmenoKnihy) != null) {
					mojeDatabaze.vypsatKnihu(jmenoKnihy);	
					break;
					}
					
					else {
						System.out.println(jmenoKnihy + " neni v databazi ");
						break;
					}
					
				case 7:
					System.out.println ("Zadejte nazev autora: ");
					autor = sc.nextLine();
					
					/* if (mojeDatabaze.getAutor(autor) == null) {
						System.out.println(autor + " neni v databazi ");
						break;
					} */
					
 					mojeDatabaze.vypisKnihAutora(autor);
					break;
					

				case 8:	
					System.out.println("Vyberte žánr");
					System.out.println("1 .. Rytířský román");
					System.out.println("2 .. Biografický román");
					System.out.println("3 .. Historický román");
					System.out.println("4 .. Profesní román) ");
					System.out.println("5 .. Pikareskní román");
					volba = pouzeCelaCisla(sc);
					mojeDatabaze.vypisPodleZanru(volba);
					break;	
					
				case 9:
					boolean e = mojeDatabaze.vypisPodleDostupnosti();
					if (e == false)
						System.out.println("Není žádná vypůjčená kniha");
					break;
					
				case 10:
					System.out.println("Zadejte název knihy, kterou chcete uložit do souboru");
					jmenoKnihy = sc.nextLine();
					if (mojeDatabaze.getKniha(jmenoKnihy) != null) {
					mojeDatabaze.ulozSoubor(jmenoKnihy);
					break;
					}
					else {
						System.out.println(jmenoKnihy + " neni v databazi ");
						break;
					}
				
				case 11:
					System.out.println("Zadejte název knihy, kterou chcete načíst ze souboru");
					jmenoKnihy= sc.nextLine();
					if (mojeDatabaze.getKniha(jmenoKnihy) != null) {
					mojeDatabaze.nactiKnihu(jmenoKnihy);
					break;
					}
					
					else {
						System.out.println(jmenoKnihy + " neexistuje v souborech ");
						break;
					}
					
				case 12:
					run=false;
					break;
					
					/* 
					case 5:
						mojeDatabaze.vypisDatabaze();
						break;
					case 6:
						run=false;
						break;	*/
			
			}
		}
	}
}
					
		

				
				
				
			
			
	


	
	


