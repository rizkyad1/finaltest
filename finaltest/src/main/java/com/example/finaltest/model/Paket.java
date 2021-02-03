package com.example.finaltest.model;

import javax.persistence.*;

@Entity
@Table(name = "package_activation")
public class Paket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String activation_id;

    @Column(name = "transaction_id")
    private String transaction_id;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "product_id")
    private String product_id;

    @Column(name = "product_price")
    private String product_price;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "created_by")
    private int created_by;

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getActivation_id() {
        return activation_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
