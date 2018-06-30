package za.co.digitalplatoon.invoiceservice.invoice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceApplicationTests {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private final String INVOICE_REQUEST = "{\n\t\"client\": \"Benedict\",\n\t\"vatRate\": 15,\n\t\"lineItems\": [\n\t\t{\n\t\t\t\"quantity\": 1,\n\t\t\t\"description\": \"Ford\",\n\t\t\t\"unitPrice\": 82.90\n\t\t}\n\t]\n}";
	private MockMvc mockMvc;

	@Autowired
	private InvoiceController invoiceController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
	}

	@Test
	public void shouldAddInvoice() throws Exception {
		this.mockMvc.perform(post("/invoices").content(INVOICE_REQUEST).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

	}

	@Test
	public void shouldNotFindTheInvoice() throws Exception {
		this.mockMvc.perform(get("/invoices/1234/").contentType(APPLICATION_JSON_UTF8)).andExpect(status().is(404));
	}

	//
	//
	//
	//
	//
	// Orchestration orch;
	// public static final String REST_SERVICE_URI =
	// "http://localhost:8080/invoices";
	// Invoice invoice;
	// LineItem lineItems;
	//
	//
	// @Test
	// public void contextLoads() {
	//
	// }
	//
	// @Test
	// public void addInvoice() {
	// System.out.println("--------------Testing Add Invoice API-----------");
	// RestTemplate restTemplate = new RestTemplate();
	//
	// Invoice invoice = restTemplate.getForObject(REST_SERVICE_URI, Invoice.class);
	//
	// }
	//
	// @Test
	// public void viewInvoiceById() throws IOException {
	//
	// }

}
