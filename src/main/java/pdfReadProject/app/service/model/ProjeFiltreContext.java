package pdfReadProject.app.service.model;

public class ProjeFiltreContext {

	private int ogretmenId;
	private String ogrenciAdSoyad;
	private String projeDers;
	private String projeAdi;
	private String projeDonem;
	private String projeDersCoklu;
	private String projeDonemCoklu;
	private String anahtarKelime;
	private String filtreTuru = "";
	
	public int getOgretmenId() {
		return ogretmenId;
	}
	
	public void setOgretmenId(int ogretmenId) {
		this.ogretmenId = ogretmenId;
	}
	
	public String getOgrenciAdSoyad() {
		return ogrenciAdSoyad;
	}
	
	public void setOgrenciAdSoyad(String ogrenciAdSoyad) {
		this.ogrenciAdSoyad = ogrenciAdSoyad;
	}
	
	public String getProjeDers() {
		return projeDers;
	}

	public void setProjeDers(String projeDers) {
		this.projeDers = projeDers;
	}

	public String getProjeAdi() {
		return projeAdi;
	}
	
	public void setProjeAdi(String projeAdi) {
		this.projeAdi = projeAdi;
	}
	
	public String getProjeDonem() {
		return projeDonem;
	}
	
	public void setProjeDonem(String projeDonem) {
		this.projeDonem = projeDonem;
	}
	
	public String getAnahtarKelime() {
		return anahtarKelime;
	}
	
	public void setAnahtarKelime(String anahtarKelime) {
		this.anahtarKelime = anahtarKelime;
	}

	public String getFiltreTuru() {
		return filtreTuru;
	}

	public void setFiltreTuru(String filtreTuru) {
		this.filtreTuru = filtreTuru;
	}

	public String getProjeDersCoklu() {
		return projeDersCoklu;
	}

	public void setProjeDersCoklu(String projeDersCoklu) {
		this.projeDersCoklu = projeDersCoklu;
	}

	public String getProjeDonemCoklu() {
		return projeDonemCoklu;
	}

	public void setProjeDonemCoklu(String projeDonemCoklu) {
		this.projeDonemCoklu = projeDonemCoklu;
	}
	
}
