package Project;

public class Ucebnice extends Kniha {
	private int trida;
	public int getTrida() {
		return trida;
	}
	public Ucebnice(String jmenoKnihy, int rok, int typ, String autor, int trida) {
		super(jmenoKnihy, rok, typ, autor);
		this.trida=trida;
		
	}
	
	
}
