package com.company.gamestore.repositories;

import com.company.gamestore.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    List<Invoice> findByName(String name);
}
