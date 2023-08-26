package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="invoice_id")
    private Integer invoice_id;

    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String item_type;
    private Integer item_id;
    private Integer quantity;
    private Long unit_price;

    private Long subtotal;
    private Long tax;
    private Long processing_fee;
    private Long total;

    public Invoice() {
    }

    public Invoice(String name, String street, String city, String state, String zipcode, String item_type, Integer item_id, Integer quantity, Long unit_price, Long subtotal, Long tax, Long processing_fee, Long total) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.item_type = item_type;
        this.item_id = item_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processing_fee = processing_fee;
        this.total = total;
    }

    public Integer getId() {
        return invoice_id;
    }

    public void setId(Integer id) {
        this.invoice_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zipcode;
    }

    public void setZip(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return item_type;
    }

    public void setItemType(String item_type) {
        this.item_type = item_type;
    }

    public Integer getItemId() {
        return item_id;
    }

    public void setItemId(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return invoice_id == invoice.invoice_id &&
                Objects.equals(name, invoice.name) &&
                Objects.equals(street, invoice.street) &&
                Objects.equals(city, invoice.city) &&
                Objects.equals(state, invoice.state) &&
                Objects.equals(zipcode, invoice.zipcode) &&
                Objects.equals(item_type, invoice.item_type) &&
                item_id == invoice.item_id &&
                quantity == invoice.quantity &&
                unit_price == invoice.unit_price &&
                subtotal == invoice.subtotal &&
                tax == invoice.tax &&
                processing_fee == invoice.processing_fee &&
                total == invoice.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, name, street, city, state, zipcode, item_type, item_id, quantity, unit_price, subtotal, tax, processing_fee, total);
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoice_id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_id=" + item_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processing_fee=" + processing_fee +
                ", total=" + total +
                '}';
    }
}
