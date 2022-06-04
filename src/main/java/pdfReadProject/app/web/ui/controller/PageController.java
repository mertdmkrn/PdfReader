package pdfReadProject.app.web.ui.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pdfReadProject.app.PdfOkuyucu;
import pdfReadProject.app.dao.entity.Kullanici;
import pdfReadProject.app.dao.entity.Proje;
import pdfReadProject.app.service.KullaniciService;
import pdfReadProject.app.service.OgrenciService;
import pdfReadProject.app.service.ProjeService;
import pdfReadProject.app.service.model.ProjeContext;
import pdfReadProject.app.service.model.ProjeFiltreContext;

@Controller
@RequestMapping("")
public class PageController {
	
	@Autowired
	private KullaniciService kullaniciService;
	
	@Autowired
	private ProjeService projeService;
	
	@Autowired
	private OgrenciService ogrenciService;
	
	private static Kullanici sessiondakiKullanici;
	
	private static ProjeFiltreContext projeFiltreContext = new ProjeFiltreContext();
	
    private final String basePath = "../pdfReadProject/src/main/resources/static/pdf/";

	@RequestMapping(value = {"/giris","/",""}, method = RequestMethod.GET)
    public String girisPage() {
		
        return "giris";
   
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Authentication authentication,Model model) {
		
		try {
			sessiondakiKullanici = kullaniciService.findByKulAdi(authentication.getName());
			
			projeFiltreContext.setFiltreTuru("");
			model.addAttribute("kullaniciAdSoyad", sessiondakiKullanici.getKulAdSoyad());
			model.addAttribute("kullanicilar", kullaniciService.getKullaniciList());
			model.addAttribute("projeler", projeService.getProjeList());
			model.addAttribute("ogretmenler", kullaniciService.getOgretmenList());
			model.addAttribute("ogrenciler", ogrenciService.getOgrenciIsimList());
			model.addAttribute("projeFiltreContext", projeFiltreContext);
			
	        return "admin";
		}
		catch(Exception ex) {
			return "redirect:/giris";
		}
   
	}
	
	@RequestMapping(value = "/admin/kullaniciDuzenle", method = RequestMethod.GET)
	public String kullaniciDuzenlePage(HttpServletRequest request, Model model) {
		 
		try {
			int id = -1;  

	        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
	        	id = Integer.parseInt(request.getParameter("id"));
	        }
			
			model.addAttribute("kullaniciAdSoyad", sessiondakiKullanici.getKulAdSoyad());
			model.addAttribute("kullanici", id > 0 ? kullaniciService.findById(id) : new Kullanici());
			
	        return "kullaniciDuzenle";
		}
		catch(Exception ex) {
			return "redirect:/giris";
		}
	    
	}
	
	@RequestMapping(value = "/admin/kullaniciKaydet", method = RequestMethod.POST)
	public String kullaniciKaydet(Kullanici kullanici, Model model) {
		 
		kullaniciService.save(kullanici);
	    return "redirect:/admin";
	    
	}
	
	@RequestMapping(value = "/admin/kullaniciSil", method = RequestMethod.GET)
	public String kullaniciSil(HttpServletRequest request) {
		
		int id = -1;  

	    if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
	       	id = Integer.parseInt(request.getParameter("id"));
	    }        
	    
	    kullaniciService.delete(id);	
	    
		return "redirect:/admin";		    
		
	}
	
	@RequestMapping(value = "/ogretmen/projeSil", method = RequestMethod.GET)
	public String projeSil(HttpServletRequest request) throws IOException {
		
		int id = -1;  

	    if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
	       	id = Integer.parseInt(request.getParameter("id"));
	    }        
	    
	    Path path = Paths.get(basePath + projeService.findPdfYolByProjeId(id));
	    Files.deleteIfExists(path);
	    projeService.delete(id);
	  	    
		return "redirect:/ogretmen";	
	}
		
		
	@RequestMapping(value = "/ogretmen", method = RequestMethod.GET)
    public String ogretmenPage(Authentication authentication,Model model) {
		
		try {
			sessiondakiKullanici = kullaniciService.findByKulAdi(authentication.getName());
			
			projeFiltreContext.setFiltreTuru("");
			model.addAttribute("kullanici", sessiondakiKullanici);
			model.addAttribute("projeler", projeService.findByKulId(sessiondakiKullanici.getKulId()));
			model.addAttribute("ogrenciler", ogrenciService.getOgrenciIsimList());
			model.addAttribute("projeFiltreContext", projeFiltreContext);
			
	        return "ogretmen";
		}
		catch(Exception ex) {
			return "redirect:giris";
		}
   
	}
	
	@RequestMapping(value = "/ogretmen/projeYukle", method = RequestMethod.POST)
    public String projeYukle(@RequestParam("pdfFile") MultipartFile file, RedirectAttributes attributes) throws IOException {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Lütfen dosya eklemesi yapýnýz.");
            return "redirect:/ogretmen";
        }

        String dosyaAdi = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path path = Paths.get(basePath + dosyaAdi);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        ProjeContext projeContext = PdfOkuyucu.pdfOkuVeGerekliBilgileriAl(dosyaAdi);
        projeContext.getProje().setKulId(sessiondakiKullanici.getKulId());
        projeContext.getProje().setProjePdfYolu(dosyaAdi);
        
        projeService.save(projeContext);
        
        attributes.addFlashAttribute("message", "Dosya baþarýlý bir biçimde kaydedildi. " + dosyaAdi + '!');

        return "redirect:/ogretmen";
    }
	
	@RequestMapping(value = "/projeFiltrele", method = RequestMethod.POST)
    public String projeFiltrele(ProjeFiltreContext filtreContext, Model model) {
		
		List<Proje> projeler = new ArrayList<Proje>();
		projeFiltreContext = filtreContext;
		
		if(projeFiltreContext.getFiltreTuru().equals("OGRETMEN")) {
			projeler = projeService.findByKulId(projeFiltreContext.getOgretmenId());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("OGRENCI")) {
			projeler = projeService.findByOgrAd(projeFiltreContext.getOgrenciAdSoyad());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("DERS")) {
			projeler = projeService.findByDers(projeFiltreContext.getProjeDers());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("AD")) {
			projeler = projeService.findByProjeAdi(projeFiltreContext.getProjeAdi());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("DONEM")) {
			projeler = projeService.findByProjeDonem(projeFiltreContext.getProjeDonem());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("ANAHTARKELIME")) {
			projeler = projeService.findByAnahtarKelime(projeFiltreContext.getAnahtarKelime());
		}
		
		if(projeFiltreContext.getFiltreTuru().equals("COKLU")) {
			projeler = projeService.findProje(projeFiltreContext.getOgretmenId(), projeFiltreContext.getProjeDonemCoklu(), projeFiltreContext.getProjeDersCoklu());
		}
		
		if(sessiondakiKullanici.getKulRol().equals("ADMIN")) {
			
			model.addAttribute("kullaniciAdSoyad", sessiondakiKullanici.getKulAdSoyad());
			model.addAttribute("kullanicilar", kullaniciService.getKullaniciList());
			model.addAttribute("projeler", projeler);
			model.addAttribute("ogretmenler", kullaniciService.getOgretmenList());
			model.addAttribute("ogrenciler", ogrenciService.getOgrenciIsimList());
			model.addAttribute("projeFiltreContext", projeFiltreContext);
			
	        return "admin";
		}
		else {
			List<Proje> kullanicininProjeleri = new ArrayList<Proje>();
			
			for(Proje p : projeler) {
				if(p.getKulId() == sessiondakiKullanici.getKulId())
					kullanicininProjeleri.add(p);
			}

			model.addAttribute("kullanici", sessiondakiKullanici);
			model.addAttribute("projeler", kullanicininProjeleri);
			model.addAttribute("ogrenciler", ogrenciService.getOgrenciIsimList());
			model.addAttribute("projeFiltreContext", projeFiltreContext);
			
	        return "ogretmen";
		}

    }

}
