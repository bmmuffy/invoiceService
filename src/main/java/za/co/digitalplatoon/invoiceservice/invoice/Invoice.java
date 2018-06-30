package za.co.digitalplatoon.invoiceservice.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQuery(name = "returnAll", query = "SELECT customer FROM Invoice AS customer ORDER BY customer.client")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String client;
	private Long vatRate;
	private Date invoiceDate = Calendar.getInstance().getTime();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return this.vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getDate() {
		return this.invoiceDate;
	}

	public void setDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<LineItem> lineItems;

	public BigDecimal getSubTotal() {
		BigDecimal subTotal = new BigDecimal("00.00");
		for (LineItem lineItem : lineItems) {
			subTotal = subTotal.add(lineItem.getLineItemTotal());
		}
		return subTotal;
	}

	public BigDecimal getVat() {
		BigDecimal vat = new BigDecimal(vatRate).multiply(new BigDecimal(0.01));

		return getSubTotal().multiply(vat).setScale(2, RoundingMode.HALF_UP);

	}

	public BigDecimal getTotal() {
		return getSubTotal().add(getVat()).setScale(2, RoundingMode.HALF_UP);
	}

}
