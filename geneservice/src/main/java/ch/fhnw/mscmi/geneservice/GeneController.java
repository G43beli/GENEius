package ch.fhnw.mscmi.geneservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/bysymbol")
    public List<Gene> getBySymbol(@RequestParam(value = "symbol") String symbol) {
        logger.debug("GENEius: Endpoint getBySymbol called with parameter: " + symbol);
        List<Gene> genes = geneService.findBySymbol(symbol);
        return genes;
    }

    @GetMapping("/bydescription")
    public List<Gene> getByDescription(@RequestParam(value = "description") String description) {
        logger.debug("GENEius: Endpoint getByDescription called with parameter: " + description);
        List<Gene> genes = geneService.findByDescription(description);
        return genes;
    }
}
