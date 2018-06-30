package za.co.digitalplatoon.invoiceservice.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.digitalplatoon.invoiceservice.invoice.api.CustomException;
import za.co.digitalplatoon.invoiceservice.invoice.api.Orchestration;

@RestController
public class InvoiceController {
	@Autowired
	private Orchestration orch;
	private Invoice response;
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@ResponseBody
	@RequestMapping(value = "/invoices", method = POST, produces = "application/json")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		try {
			response = orch.createInvoice(invoice);
		} catch (Exception e) {
			logger.error("Error occured while trying to process the request", e);
			return null;
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/invoices", method = GET, produces = "application/json")
	public List<Invoice> viewAllInvoices() {
		return orch.viewAllInvoices();
	}

	@ResponseBody
	@RequestMapping(value = "/invoices/{invoiceId}", method = GET, produces = "application/json")
	public Invoice viewInvoice(@PathVariable(value = "invoiceId") Long invoiceId) { // no other way i could pass it

		try {
			response = orch.viewInvoiceById(invoiceId);
		} catch (Exception e) {
			logger.error("Error occured while trying to process the request", e);
			throw new CustomException("The invoice with this Id: " + invoiceId + " Is not Found ");
		}

		return response;

	}

}
