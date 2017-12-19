package fr.dta.book;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.dta.test.IntegrationTest;
import fr.dta.webapprest.model.Book;
import fr.dta.webapprest.service.BookService;

@Sql("classpath:test-book-data.sql")
public class BookIT extends IntegrationTest {

	@Autowired
	BookService bookService;

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testCreateBook() throws Exception {
		Book b = new Book();
		b.setTitle("L'histoire de Caleb");
		b.setAuthor("Son voisin");
		b.setIsbn("9876543210");
		b.setNbPages(10);
		b.setPublicationDate(new Date());

		this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(b))).andExpect(status().isCreated());
		this.mockMvc.perform(get("/books")).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(status().isOk());

	}

	@Test
	// @WithMockUser
	public void testCreatePreconditionFail() throws Exception {
		Book b = new Book();
		b.setTitle(""); // @NotBlank present in Book class
		b.setAuthor("Son voisin");
		b.setIsbn("9876543210");
		b.setNbPages(10);
		b.setPublicationDate(new Date());

		this.mockMvc
				.perform(post("/books").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
						.content(jsonHelper.serialize(b)))
				.andDo(MockMvcResultHandlers.print())
				// .andExpect(jsonPath("$[*].field", containsInAnyOrder("L'histoire de Caleb",
				// "Son voisin")))
				// .andExpect(jsonPath("$[0].objectName").value("b"))
				// .andExpect(jsonPath("$[*].code", containsInAnyOrder("NotBlank", "NotBlank")))
				.andExpect(status().isBadRequest())/* isPreconditionFailed()) */;

	}

	// @Test
	// // @WithMockUser
	// public void testUpdate() throws Exception {
	// Book book = bookService.getById(1);
	// Assert.assertEquals("sir Caleb Joseph", book.getAuthor());
	//
	// book.setAuthor("sir Caleb Joseph");
	// this.mockMvc
	// .perform(put("/books",
	// book.getId()).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
	// .content(jsonHelper.serialize(book)))
	// .andExpect(jsonPath("$.author").value("sir Caleb
	// Joseph")).andExpect(status().isOk());
	// }

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetUser() throws Exception {
		this.mockMvc.perform(get("/books/{id}", 1)).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.author").value("sir Caleb Joseph"))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetNotFoundUser() throws Exception {
		this.mockMvc.perform(get("/books/{id}", 1)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isNotFound());
	}

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetAllUsers() throws Exception {
		this.mockMvc.perform(get("/books")).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(status().isOk());
	}

}
