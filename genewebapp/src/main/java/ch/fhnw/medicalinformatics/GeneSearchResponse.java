package ch.fhnw.medicalinformatics;

import java.util.List;
 
public class GeneSearchResponse {
     
    private int searchResultCount; 
    private long totalCount; 
    private List<Gene> response;
    
    public List<Gene> getResponse() {
        return response;
    }
    public int getSearchResultCount() {
        return searchResultCount;
    }
    public void setSearchResultCount(int searchResultCount) {
        this.searchResultCount = searchResultCount;
    }
    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    public void setResponse(List<Gene> response) {
        this.response = response;
    }

}

