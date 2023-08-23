package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    // Create Invoice
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    // Read an Invoice by ID
    @GetMapping("/{invoice_id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Long invoice_id) {
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
    public Invoice updateInvoice(@PathVariable Long invoice_id, @RequestBody Invoice invoice) {
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
    public void deleteInvoice(@PathVariable Long invoice_id) {
        invoiceRepo.deleteById(invoice_id);
    }
}
