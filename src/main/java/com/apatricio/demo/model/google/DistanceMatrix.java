package com.apatricio.demo.model.google;

public class DistanceMatrix
{
    private Element distance;
    private Element duration;
    private String status;

    public Element getDistance()
    {
        return distance;
    }

    public void setDistance(Element distance)
    {
        this.distance = distance;
    }

    public Element getDuration()
    {
        return duration;
    }

    public void setDuration(Element duration)
    {
        this.duration = duration;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
