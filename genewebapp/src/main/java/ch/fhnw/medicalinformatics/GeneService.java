package ch.fhnw.medicalinformatics;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.NamedEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.primefaces.application.resource.barcode.EAN13Generator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@NamedEvent
@ApplicationScoped
@ManagedBean(name = "geneService")
public class GeneService {

	private String searchOption;
	private String searchTerm;
	private List<Gene> data = new ArrayList<>();
	private long totalCount;
	private String hostname = "http://localhost:9090";
	
	private final OkHttpClient httpClient;
	
	public GeneService() {
		httpClient = new OkHttpClient.Builder()
			      .connectTimeout(180, TimeUnit.SECONDS)
			      .readTimeout(180, TimeUnit.SECONDS)
			      .writeTimeout(180, TimeUnit.SECONDS)
			      .build();
	}
	
	public List<String> getSearchOptions() {
		List<String> result = new ArrayList<String>();
		result.add("Search by ID");
		result.add("Search by Symbol");
		result.add("search by Description");
		return result;
	}	
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public void retrieveData() {
		System.out.println("retrieve data");
		data.clear();
		// retrieve data from the service
		Request request = null;
		String serviceCall = "";
		if (searchOption.toUpperCase().contains("ID")) {
			serviceCall = "/geneservice/byid?id=" + searchTerm;
		} else if (searchOption.toUpperCase().contains("SYMBOL")) {
			serviceCall = "/geneservice/bysymbol?symbol=" + searchTerm + "&offset=0&pageSize=10";
		} else if (searchOption.toUpperCase().contains("DESCRIPTION")) {
			serviceCall = "/geneservice/bydescription?description=" + searchTerm + "&offset=0&pageSize=10";
		} else {
			System.out.println("invalid search");
			// TODO: Exception Handling
		}
		
		System.out.println("URL: " + hostname + serviceCall);
		
		request = new Request.Builder().url(hostname + serviceCall).build();
		
		try (Response response = httpClient.newCall(request).execute()) {
			Gson g = new Gson();
			Type resultType = null;
			if (searchOption.toUpperCase().contains("ID")) {
				resultType = new TypeToken<Gene>() {}.getType();
				Gene sg = g.fromJson(response.body().string(), resultType);
				data.add(sg);
				totalCount = data.size();
			} else {
				resultType = new TypeToken<GeneSearchResponse>() {}.getType();
				GeneSearchResponse sr = g.fromJson(response.body().string(), resultType);
				for(Gene gene : sr.getResponse()) {
					data.add(gene);
				}				
				totalCount = sr.getTotalCount();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Gene> getGenes() {
		return data;
	}
}
