package ch.fhnw.mscmi.geneservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GeneService {

    @Autowired
    private GeneRepository geneRepository;   

    public Gene findById(Integer id) {
        return geneRepository.findById(id).get();
    }

    public Page<Gene> findBySymbol(String symbol, int offset, int pageSize) {
        return geneRepository.findBySymbol(symbol, PageRequest.of(offset, pageSize));
    }

    public Page<Gene> findByDescription(String description, int offset, int pageSize) {
        return geneRepository.findByDescription(description, PageRequest.of(offset, pageSize));
    }

    public Page<Gene> search(String searchQuery, int intSearchQuery, int offset, int pageSize) {
        return geneRepository.search(searchQuery, intSearchQuery, PageRequest.of(offset, pageSize));
    }
}
