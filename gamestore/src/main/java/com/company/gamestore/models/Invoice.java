package com.company.gamestore.models;

import javax.persistence.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return "Invoice{" +
                "id=" + id +
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
