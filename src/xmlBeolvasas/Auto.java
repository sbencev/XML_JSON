package xmlBeolvasas;

public class Auto {

	private String gyartmany;
	private String tipus;
	private int gyartasiEv;
	private int ar;

	public Auto(String gyartmany, String tipus, int gyartasiEv, int ar) {
		this.gyartmany = gyartmany;
		this.tipus = tipus;
		this.gyartasiEv = gyartasiEv;
		this.ar = ar;
	}

	public String getGyartmany() {
		return gyartmany;
	}

	public String getTipus() {
		return tipus;
	}

	public int getGyartasiEv() {
		return gyartasiEv;
	}

	public int getAr() {
		return ar;
	}

	@Override
	public String toString() {
		return gyartmany + " " + tipus + " " + gyartasiEv + " ara: " + ar + " Ft";
	}

}
