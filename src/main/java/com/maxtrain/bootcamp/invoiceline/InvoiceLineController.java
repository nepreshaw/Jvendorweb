package com.maxtrain.bootcamp.invoiceline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoicelines")
public class InvoiceLineController {
	@Autowired
	private InvoiceLineRepository ilrepo;

}
