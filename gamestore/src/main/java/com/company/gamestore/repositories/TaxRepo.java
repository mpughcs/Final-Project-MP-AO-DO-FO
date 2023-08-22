package com.company.gamestore.repositories;

import com.company.gamestore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepo extends JpaRepository<Tax, Long> {

}
