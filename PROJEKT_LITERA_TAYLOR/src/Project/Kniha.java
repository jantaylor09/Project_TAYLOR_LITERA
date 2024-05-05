package Project;

public class Kniha {
	public Kniha(String jmenoKnihy, int rok, int typ, String autor)
	{
		this.jmenoKnihy=jmenoKnihy;
		this.rok=rok;
		this.autor=autor;
		this.typ=typ;
		
	}
	


	public boolean isStav() {
		return stav;
	}


	public void setStav(boolean stav) {
		this.stav = stav;
	}



	

	


	public void setRok(int rok) {
		this.rok = rok;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getJmenoKnihy() {
		return jmenoKnihy;
	}
	public int getRok() {
		return rok;
	}
	public String getAutor() {
		return autor;
	}
	public int getTyp() {
		return typ;
	}
	private String jmenoKnihy;
	private int rok;
	private String autor;
	private int typ;
	public boolean stav = true;
}
