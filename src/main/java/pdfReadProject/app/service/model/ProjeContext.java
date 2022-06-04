package pdfReadProject.app.service.model;

import java.util.List;

import pdfReadProject.app.dao.entity.*;

public class ProjeContext {

	private Proje proje = new Proje();
	private List<Ogrenci> ogrenciler;
	private List<Danisman> danismanlar;
	private List<Juri> juriler;
	
	public Proje getProje() {
		return proje;
	}
	
	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}
	
	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}
	
	public List<Danisman> getDanismanlar() {
		return danismanlar;
	}
	public void setDanismanlar(List<Danisman> danismanlar) {
		this.danismanlar = danismanlar;
	}
	
	public List<Juri> getJuriler() {
		return juriler;
	}
	
	public void setJuriler(List<Juri> juriler) {
		this.juriler = juriler;
	}
	
}
