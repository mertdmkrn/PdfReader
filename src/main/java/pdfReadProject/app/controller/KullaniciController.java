package pdfReadProject.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pdfReadProject.app.dao.entity.Kullanici;
import pdfReadProject.app.service.KullaniciService;

@RestController
@RequestMapping("")
public class KullaniciController {

	@Autowired
	private KullaniciService kullaniciService;
	
	@RequestMapping(value = "/kullanici/list", method = RequestMethod.GET)
	public List<Kullanici> getKullaniciList() {
		
		return kullaniciService.getKullaniciList();
	}
}
