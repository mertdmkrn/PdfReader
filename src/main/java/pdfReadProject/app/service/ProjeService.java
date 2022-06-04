package pdfReadProject.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdfReadProject.app.dao.entity.*;
import pdfReadProject.app.dao.jpa.repository.*;
import pdfReadProject.app.service.model.ProjeContext;

@Service
public class ProjeService {

	@Autowired
	private ProjeRepository projeRepository;
	
	@Autowired
	private OgrenciRepository ogrenciRepository;
	
	@Autowired
	private DanismanRepository danismanRepository;
	
	@Autowired
	private JuriRepository juriRepository;
	
	public List<Proje> getProjeList() {
		
		return projeRepository.getProjeList();	
	
	}
	
	public List<Proje> findByKulId(int kulId) {
		
		return projeRepository.findByKulId(kulId);	
	
	}
	
	public List<Proje> findByOgrAd(String ogrAdSoyad) {
		
		return projeRepository.findByOgrAd(ogrAdSoyad);	
	
	}
	
	public List<Proje> findByDers(String projeDers) {
		
		return projeRepository.findByDers(projeDers);	
	
	}
	
	public List<Proje> findByProjeDonem(String projeDonem) {
		
		return projeRepository.findByProjeDonem(projeDonem);	
	
	}
	
	public List<Proje> findByProjeAdi(String projeAdi) {
		
		return projeRepository.findByProjeAdi(projeAdi);	
	
	}
	
	public List<Proje> findByAnahtarKelime(String anahtarKelime) {
		
		return projeRepository.findByAnahtarKelime(anahtarKelime);	
	
	}
	
	public List<Proje> findProje(int kulId, String projeDonem, String projeDers) {
		
		return projeRepository.findProje(kulId, projeDonem, projeDers);	
	
	}
	
	public String findPdfYolByProjeId(int projeId) {
		
		return projeRepository.findPdfYolByProjeId(projeId);	
	
	}
	
	public void delete(int id) {
		
		projeRepository.deleteById(id);
	}
	
	@Transactional
	public boolean save(ProjeContext projeContext) {
		
		if(projeContext.getProje().getProjeId() == 0) {
			int maxId = 1;
			
			if(projeRepository.count() > 0)
				maxId = projeRepository.findMaxId() + 1;
			
			projeContext.getProje().setProjeId(maxId);
		}
		
		Proje proje = projeRepository.save(projeContext.getProje());
		
		if(proje.getProjeId() > 0) {
			
			if(projeContext.getOgrenciler().size() > 0) {
				
				int sonOgrenciId = 0;
				
				if(ogrenciRepository.count() > 0)
					sonOgrenciId = ogrenciRepository.findMaxId();
				
				for(Ogrenci ogrenci : projeContext.getOgrenciler()) {
					sonOgrenciId++;
					ogrenci.setOgrId(sonOgrenciId);
					ogrenci.setProjeId(proje.getProjeId());
					ogrenciRepository.save(ogrenci);
				}
			}
			
			if(projeContext.getDanismanlar().size() > 0) {
				
				int sonDanismanId = 0;			
				
				if(danismanRepository.count() > 0)
					sonDanismanId = danismanRepository.findMaxId();
				
				for(Danisman danisman : projeContext.getDanismanlar()) {
					sonDanismanId++;
					danisman.setDanismanId(sonDanismanId);
					danisman.setProjeId(proje.getProjeId());
					danismanRepository.save(danisman);
				}
			}
			
			if(projeContext.getJuriler().size() > 0) {
				
				int sonJuriId = 0;
				
				if(juriRepository.count() > 0)
					sonJuriId = juriRepository.findMaxId();
				
				for(Juri juri : projeContext.getJuriler()) {
					sonJuriId++;
					juri.setJuriId(sonJuriId);
					juri.setProjeId(proje.getProjeId());
					juriRepository.save(juri);
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
