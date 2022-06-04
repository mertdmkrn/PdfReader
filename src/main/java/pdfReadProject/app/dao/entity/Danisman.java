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
@Table(name = "Danisman")
public class Danisman {
	
	@Id
	@Column(name = "danismanId")
	private int danismanId;
	
	@Column(name = "danismanAdSoyad")
	private String danismanAdSoyad;
	
	@Column(name = "danismanUnvan")
	private String danismanUnvan;
	
	@Column(name = "projeId")
	private int projeId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projeId", insertable = false, updatable = false)
	private Proje proje;

	public int getDanismanId() {
		return danismanId;
	}

	public void setDanismanId(int danismanId) {
		this.danismanId = danismanId;
	}

	public String getDanismanAdSoyad() {
		return danismanAdSoyad;
	}

	public void setDanismanAdSoyad(String danismanAdSoyad) {
		this.danismanAdSoyad = danismanAdSoyad;
	}

	public String getDanismanUnvan() {
		return danismanUnvan;
	}

	public void setDanismanUnvan(String danismanUnvan) {
		this.danismanUnvan = danismanUnvan;
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
