package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "tshirt")
public class Tshirt {
    @Id
    @Column(name = "tshirt_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String size;

    private String color;

    private String description;

    private BigDecimal price;

    private int quantity;

    public Tshirt(String size, String color, String description, BigDecimal price, int quantity){
        this.size = size;
        this.color = color;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Tshirt(){}

    public int getId(){
        return id;
    }

    public int getQuantity(){
        return quantity;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public String getSize(){
        return size;
    }

    public String getDescription(){
        return description;
    }

    public String getColor(){
        return color;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setSize(String size){
        this.size = size;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setColor(String color){
        this.color = color;
    }

    @Override
    public boolean equals(Object o){
    if(this == o)return true;
    if(!(o instanceof Tshirt)) return false;
    Tshirt t = (Tshirt) o;
    return Objects.equals(getColor(), ((Tshirt) o).getColor()) && Objects.equals(getDescription(), ((Tshirt) o).getDescription())
            && Objects.equals(getSize(), ((Tshirt) o).getSize()) && Objects.equals(getId(), ((Tshirt) o).getId())
            && Objects.equals(getPrice(), ((Tshirt) o).getPrice()) && Objects.equals(getQuantity(), ((Tshirt) o).getQuantity());
    }

    @Override
    public int hashCode(){ return Objects.hash(getColor(), getDescription(), getSize(), getId(), getPrice(), getQuantity());}
}
