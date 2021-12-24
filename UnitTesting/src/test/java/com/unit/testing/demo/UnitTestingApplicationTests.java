package com.unit.testing.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unit.testing.demo.controller.Library;
import com.unit.testing.demo.controller.LibraryController;
import com.unit.testing.demo.model.addRespose;
import com.unit.testing.demo.repository.LiraryReposotry;
import com.unit.testing.demo.service.LibraryService;

@SpringBootTest
@AutoConfigureMockMvc
class UnitTestingApplicationTests {

	@MockBean
	LibraryService libservice;

	@MockBean
	LiraryReposotry liraryReposotry;

	@Autowired
	LibraryController libCont;

	@Autowired
	MockMvc mockMvc;

//	@Autowired
//	Library lib;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testBuildIdLogic() {
//		String id = libservice.buildId("zman", 20);
//		assertEquals(id,"OLDzman20");
//		
//		String id1 = libservice.buildId("man", 20);
//		assertEquals(id1,"man20");
//	}

	@Test
	public void addBook() {

		Library library = buildLibrary();

		when(libservice.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
		when(libservice.checkBookAlreadyExist(library.getId())).thenReturn(true);
		when(liraryReposotry.save(any())).thenReturn(library);
		ResponseEntity respose = libCont.addBook(buildLibrary());

		System.out.println(respose.getStatusCode());
		assertEquals(respose.getStatusCode(), HttpStatus.ACCEPTED);

		addRespose ad = (addRespose) respose.getBody();
		assertEquals(ad.getId(), library.getId());

		assertEquals(ad.getMsg(), "Book already exist!!!");

	}

	@Test
	public void addBookConntrollerTest() throws Exception {

		Library library = buildLibrary();
		ObjectMapper mp = new ObjectMapper();
		String jsonString = mp.writeValueAsString(library);

		when(libservice.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
		when(libservice.checkBookAlreadyExist(library.getId())).thenReturn(false);
		when(liraryReposotry.save(any())).thenReturn(library);
		this.mockMvc.perform(
				post("/addbooks").contentType(org.springframework.http.MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isCreated());
	}

	private Library buildLibrary() {
		Library lib = new Library();
		lib.setAisle(123);
		lib.setAuthor("MySon");
		lib.setBook_name("Hero");
		lib.setIsbn("abcd");
		lib.setId("abcd123");
		return lib;
	}

}
