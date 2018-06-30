package za.co.digitalplatoon.invoiceservice.invoice.api;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;

@Repository
public class Orchestration {
	Orchestration orch = new Orchestration();
	@PersistenceContext
	private EntityManager entityManager;

	// private SecureRandom random = new SecureRandom();

	public Invoice createInvoice(Invoice invoice) {
		// Long randomId = new BigInteger(130, random).longValue();
		// invoice.setId(randomId);
		entityManager.persist(invoice);

		return null;
	}

	public Invoice viewInvoiceById(long invoiceId) {
		return entityManager.find(Invoice.class, invoiceId);
	}

}
