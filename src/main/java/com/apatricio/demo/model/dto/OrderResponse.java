package com.apatricio.demo.model.dto;

import com.apatricio.demo.model.OrderEntity;
import com.apatricio.demo.model.Status;

public class OrderResponse
{
    private Integer id;
    private Integer distance;
    private Status status;

    public OrderResponse(Status status)
    {
        this.status = status;
    }

    public OrderResponse(OrderEntity order)
    {
        this.id = order.getId();
        this.distance = order.getDistance();
        this.status = order.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
