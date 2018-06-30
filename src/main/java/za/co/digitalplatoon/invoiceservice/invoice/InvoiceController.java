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

import za.co.digitalplatoon.invoiceservice.invoice.api.InvoiceService;

@RestController
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@ResponseBody
	@RequestMapping(value = "/invoices", method = POST, produces = "application/json")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		try {
			invoiceService.createInvoice(invoice);
		} catch (Exception e) {
			logger.error("Error occured while trying to process the request", e);
			return null;
		}

		return null;

	}

	@ResponseBody
	@RequestMapping(value = "/invoices", method = GET, produces = "application/json")
	public List<Invoice> viewAllInvoices() {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/invoices/{invoiceId}", method = GET, produces = "application/json")
	public Invoice viewInvoice(@PathVariable(value = "invoiceId") Long invoiceId) { // no other way i could pass it
		Invoice response;

		try {
			response = invoiceService.viewInvoiceById(invoiceId);
		} catch (Exception e) {
			logger.error("Error occured while trying to process the request", e);
			return null;
		}

		return response;

	}

}
