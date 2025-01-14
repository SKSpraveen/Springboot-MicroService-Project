package com.example.Orders.Controller;

import com.example.Orders.Common.OrderResponse;
import com.example.Orders.DTO.OrderDTO;
import com.example.Orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public OrderResponse save(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAll() {
        return orderService.findAll();
    }

    @PutMapping("/update")
    public OrderDTO update(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/get/{id}")
    public OrderDTO get(@PathVariable Integer id) {
        return orderService.findOrderById(id);
    }
}
