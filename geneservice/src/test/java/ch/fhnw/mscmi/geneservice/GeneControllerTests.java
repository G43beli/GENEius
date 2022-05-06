package ch.fhnw.mscmi.geneservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void retrieveGeneByIdTest() throws Exception {
		ResultActions ra = this.mockMvc.perform(get("/geneservice/byid").param("id", "1"))
									   .andDo(print())
									   .andExpect(status().isOk())
									   .andExpect(jsonPath("$.geneId").value(1));
	}
}
