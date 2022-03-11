package com.maxtrain.bootcamp.invoiceline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/api/invoicelines")
public class InvoiceLineController {
	@Autowired
	private InvoiceLineRepository ilrepo;
	
	@GetMapping
	public ResponseEntity<Iterable<InvoiceLine>> getInvoiceLines() {
		var il = ilrepo.findAll();
		return new ResponseEntity<Iterable<InvoiceLine>>(il, HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<InvoiceLine> getInvoiceLine(@PathVariable int id){
		var il = ilrepo.findById(id);
		if(il.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InvoiceLine>(il.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<InvoiceLine> postInvoiceLine(@RequestBody InvoiceLine invoiceline){
		if(invoiceline == null || invoiceline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var il = ilrepo.save(invoiceline);
		return new ResponseEntity<InvoiceLine>(il, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<InvoiceLine> putInvoiceLine(@PathVariable int id, @RequestBody InvoiceLine invoiceline){
		if(invoiceline == null || invoiceline.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var il = ilrepo.findById(invoiceline.getId());
		if(il.isEmpty()) {
			return new ResponseEntity<>(invoiceline, HttpStatus.NOT_FOUND);
		}
		ilrepo.save(invoiceline);
		return new ResponseEntity<>(invoiceline, HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoiceLine(@PathVariable int id) {
		var il = ilrepo.findById(id);
		if(il.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		ilrepo.delete(il.get());
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}
