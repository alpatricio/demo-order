package com.apatricio.demo.service;

import com.apatricio.demo.exception.ServiceException;
import com.apatricio.demo.model.dto.OrderRequest;
import com.apatricio.demo.model.dto.OrderResponse;
import com.apatricio.demo.model.dto.StatusReqResp;

import java.util.List;

public interface OrderService
{
    OrderResponse placeOrder(OrderRequest req);
    StatusReqResp takeOrder(String id, StatusReqResp status) throws ServiceException;
    List<OrderResponse> listOrder(int page, int limit);
}
