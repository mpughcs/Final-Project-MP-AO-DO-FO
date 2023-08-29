package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.InvoiceRepo;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    ServiceLayer serviceLayer;

//    endpoint to make a purchase
    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice purchase(@RequestBody @Valid InvoiceViewModel ivm){
        return invoiceRepo.save(serviceLayer.createInvoice(ivm));
    }
    // Create Invoice
    // should update to use service layer instead of repo directly
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    // Read an Invoice by ID
    @GetMapping("/{invoice_id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Integer invoice_id) {
        return invoiceRepo.findById(invoice_id).orElse(null);
    }

    // Read all Invoices
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    // Find Invoices by Customer Name
    @GetMapping("/customer/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerName(@PathVariable String name) {
        return invoiceRepo.findByName(name);
    }

    // Update an Invoice by ID
    @PutMapping("/{invoice_id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice(@PathVariable Integer invoice_id, @RequestBody @Valid Invoice invoice) {
        if (invoiceRepo.existsById(invoice_id)) {
            invoice.setId(invoice_id);
            return invoiceRepo.save(invoice);
        } else {
            return null;
        }
    }

    // Delete an Invoice by ID
    @DeleteMapping("/{invoice_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Integer invoice_id) {
        invoiceRepo.deleteById(invoice_id);
    }
}
