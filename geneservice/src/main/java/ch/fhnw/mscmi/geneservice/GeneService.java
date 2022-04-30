package ch.fhnw.mscmi.geneservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneService {

	@Autowired
    private GeneRepository geneRepository;
    
    public Gene findById(Integer id) {
    	return geneRepository.findById(id).get();
    }
    
    public List<Gene> findBySymbol(String symbol) {
    	return geneRepository.findBySymbol(symbol);
    }

    public List<Gene> findByDescription(String description) {
        return geneRepository.findByDescription(description);
    }
	
}
