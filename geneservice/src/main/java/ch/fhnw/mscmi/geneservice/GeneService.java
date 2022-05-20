package ch.fhnw.mscmi.geneservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GeneService {

	@Autowired
    private GeneRepository geneRepository;
    
    public Gene findById(Integer id) {
    	return geneRepository.findById(id).get();
    }
    
    public List<Gene> findBySymbol(String symbol, Integer pagenumber, Integer pagesize) {
        final Pageable genePageable = PageRequest.of(pagenumber, pagesize);
        Page<Gene> pagedResult = geneRepository.findBySymbol(symbol, genePageable);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Gene>();
        }
    }

    public List<Gene> findByDescription(String description) {
        return geneRepository.findByDescription(description);
    }
	
}
