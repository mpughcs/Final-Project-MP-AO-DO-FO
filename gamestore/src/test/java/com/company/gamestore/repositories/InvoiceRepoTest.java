package com.company.gamestore.repositories;

import com.company.gamestore.models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceRepoTest {

    @Autowired
    private InvoiceRepo repo;

    Invoice testInvoice = new Invoice(
            "John Doe",
            "123 Elm St",
            "Springfield",
            "IL",
            "12345",
            "Game",
            1,
            3,
            (long) 59.99,
            (long) 179.97,
            (long) 10.80,
            (long) 5.00,
            (long) 195.77
    );

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    @Test
    void shouldAddInvoice() throws Exception {
        testInvoice = repo.save(testInvoice);
        Optional<Invoice> expected = repo.findById(testInvoice.getId());
        assertEquals(expected.get(), testInvoice);
    }

    @Test
    void shouldFindInvoices() throws Exception{
        testInvoice = repo.save(testInvoice);
        List<Invoice> expected = new ArrayList<>();
        expected.add(testInvoice);
        assertEquals(expected, repo.findAll());
    }

    @Test
    void shouldFindInvoiceById() {
        testInvoice = repo.save(testInvoice);
        Optional<Invoice> expected = repo.findById(testInvoice.getId());
        assertEquals(expected.get(), testInvoice);
    }

    @Test
    void shouldUpdateInvoice() {
        testInvoice = repo.save(testInvoice);
        Invoice updated = new Invoice("Jane Smith", "456 Oak St", "Shelbyville", "IL", "67890", "Console", 2, 1,
                (long) 59.99,
                (long) 179.97,
                (long) 10.80,
                (long) 5.00,
                (long) 195.77);
        updated.setId(testInvoice.getId());
        repo.save(updated);
        assertEquals(updated, repo.findById(testInvoice.getId()).get());
    }

    @Test
    void shouldDeleteInvoice() {
        testInvoice = repo.save(testInvoice);
        Optional<Invoice> optionalInvoice;
        repo.delete(testInvoice);

        optionalInvoice = repo.findById(testInvoice.getId());
        assertFalse(optionalInvoice.isPresent(), "Invoice should be deleted");
    }

    @Test
    void shouldGetInvoiceByName() {
        testInvoice = repo.save(testInvoice);
        List<Invoice> namedInvoices = new ArrayList<>();
        namedInvoices.add(testInvoice);
        assertEquals(namedInvoices, repo.findByName("John Doe"));
    }
}
