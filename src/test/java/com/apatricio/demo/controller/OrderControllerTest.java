package com.apatricio.demo.controller;

import com.apatricio.demo.model.Status;
import com.apatricio.demo.model.dto.OrderRequest;
import com.apatricio.demo.model.dto.OrderResponse;
import com.apatricio.demo.model.dto.StatusReqResp;
import com.apatricio.demo.model.mongodb.OrderEntity;
import com.apatricio.demo.service.OrderService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void placeOrderShouldCallServiceReturn200() throws Exception {
        OrderRequest req = new OrderRequest();
        req.setOrigin(new String[]{"14.625607", "121.058315"});
        req.setDestination(new String[]{"14.627728", "121.057306"});
        OrderEntity entity = new OrderEntity(req, 1);
        when(service.placeOrder(any())).thenReturn(new OrderResponse(entity));

        MockHttpServletResponse result = mockMvc.perform(post("/orders")
                .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(req)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(result.getStatus(), 200);
        verify(service).placeOrder(any());
    }

    @Test
    void placeOrderShouldCallServiceReturn400() throws Exception {
        OrderRequest req = new OrderRequest();
        req.setOrigin(new String[]{"14.625607", "121.058315"});
        req.setDestination(new String[]{"14.627728"});

        MockHttpServletResponse result = mockMvc.perform(post("/orders")
                .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(req)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        assertEquals(result.getStatus(), 400);
        verifyNoInteractions(service);
    }

    @Test
    void takeOrderShouldReturn200() throws Exception {
        StatusReqResp status = new StatusReqResp();
        status.setStatus(Status.TAKEN);
        when(service.takeOrder("1",status)).thenReturn(status);

        MockHttpServletResponse result = mockMvc.perform(patch("/orders/1")
                .contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(status)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(result.getStatus(), 200);
        verify(service).takeOrder(any(),any());
    }

    @Test
    void listOrderShouldCallServiceReturn200() throws Exception {
        when(service.listOrder(1,1)).thenReturn(Arrays.asList(new OrderResponse(Status.SUCCESS)));

        MockHttpServletResponse result = mockMvc.perform(get("/orders")
                .param("page","1").param("limit","1")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertEquals(result.getStatus(), 200);
        verify(service).listOrder(1,1);
    }

    @Test
    void listOrderShouldCallServiceReturn400() throws Exception {
        when(service.listOrder(1,1)).thenReturn(Arrays.asList(new OrderResponse(Status.TAKEN)));

        MockHttpServletResponse result = mockMvc.perform(get("/orders")
                .param("limit","-1")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

        assertEquals(result.getStatus(), 400);
        verifyNoInteractions(service);
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
