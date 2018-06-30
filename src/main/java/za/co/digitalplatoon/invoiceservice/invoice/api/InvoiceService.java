package za.co.digitalplatoon.invoiceservice.invoice.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;

@Transactional
public class InvoiceService {
	@Autowired
	private Orchestration orch;

	public Invoice createInvoice(Invoice invoice) {
		return orch.createInvoice(invoice);

	}

	public Invoice viewInvoiceById(long invoiceId) {
		return orch.viewInvoiceById(invoiceId);
	}

}
