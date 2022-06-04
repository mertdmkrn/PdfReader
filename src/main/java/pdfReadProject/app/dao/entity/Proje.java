package pdfReadProject.app.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Proje")
public class Proje {
	
	@Id
	@Column(name = "projeId")
	private int projeId;
	
	@Column(name = "projeAdi")
	private String projeAdi;

	@Column(name = "projeDers")
	private String projeDers;
	
	@Column(name = "projeOzet")
	private String projeOzet;
	
	@Column(name = "projeAnahtarKelime")
	private String projeAnahtarKelime;

	@Column(name = "projeDonem")
	private String projeDonem;

	@Column(name = "projePdfYolu")
	private String projePdfYolu;

	@Column(name = "kulId")
	private int kulId;	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kulId", insertable = false, updatable = false)
	private Kullanici kullanici;
	
	@OneToMany(mappedBy = "proje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ogrenci> ogrenciler;
	
	@OneToMany(mappedBy = "proje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Danisman> danismanlar;
	
	@OneToMany(mappedBy = "proje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Juri> juriler;

	public int getProjeId() {
		return projeId;
	}

	public void setProjeId(int projeId) {
		this.projeId = projeId;
	}

	public String getProjeAdi() {
		return projeAdi;
	}

	public void setProjeAdi(String projeAdi) {
		this.projeAdi = projeAdi;
	}

	public String getProjeDers() {
		return projeDers;
	}

	public void setProjeDers(String projeDers) {
		this.projeDers = projeDers;
	}

	public String getProjeOzet() {
		return projeOzet;
	}

	public void setProjeOzet(String projeOzet) {
		this.projeOzet = projeOzet;
	}

	public String getProjeAnahtarKelime() {
		return projeAnahtarKelime;
	}

	public void setProjeAnahtarKelime(String projeAnahtarKelime) {
		this.projeAnahtarKelime = projeAnahtarKelime;
	}

	public String getProjeDonem() {
		return projeDonem;
	}

	public void setProjeDonem(String projeDonem) {
		this.projeDonem = projeDonem;
	}

	public String getProjePdfYolu() {
		return projePdfYolu;
	}

	public void setProjePdfYolu(String projePdfYolu) {
		this.projePdfYolu = projePdfYolu;
	}

	public int getKulId() {
		return kulId;
	}

	public void setKulId(int kulId) {
		this.kulId = kulId;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
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
