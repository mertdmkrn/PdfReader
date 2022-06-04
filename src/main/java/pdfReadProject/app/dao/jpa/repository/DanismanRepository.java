package pdfReadProject.app.dao.jpa.repository;

import org.springframework.stereotype.Repository;

import pdfReadProject.app.dao.entity.Danisman;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DanismanRepository extends CrudRepository<Danisman, Integer>  {

	@Query(value = "SELECT d from Danisman d")
	public List<Danisman> getDanismanList();
	
	@Query(value = "SELECT max(d.danismanId) from Danisman d")
	public int findMaxId();

}
