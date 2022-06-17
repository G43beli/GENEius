package ch.fhnw.mscmi.geneservice;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse<T> implements Serializable {
    
    @JsonProperty("searchResultCount")
    private int searchResultCount;
    @JsonProperty("totalCount")
    private long totalCount;
    @JsonProperty("response")
    private T response;

    public SearchResponse() {
        this.searchResultCount = 0;
        this.totalCount = 0;
        this.response = null;
    }

    public SearchResponse(int searchResultCount, long totalCount, T response) {
        this.searchResultCount = searchResultCount;
        this.totalCount = totalCount;
        this.response = response;
    }
}
