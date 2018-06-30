package za.co.digitalplatoon.invoiceservice.invoice.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;

@Repository
public class Orchestration {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Invoice createInvoice(Invoice invoice) {

		entityManager.persist(invoice);
		entityManager.flush();
		return invoice;
	}

	public Invoice viewInvoiceById(long invoiceId) {
		Invoice invoice = entityManager.find(Invoice.class, invoiceId);
		if (invoice == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The invoice was not found");
		}
		return invoice;
	}

	public List<Invoice> viewAllInvoices() {
		TypedQuery<Invoice> response = entityManager.createNamedQuery("returnAll", Invoice.class);

		if (response == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any invoices");
		}
		return response.getResultList();
	}

}
