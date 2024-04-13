package com.bharath.ws.soap;

import com.bharath.ws.trainings.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CustomerOrderWsImpl implements CustomerOrdersPortType {

    Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    int currenId;

    @Autowired
    aab a;
    @Value("${app.aks}")
    private String ak;
    public CustomerOrderWsImpl(){
        init();
    }
    public void init(){
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(BigInteger.valueOf(1));

        Product product = new Product();
        product.setId("1");
        product.setDescription("IPhone");
        product.setQuantity(BigInteger.valueOf(3));

        order.getProduct().add(product);
        orders.add(order);

        customerOrders.put(BigInteger.valueOf(++currenId),orders);
    }
    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        List<Order> orders = customerOrders .get(customerId);

        GetOrdersResponse response = new GetOrdersResponse();
        response.getOrder().addAll(orders);
        return response;
    }

    @Override
    public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        Order order = request.getOrder();
        List<Order> orders = customerOrders.get(customerId);
        orders.add(order);
        CreateOrdersResponse response = new CreateOrdersResponse();
        response.setResult(true);
        return response;


    }
}
