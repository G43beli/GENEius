package ch.fhnw.mscmi.geneservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("geneservice")
public class GeneController {

    @Autowired
    private GeneService geneService;

    private static final Logger logger = LoggerFactory.getLogger(GeneserviceApplication.class);

    @GetMapping("/byid")
    public Gene getById(@RequestParam(value = "id") Integer id) {
        logger.debug("GENEius: Endpoint getById called with parameter: " + id);
        return geneService.findById(id);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/bysymbol")
    public SearchResponse<List<Gene>> getBySymbol(@RequestParam(value = "symbol") String symbol, @PathVariable int offset, @PathVariable int pageSize) {
        logger.debug("GENEius: Endpoint getBySymbol called with parameter: " + symbol);
        Page<Gene> genes = geneService.findBySymbol(symbol, offset, pageSize);
        if (genes != null && genes.hasContent()) {
            return new SearchResponse<>(genes.getSize(), genes.getTotalElements(), genes.getContent());
        } else {
            return new SearchResponse<List<Gene>>();
        }
    }

    @GetMapping("/pagination/{offset}/{pageSize}/bydescription")
    public SearchResponse<List<Gene>> getByDescription(@RequestParam(value = "description") String description, @PathVariable int offset, @PathVariable int pageSize) {
        logger.debug("GENEius: Endpoint getByDescription called with parameter: " + description);
        Page<Gene> genes = geneService.findByDescription(description, offset, pageSize);
        if (genes != null && genes.hasContent()) {
            return new SearchResponse<>(genes.getSize(), genes.getTotalElements(), genes.getContent());
        } else {
            return new SearchResponse<List<Gene>>();
        }
    }

    @GetMapping("/search/{offset}/{pageSize}/{sortField}")
    public SearchResponse<List<Gene>> searchGenesPaginated(@RequestParam(value = "q") String searchQuery, @PathVariable int offset, @PathVariable int pageSize, @PathVariable String sortField) {
        logger.debug("GENEius: Endpoint searchGenesPaginated called with search query: " + searchQuery);        
        Page<Gene> genes = geneService.search(searchQuery, offset, pageSize, sortField);
        if (genes != null && genes.hasContent()) {
            return new SearchResponse<>(genes.getSize(), genes.getTotalElements(), genes.getContent());
        } else {
            return new SearchResponse<List<Gene>>();
        }
    }
}
