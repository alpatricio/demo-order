package com.apatricio.demo.service.impl;

import com.apatricio.demo.repository.OrderRepository;
import com.apatricio.demo.model.mongodb.OrderEntity;
import com.apatricio.demo.restclient.DistanceApiRestClient;
import com.apatricio.demo.exception.ServiceException;
import com.apatricio.demo.model.dto.OrderRequest;
import com.apatricio.demo.model.dto.OrderResponse;
import com.apatricio.demo.model.Status;
import com.apatricio.demo.model.dto.StatusReqResp;
import com.apatricio.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService
{
    public static final String TAKE_ORDER_ERROR = "Order not found or is already taken";
    @Autowired
    DistanceApiRestClient client;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest req) {
        Integer distance = client.callGoogle(req);
        return new OrderResponse(orderRepository.save(new OrderEntity(req, distance)));
    }

    @Override
    public StatusReqResp takeOrder(String id, StatusReqResp status) throws ServiceException {
        if (orderRepository.findUpdateStatus(id, status.getStatus(), Status.UNASSIGNED) == 0) {
            throw new ServiceException(TAKE_ORDER_ERROR);
        }
        return new StatusReqResp(Status.SUCCESS);
    }

    @Override
    public List<OrderResponse> listOrder(int page, int limit) {
        return orderRepository.findAll(PageRequest.of(page - 1, limit)).stream().map(e -> new OrderResponse(e)).collect(Collectors.toList());
    }
}
