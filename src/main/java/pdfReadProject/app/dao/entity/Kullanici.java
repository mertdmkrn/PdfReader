package pdfReadProject.app.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Kullanici")
public class Kullanici {
	
	@Id
	@Column(name = "kulId")
	private int kulId;
	
	@Column(name = "kulAdSoyad")
	private String kulAdSoyad;
	
	@Column(name = "kulAdi")
	private String kulAdi;
	
	@Column(name = "kulSifre")
	private String kulSifre;
	
	@Column(name = "kulRol")
	private String kulRol;
	
	@OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Proje> projeler;

	public int getKulId() {
		return kulId;
	}

	public void setKulId(int kulId) {
		this.kulId = kulId;
	}

	public String getKulAdSoyad() {
		return kulAdSoyad;
	}

	public void setKulAdSoyad(String kulAdSoyad) {
		this.kulAdSoyad = kulAdSoyad;
	}

	public String getKulAdi() {
		return kulAdi;
	}

	public void setKulAdi(String kulAdi) {
		this.kulAdi = kulAdi;
	}

	public String getKulSifre() {
		return kulSifre;
	}

	public void setKulSifre(String kulSifre) {
		this.kulSifre = kulSifre;
	}
	
	public String getKulRol() {
		return kulRol;
	}

	public void setKulRol(String kulRol) {
		this.kulRol = kulRol;
	}

	public List<Proje> getProjeler() {
		return projeler;
	}

	public void setProjeler(List<Proje> proje) {
		this.projeler = proje;
	}
	
}
