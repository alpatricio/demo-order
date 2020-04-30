package com.apatricio.demo.model.dto;

import javax.validation.constraints.Size;

public class OrderRequest
{
    @Size(min = 2)
    private String[] origin;

    @Size(min = 2)
    private String[] destination;

    public String[] getOrigin()
    {
        return origin;
    }

    public void setOrigin(String[] origin)
    {
        this.origin = origin;
    }

    public String[] getDestination()
    {
        return destination;
    }

    public void setDestination(String[] destination)
    {
        this.destination = destination;
    }
}
