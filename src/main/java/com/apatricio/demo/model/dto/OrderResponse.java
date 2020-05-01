package com.apatricio.demo.model.dto;

import com.apatricio.demo.model.mongodb.OrderEntity;
import com.apatricio.demo.model.Status;

public class OrderResponse {
    private String id;
    private Integer distance;
    private Status status;

    public OrderResponse(Status status) {
        this.status = status;
    }

    public OrderResponse(OrderEntity order) {
        if (order.get_id() != null)
            this.id = String.valueOf(order.get_id());
        this.distance = order.getDistance();
        this.status = order.getStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
