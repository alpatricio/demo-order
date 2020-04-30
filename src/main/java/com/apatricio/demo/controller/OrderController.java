package com.apatricio.demo.controller;

import com.apatricio.demo.exception.ServiceException;
import com.apatricio.demo.model.dto.OrderRequest;
import com.apatricio.demo.model.dto.OrderResponse;
import com.apatricio.demo.model.dto.StatusReqResp;
import com.apatricio.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/orders")
    @ResponseBody
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.OK);
    }

    @PatchMapping(path = "/orders/{id}")
    @ResponseBody
    public ResponseEntity<StatusReqResp> createOrder(@RequestBody StatusReqResp status, @PathVariable("id") Integer id) throws ServiceException {
        return new ResponseEntity<>(orderService.takeOrder(id, status), HttpStatus.OK);
    }

    @GetMapping(path = "/orders", params = {"page", "limit"})
    @ResponseBody
    public ResponseEntity<List<OrderResponse>>
        orderList(@RequestParam("page") @Valid @Min(1) int page, @RequestParam("limit") @Valid @Min(1) int limit) {
            return new ResponseEntity<>(orderService.listOrder(page, limit), HttpStatus.OK);
    }
}
