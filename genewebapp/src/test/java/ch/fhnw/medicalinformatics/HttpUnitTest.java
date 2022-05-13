package ch.fhnw.medicalinformatics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.WebClient;

public class HttpUnitTest {
	
	@Test
	public void homePage() throws Exception {
		
		if (!TestSettings.EXECUTE_WEBTESTS) return;
		
		final WebClient webClient = new WebClient();
		final HtmlPage page = (HtmlPage)webClient.getPage(TestSettings.URL);
		assertEquals("FHNW - MSCMI - Gene Information Service", page.getTitleText());
		webClient.close();
	}
	
	@Test
	public void submitForm() throws Exception {
		
		if (!TestSettings.EXECUTE_WEBTESTS) return;
		
		final WebClient webClient = new WebClient();
		final HtmlPage page = (HtmlPage)webClient.getPage(TestSettings.URL);
		final HtmlForm form = page.getFormByName("inputform");
		
		// select search option
		HtmlSelect select = (HtmlSelect) page.getElementById("inputform:searchoption_input");
		HtmlOption option = select.getOptionByValue("Search by ID");
		select.setSelectedAttribute(option, true);
		
		// enter search term
		HtmlInput input = (HtmlInput) page.getElementById("inputform:searchterm");
		input.setValueAttribute("70");
		
		final HtmlButton retrieveButton = form.getButtonByName("inputform:retrieveButton");
		final HtmlPage resultPage = retrieveButton.click();
		
		//System.out.println(resultPage.asXml());
		List<HtmlElement> tableElements = resultPage.getBody().getElementsByAttribute("tbody", "id", "inputform:datatable_data");
		assertTrue(tableElements.size() == 1);
		assertTrue(tableElements.get(0) instanceof HtmlTableBody);
		HtmlTableBody tableBody = (HtmlTableBody) tableElements.get(0);
		assertTrue(tableBody.getRows().size() == 1);
		List<HtmlTableCell> cells = tableBody.getRows().get(0).getCells();
		String content = cells.get(1).asText();
		assertTrue(content.equals("ACTC1"));
		webClient.close();
	}
	
}