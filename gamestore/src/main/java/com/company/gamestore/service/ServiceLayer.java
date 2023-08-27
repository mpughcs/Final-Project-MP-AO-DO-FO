
package com.company.gamestore.service;

import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.Tshirt;
import com.company.gamestore.repositories.*;
import com.company.gamestore.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ServiceLayer {
    private InvoiceRepo invoiceRepo;
    private ConsoleRepo consoleRepo;
    private TshirtRepo tshirtRepo;
    private TaxRepo taxRepo;
    private GameRepo gameRepo;
    private FeeRepo feeRepo;

    @Autowired
    public ServiceLayer(
            InvoiceRepo invoiceRepo,
            ConsoleRepo consoleRepo,
            TshirtRepo tshirtRepo,
            TaxRepo taxRepo,
            GameRepo gameRepo,
            FeeRepo feeRepo
    ) {
        this.invoiceRepo = invoiceRepo;
        this.consoleRepo = consoleRepo;
        this.tshirtRepo = tshirtRepo;
        this.taxRepo = taxRepo;
        this.gameRepo = gameRepo;
        this.feeRepo = feeRepo;
    }

    @Transactional
    public Invoice createInvoice(InvoiceViewModel ivm) {
        System.out.println("entered create invoice");
        Invoice toReturn = new Invoice();
        toReturn.setName(ivm.getCustomerName());
        toReturn.setStreet(ivm.getStreet());
        toReturn.setCity(ivm.getCity());
        toReturn.setState(ivm.getState());
        toReturn.setZipcode(ivm.getZipcode());
        toReturn.setItem_type(ivm.getItemType());
        toReturn.setItem_id(ivm.getItemId());
        toReturn.setQuantity(ivm.getQuantity());

        String tableName = ivm.getItemType();
        int itemId = ivm.getItemId();
        System.out.println("item id: " + itemId);
        int quantity = ivm.getQuantity();
        double processingFee = 0;
        double total = 0;
        double taxRate=0;

        if (tableName.equals("Game")) {
            Game game = gameRepo.findById(itemId).orElse(null);
            BigDecimal unitPrice = BigDecimal.valueOf(game.getPrice().doubleValue());
            toReturn.setUnit_price(unitPrice);

            if (game.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough games in stock");
            }
            game.setQuantity(game.getQuantity() - quantity);
            gameRepo.save(game);

            total += unitPrice.multiply(BigDecimal.valueOf(quantity)).doubleValue();
        }
        if (tableName.equals("Console")) {
            Console console = consoleRepo.findById(itemId).orElse(null);
            BigDecimal unitPrice = BigDecimal.valueOf(console.getPrice().doubleValue());
            toReturn.setUnit_price(unitPrice);

            if (console.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough consoles in stock");
            }
            console.setQuantity(console.getQuantity() - quantity);
            consoleRepo.save(console);
            total += unitPrice.multiply(BigDecimal.valueOf(quantity)).doubleValue();

        }
        if (tableName.equals("T-Shirt")) {
            Tshirt tshirt = tshirtRepo.findById(itemId).orElse(null);
            BigDecimal unitPrice = BigDecimal.valueOf(tshirt.getPrice().doubleValue());
            toReturn.setUnit_price(unitPrice);

            if (tshirt.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough tshirts in stock");
            }
            tshirt.setQuantity(tshirt.getQuantity() - quantity);
            tshirtRepo.save(tshirt);
            total += unitPrice.multiply(BigDecimal.valueOf(quantity)).doubleValue();


        }
//        set subtotal
        toReturn.setSubtotal(BigDecimal.valueOf(total));


//        adding tax
        System.out.println("total: "+total);

        taxRate = taxRepo.findByState(ivm.getState()).getRate().doubleValue();
        System.out.println("tax rate: "+taxRate);
//        maintain 2 decimal places, maintain precision
        total = Math.round(total*(1+taxRate)*100.0)/100.0;
        System.out.println("total after tax: "+total);

//        total += total*(taxRate);
//        System.out.println("total after tax: "+total);

//        adding processing fee

        processingFee = feeRepo.findByProductType(tableName).getFee().doubleValue();



        if (quantity > 10) {
            processingFee += 15.49;
        }

        toReturn.setProcessing_fee(BigDecimal.valueOf(processingFee));
        toReturn.setTax(BigDecimal.valueOf(total*taxRate));
        total += processingFee;
        toReturn.setTotal( BigDecimal.valueOf(total));

        return toReturn;
    }
}

