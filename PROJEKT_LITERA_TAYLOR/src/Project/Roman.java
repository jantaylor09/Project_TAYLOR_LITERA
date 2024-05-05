package Project;

public class Roman extends Kniha{
	
	private String zanr;
	public String getZanr() {
		return zanr;
	}
	public Roman(String jmenoKnihy, int rok, int typ, String autor, String zanr) {
		super(jmenoKnihy, rok, typ, autor);
		this.zanr=zanr;
		
	}

	
	
}
