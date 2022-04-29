package ch.fhnw.mscmi.geneservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	@Query("SELECT t FROM Gene t where t.symbol = :symbol")
	List<Gene> findBySymbol(String symbol);

	@Query("SELECT t FROM Gene t where t.description like %:description%")
	List<Gene> findByDescription(String description);
}
