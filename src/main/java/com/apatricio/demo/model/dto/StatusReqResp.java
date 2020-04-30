package com.apatricio.demo.model.dto;

import com.apatricio.demo.model.Status;

import javax.validation.constraints.NotNull;

public class StatusReqResp {
    @NotNull
    Status status;

    public StatusReqResp() {

    }

    public StatusReqResp(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
