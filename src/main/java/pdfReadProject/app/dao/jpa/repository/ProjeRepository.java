package pdfReadProject.app.dao.jpa.repository;

import org.springframework.stereotype.Repository;

import pdfReadProject.app.dao.entity.Proje;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProjeRepository extends CrudRepository<Proje, Integer>  {

	@Query(value = "SELECT p from Proje p")
	public List<Proje> getProjeList();
	
	@Query(value = "SELECT max(p.projeId) from Proje p")
	public int findMaxId();
	
	@Query(value = "SELECT p from Proje p where p.kulId = :kulId")
	public List<Proje> findByKulId(@Param("kulId") int kulId);
	
	@Query(value = "SELECT p.projePdfYolu from Proje p where p.projeId = :projeId")
	public String findPdfYolByProjeId(@Param("projeId") int projeId);
	
	@Query(value = "SELECT p from Proje p INNER JOIN Ogrenci o ON p.projeId = o.projeId where o.ogrAdSoyad = :ogrAdSoyad")
	public List<Proje> findByOgrAd(@Param("ogrAdSoyad") String ogrAdSoyad);
	
	@Query(value = "SELECT p from Proje p where p.projeDers = :projeDers")
	public List<Proje> findByDers(@Param("projeDers") String projeDers);
	
	@Query(value = "SELECT p from Proje p where p.projeAdi = :projeAdi")
	public List<Proje> findByProjeAdi(@Param("projeAdi") String projeAdi);
	
	@Query(value = "SELECT p from Proje p where p.projeDonem = :projeDonem")
	public List<Proje> findByProjeDonem(@Param("projeDonem") String projeDonem);
	
	@Query(value = "SELECT p from Proje p where p.projeAnahtarKelime LIKE '%' || :projeAnahtarKelime || '%'")
	public List<Proje> findByAnahtarKelime(@Param("projeAnahtarKelime") String projeAnahtarKelime);
	
	@Query(value = "SELECT p from Proje p where p.kulId = :kulId and p.projeDonem = :projeDonem and p.projeDers = :projeDers")
	public List<Proje> findProje(@Param("kulId") int kulId, @Param("projeDonem") String projeDonem, @Param("projeDers") String projeDers);
	
}
