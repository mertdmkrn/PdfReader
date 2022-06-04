package pdfReadProject.app.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ogrenci")
public class Ogrenci {
	
	@Id
	@Column(name = "ogrId")
	private int ogrId;
	
	@Column(name = "ogrAdSoyad")
	private String ogrAdSoyad;
	
	@Column(name = "ogrNo")
	private String ogrNo;
	
	@Column(name = "ogrOgrenimTuru")
	private String ogrOgrenimTuru;
	
	@Column(name = "projeId")
	private int projeId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projeId", insertable = false, updatable = false)
	private Proje proje;

	public int getOgrId() {
		return ogrId;
	}

	public void setOgrId(int ogrId) {
		this.ogrId = ogrId;
	}

	public String getOgrAdSoyad() {
		return ogrAdSoyad;
	}

	public void setOgrAdSoyad(String ogrAdSoyad) {
		this.ogrAdSoyad = ogrAdSoyad;
	}

	public String getOgrNo() {
		return ogrNo;
	}

	public void setOgrNo(String ogrNo) {
		this.ogrNo = ogrNo;
	}

	public String getOgrOgrenimTuru() {
		return ogrOgrenimTuru;
	}

	public void setOgrOgrenimTuru(String ogrOgrenimTuru) {
		this.ogrOgrenimTuru = ogrOgrenimTuru;
	}

	public int getProjeId() {
		return projeId;
	}

	public void setProjeId(int projeId) {
		this.projeId = projeId;
	}

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}
	
}
