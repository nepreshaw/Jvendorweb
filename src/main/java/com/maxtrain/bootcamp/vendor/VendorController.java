package com.maxtrain.bootcamp.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorController {
	@Autowired
	private VendorRepository vendRepo;
	
	@GetMapping("code/{code}")
	public ResponseEntity<Vendor> getByCode(@PathVariable String code){
		var vendor = vendRepo.findByCode(code);
		if(vendor.isEmpty()) {
			return new ResponseEntity<Vendor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor.get(), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Vendor>> getVendors(){
		var vendors = vendRepo.findAll();
		return new ResponseEntity<Iterable<Vendor>>(vendors, HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendor> getVendor(@PathVariable int id){
		var vendor = vendRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Vendor> postVendor(@RequestBody Vendor vendor){
		if(vendor == null || vendor.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var vend = vendRepo.save(vendor);
		return new ResponseEntity<Vendor>(vend, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity<Vendor> putVendor(@PathVariable int id, @RequestBody Vendor vendor){
		if(vendor == null || vendor.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var vend = vendRepo.findById(vendor.getId());
		if(vend.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteVendor(@PathVariable int id){
		var vendor = vendRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.delete(vendor.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
