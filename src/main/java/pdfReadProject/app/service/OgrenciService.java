package pdfReadProject.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdfReadProject.app.dao.entity.*;
import pdfReadProject.app.dao.jpa.repository.*;

@Service
public class OgrenciService {
	
	@Autowired
	private OgrenciRepository ogrenciRepository;
	
	public List<Ogrenci> getOgrenciList() {
		
		return ogrenciRepository.getOgrenciList();	
	
	}
	
	public List<String> getOgrenciIsimList() {
		
		return ogrenciRepository.getOgrenciIsimList();	
	
	}
	
}
