package com.example.Orders.Service;

import com.example.Inventory.DTO.InventoryDTO;
import com.example.Orders.Common.ErrorResponse;
import com.example.Orders.Common.OrderResponse;
import com.example.Orders.Common.SuccessOrderResponse;
import com.example.Orders.DTO.OrderDTO;
import com.example.Orders.Entity.OrderEntity;
import com.example.Orders.Repo.OrderRepo;
import com.example.Products.DTO.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final WebClient inventoryClient;
    private final WebClient productClient;



    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryClient, WebClient productClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryClient = inventoryClient;
        this.productClient = productClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }


    public OrderResponse save(OrderDTO orderDTO) {
        System.out.println("Received OrderDTO: " + orderDTO);
        Integer itemId = orderDTO.getItemId();
        System.out.println("Received OrderDTO: " + orderDTO);
        try {

            InventoryDTO inventoryResponse = inventoryClient.get()

                    .uri(uriBuilder -> uriBuilder.path("/get/{id}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();
            System.out.println("Received OrderDTO7: " + orderDTO);
            ProductDTO productResponse = productClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/get/{id}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();


            assert productResponse != null;


            if (inventoryResponse.getQuantity() > 0){
                if (productResponse.getForSale() == 1){
                    orderRepo.save(modelMapper.map(orderDTO, OrderEntity.class));
                    System.out.println("Mapped OrderEntity: " + orderDTO);
                }
                else {
                    return new ErrorResponse("This item is not for sale");
                }
                System.out.println("Received OrderDTO6: " + orderDTO);
                return new SuccessOrderResponse(orderDTO);
            }
            else {
                return new ErrorResponse("This item is not available");
            }
        }
        catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorResponse("Item not found");
            }
        }
        return null;

    }

    public List<OrderDTO> findAll() {
        List<OrderEntity> orderEntities = orderRepo.findAll();
        return modelMapper.map(orderEntities, new TypeToken<List<OrderDTO>>() {}.getType());
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, OrderEntity.class));
        return orderDTO;
    }

    public String deleteOrder(Integer id) {
        orderRepo.deleteById(id);
        return "Order deleted";
    }

    public OrderDTO findOrderById(Integer id) {
        OrderEntity orderEntity = orderRepo.findById(id).get();
        return modelMapper.map(orderEntity, OrderDTO.class);
    }
}
