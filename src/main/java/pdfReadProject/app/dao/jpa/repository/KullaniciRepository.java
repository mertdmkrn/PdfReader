package pdfReadProject.app.dao.jpa.repository;

import org.springframework.stereotype.Repository;

import pdfReadProject.app.dao.entity.Kullanici;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface KullaniciRepository extends CrudRepository<Kullanici, Integer>  {

	@Query(value = "SELECT k from Kullanici k")
	public List<Kullanici> getKullaniciList();
	
	@Query(value = "SELECT k from Kullanici k where k.kulRol = 'OGRETMEN'")
	public List<Kullanici> getOgretmenList();
	
	@Query(value = "SELECT max(k.kulId) from Kullanici k")
	public int findMaxId();
	
	@Query(value = "SELECT k from Kullanici k where k.kulAdi = :kulAdi and k.kulSifre = :kulSifre")
	public Kullanici findByLoginUser(@Param("kulAdi") String kulAdi,@Param("kulSifre") String kulSifre);
	
	@Query(value = "SELECT k from Kullanici k where k.kulAdi = :kulAdi")
	public Kullanici findByKulAdi(@Param("kulAdi") String kulAdi);
	
	@Query(value = "SELECT k from Kullanici k where k.kulId = :kulId")
	public Kullanici findById(@Param("kulId") int kulId);
}
