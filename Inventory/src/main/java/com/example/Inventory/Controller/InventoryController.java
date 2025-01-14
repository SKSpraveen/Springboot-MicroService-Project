package com.example.Inventory.Controller;

import com.example.Inventory.DTO.InventoryDTO;
import com.example.Inventory.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/save")
    public InventoryDTO save(@RequestBody InventoryDTO inventoryDTO) {
        System.out.println(inventoryDTO);
        return inventoryService.save(inventoryDTO);
    }

    @GetMapping("/getAll")
    public List<InventoryDTO> getAll() {
        return inventoryService.findAll();
    }

    @PutMapping("/update")
    public InventoryDTO update(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return inventoryService.deleteInventory(id);
    }

    @GetMapping("/get/{id}")
    public InventoryDTO getItemById(@PathVariable Integer id) {
        return inventoryService.findInventoryById(id);
    }

}
