package pdfReadProject.app.dao.jpa.repository;

import org.springframework.stereotype.Repository;

import pdfReadProject.app.dao.entity.Juri;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface JuriRepository extends CrudRepository<Juri, Integer>  {

	@Query(value = "SELECT j from Juri j")
	public List<Juri> getJuriList();
	
	@Query(value = "SELECT max(j.juriId) from Juri j")
	public int findMaxId();

}
