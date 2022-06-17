package ch.fhnw.mscmi.geneservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface GeneRepository extends PagingAndSortingRepository<Gene, Integer> {

	@Query("SELECT t FROM Gene t where t.symbol = :symbol")
	Page<Gene> findBySymbol(String symbol, Pageable pageable);

	@Query("SELECT t FROM Gene t where t.description like %:description%")
	Page<Gene> findByDescription(String description, Pageable pageable);

	@Query(value="SELECT * FROM allgenes where allgenes.gene_id = :intSearchQuery or allgenes.symbol = :searchQuery or MATCH(`description`) AGAINST(:searchQuery IN BOOLEAN MODE)", 
	       countQuery = "select count(*) FROM allgenes where allgenes.gene_id = :intSearchQuery or allgenes.symbol = :searchQuery or MATCH(`description`) AGAINST(:searchQuery IN BOOLEAN MODE)", 
	       nativeQuery=true)
	Page<Gene> search(@Param("searchQuery") String searchQuery, @Param("intSearchQuery") int intSearchQuery, Pageable pageable);
}
