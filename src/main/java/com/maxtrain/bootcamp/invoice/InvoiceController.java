package com.maxtrain.bootcamp.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	@Autowired
	private InvoiceRepository invRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Invoice>> getInvoices(){
		var invoices = invRepo.findAll();
		return new ResponseEntity<Iterable<Invoice>>(invoices, HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable int id){
		var invoice = invRepo.findById(id);
		if(invoice.isEmpty()) {
			return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoice.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Invoice> postInvoice(@RequestBody Invoice invoice){
		if(invoice == null || invoice.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inv = invRepo.save(invoice);
		return new ResponseEntity<Invoice>(invoice, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Invoice> putInvoice(@PathVariable int id, @RequestBody Invoice invoice){
		if(invoice == null || invoice.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inv = invRepo.findById(invoice.getId());
		if(inv.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoice(@PathVariable int id){
		var inv = invRepo.findById(id);
		if(inv.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.delete(inv.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
