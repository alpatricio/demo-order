package com.apatricio.demo.model;

import com.apatricio.demo.model.dto.OrderRequest;

import javax.persistence.*;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String[] origin;
    private String[] destination;
    private Integer distance;
    private Status status;

    public OrderEntity(){

    }

    public OrderEntity(OrderRequest orderRequest, Integer distance){
        this.origin = orderRequest.getOrigin();
        this.destination  = orderRequest.getDestination();
        this.status = Status.UNASSIGNED;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getOrigin() {
        return origin;
    }

    public void setOrigin(String[] origin) {
        this.origin = origin;
    }

    public String[] getDestination() {
        return destination;
    }

    public void setDestination(String[] destination) {
        this.destination = destination;
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
