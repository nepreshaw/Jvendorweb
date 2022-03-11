package com.maxtrain.bootcamp.invoiceline;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceLineRepository extends CrudRepository<InvoiceLine, Integer> {
	Optional<InvoiceLine> findByQuantity(Integer quantity);

}
