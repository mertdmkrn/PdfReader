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
@Table(name = "Juri")
public class Juri {
	
	@Id
	@Column(name = "juriId")
	private int juriId;
	
	@Column(name = "juriAdSoyad")
	private String juriAdSoyad;
	
	@Column(name = "juriUnvan")
	private String juriUnvan;
	
	@Column(name = "projeId")
	private int projeId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projeId", insertable = false, updatable = false)
	private Proje proje;

	public int getJuriId() {
		return juriId;
	}

	public String getJuriAdSoyad() {
		return juriAdSoyad;
	}

	public void setJuriAdSoyad(String juriAdSoyad) {
		this.juriAdSoyad = juriAdSoyad;
	}

	public String getJuriUnvan() {
		return juriUnvan;
	}

	public void setJuriUnvan(String juriUnvan) {
		this.juriUnvan = juriUnvan;
	}

	public void setJuriId(int juriId) {
		this.juriId = juriId;
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
