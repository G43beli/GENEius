package ch.fhnw.mscmi.geneservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GeneRepository extends PagingAndSortingRepository<Gene, Integer> {

	@Query("SELECT t FROM Gene t where t.symbol = :symbol")
	Page<Gene> findBySymbol(String symbol, Pageable pageable);

	@Query("SELECT t FROM Gene t where t.description like %:description%")
	Page<Gene> findByDescription(String description, Pageable pageable);

	@Query("SELECT t FROM Gene t where t.symbol = :searchQuery or t.description like %:searchQuery%")
	Page<Gene> search(String searchQuery, Pageable pageable);

}
