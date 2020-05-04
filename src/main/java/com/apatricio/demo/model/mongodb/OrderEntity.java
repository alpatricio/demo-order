package com.apatricio.demo.model.mongodb;

import com.apatricio.demo.model.Status;
import com.apatricio.demo.model.dto.OrderRequest;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ordermongo")
public class OrderEntity {

    @Id
    public ObjectId _id;
    private String[] origin;
    private String[] destination;
    private Integer distance;
    private Status status;

    public OrderEntity() {
    }

    public OrderEntity(OrderRequest orderRequest, Integer distance) {
        this.origin = orderRequest.getOrigin();
        this.destination  = orderRequest.getDestination();
        this.status = Status.UNASSIGNED;
        this.distance = distance;
    }

    public String get_id() {
        return _id!=null ? _id.toHexString() : null;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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