package ch.fhnw.medicalinformatics;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.NamedEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@NamedEvent
@SessionScoped
@ManagedBean(name = "geneService")
public class GeneService {

	private String searchOption;
	private String searchTerm;
	private List<Gene> data = new ArrayList<>();
	private long totalCount = 0;
	private List<Integer> pages = new ArrayList<>();
	private int maxTblRows = 10;
	private int currentRows;
	private int selectedPage;
	private boolean displayNextPageBtn;
	private boolean displayPreviousPageBtn;
	private String hostname = "http://localhost:9090";

	private final OkHttpClient httpClient;

	public GeneService() {
		httpClient = new OkHttpClient.Builder()
				.connectTimeout(180, TimeUnit.SECONDS)
				.readTimeout(180, TimeUnit.SECONDS)
				.writeTimeout(180, TimeUnit.SECONDS)
				.build();
	}

	public boolean getIsApiRunning() {
		Request request = null;
		String serviceCall = "/geneservice/health-check";
		request = new Request.Builder().url(hostname + serviceCall).build();
		boolean isRunning = false;
		try (Response response = httpClient.newCall(request).execute()) {
			Gson g = new Gson();
			Type resultType = null;
			resultType = new TypeToken<String>() {
			}.getType();
			String result = g.fromJson(response.body().string(), resultType);
			isRunning = "OK".equals(result);
		} catch (Exception ex) {
			isRunning = false;
		}
		return isRunning;
	}

	public void retrieveData(boolean reset) {
		System.out.println("retrieve data");
		
		pages.clear();
		data.clear();
		
		if(reset){
			currentRows = 0;
			selectedPage = 0;
		}
		// retrieve data from the service
		Request request = null;
		String serviceCall = "";
		if (searchOption.toUpperCase().contains("ID")) {
			serviceCall = "/geneservice/byid?id=" + searchTerm;
		} else if (searchOption.toUpperCase().contains("SYMBOL")) {
			serviceCall = "/geneservice/bysymbol?symbol=" + searchTerm + "&offset=" + (currentRows / maxTblRows) + "&pageSize=" + maxTblRows;
		} else if (searchOption.toUpperCase().contains("DESCRIPTION")) {
			serviceCall = "/geneservice/bydescription?description=" + searchTerm + "&offset=" + (currentRows / maxTblRows) + "&pageSize=" + maxTblRows;
		} else {
			System.out.println("invalid search");
		}

		request = new Request.Builder().url(hostname + serviceCall).build();

		try (Response response = httpClient.newCall(request).execute()) {
			Gson g = new Gson();
			Type resultType = null;
			resultType = new TypeToken<GeneSearchResponse>() {
			}.getType();
			GeneSearchResponse sr = g.fromJson(response.body().string(), resultType);
			for (Gene gene : sr.getResponse()) {
				data.add(gene);
			}
			this.setTotalCount(sr.getTotalCount());
			int maxPages = (int) Math.ceil((double) (this.totalCount / maxTblRows));
			for (int i = 1; i <= maxPages; i++) {
				pages.add(i);
			}
			handlePagination();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<String> getSearchOptions() {
		List<String> result = new ArrayList<String>();
		result.add("Search by ID");
		result.add("Search by Symbol");
		result.add("Search by Description");
		return result;
	}

	public void nextPage() {
		currentRows += maxTblRows;
		if (currentRows > this.totalCount) {
			currentRows = 0;
		}
		retrieveData(false);
	}

	public void previousPage() {
		currentRows -= maxTblRows;
		if (currentRows < maxTblRows) {
			currentRows = 0;
		}
		retrieveData(false);
	}

	private void handlePagination() {
		displayNextPageBtn = (currentRows + maxTblRows) < this.totalCount;
		displayPreviousPageBtn = currentRows >= maxTblRows;
		selectedPage = (int) Math.ceil((double) (currentRows / maxTblRows)) + 1;
	}

	public void pageChange(AjaxBehaviorEvent abe) {
		currentRows = (selectedPage - 1) * maxTblRows;
		retrieveData(false);
	}

	public List<Gene> getGenes() {
		return data;
	}

	public long getTotalCount() {
		return totalCount;
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

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setDisplayNextPageBtn(boolean displayNextPageBtn) {
		this.displayNextPageBtn = displayNextPageBtn;
	}

	public void setDisplayPreviousPageBtn(boolean displayPreviousPageBtn) {
		this.displayPreviousPageBtn = displayPreviousPageBtn;
	}

	public boolean getDisplayNextPageBtn() {
		return displayNextPageBtn;
	}

	public boolean getDisplayPreviousPageBtn() {
		return displayPreviousPageBtn;
	}

	public int getMaxTblRows() {
		return maxTblRows;
	}

	public void setMaxTblRows(int maxTblRows) {
		this.maxTblRows = maxTblRows;
	}

	public int getCurrentRows() {
		return currentRows;
	}

	public void setSelectedPage(int selectedPage) {
		this.selectedPage = selectedPage;
	}

	public int getSelectedPage() {
		return selectedPage;
	}

	public List<Integer> getPages() {
		return pages;
	}
}
