package pdfReadProject.app.dao.jpa.repository;

import org.springframework.stereotype.Repository;

import pdfReadProject.app.dao.entity.Ogrenci;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OgrenciRepository extends CrudRepository<Ogrenci, Integer>  {

	@Query(value = "SELECT o from Ogrenci o")
	public List<Ogrenci> getOgrenciList();
	
	@Query(value = "SELECT DISTINCT o.ogrAdSoyad from Ogrenci o")
	public List<String> getOgrenciIsimList();
	
	@Query(value = "SELECT max(o.ogrId) from Ogrenci o")
	public int findMaxId();
	
}
