package com.example.finaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseActivation {
    private Transaction transaction;

    @JsonProperty("iseligible")
    private String iseligible;

    @JsonProperty("service_id_a")
    private String service_id_a;

    @JsonProperty("notification")
    private String notification;

    public Transaction getTransaction() {
        return transaction;
    }

    public String getIseligible() {
        return iseligible;
    }

    public String getService_id_a() {
        return service_id_a;
    }

    public String getNotification() {
        return notification;
    }
}
