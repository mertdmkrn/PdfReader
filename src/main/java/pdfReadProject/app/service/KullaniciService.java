package pdfReadProject.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import pdfReadProject.app.dao.entity.Kullanici;
import pdfReadProject.app.dao.jpa.repository.KullaniciRepository;

@Service
public class KullaniciService implements UserDetailsService {

	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	public List<Kullanici> getKullaniciList() {
		
		return kullaniciRepository.getKullaniciList();	
	
	}
	
	public List<Kullanici> getOgretmenList() {
		
		return kullaniciRepository.getOgretmenList();	
	
	}
	
	public Kullanici findByLoginUser(String kulAdi, String kulSifre) {
		
		return kullaniciRepository.findByLoginUser(kulAdi,kulSifre);	
	
	}
	
	public Kullanici findByKulAdi(String kulAdi) {
		
		return kullaniciRepository.findByKulAdi(kulAdi);	
	
	}
	
	public Kullanici findById(int id) {
		
		return kullaniciRepository.findById(id);	
	
	}
	
	public void delete(int id) {
		
		kullaniciRepository.deleteById(id);

	}
	
	@Transactional
	public int save(Kullanici kullanici) {
		
		if(kullanici.getKulId() == 0) {//Yeni Kullanýcý
			int maxId = 1;
			
			if(kullaniciRepository.count() > 0)
				maxId = kullaniciRepository.findMaxId() + 1;
			
			kullanici.setKulId(maxId);
		}
		
		kullanici = kullaniciRepository.save(kullanici);
		
		return kullanici.getKulId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String kulAdi) throws UsernameNotFoundException {
		
		Kullanici kullaniciBilgi = kullaniciRepository.findByKulAdi(kulAdi);
		
	    if (kullaniciBilgi == null){
	    	throw new UsernameNotFoundException("Invalid username or password.");
		}
			       
	    List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
	    
	    PasswordEncoder pswencode = new BCryptPasswordEncoder();
	   	
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + kullaniciBilgi.getKulRol());
		grantList.add(authority);
			             
		UserDetails userDetails = (UserDetails) new User(kullaniciBilgi.getKulAdi(),
		pswencode.encode(kullaniciBilgi.getKulSifre()),grantList);
			        
		return userDetails;
	
	}
	
}
