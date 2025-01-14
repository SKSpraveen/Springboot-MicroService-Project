package com.example.Inventory.Service;

import com.example.Inventory.DTO.InventoryDTO;
import com.example.Inventory.Entity.Inventory;
import com.example.Inventory.Repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public InventoryDTO save(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public List<InventoryDTO> findAll() {
        List<Inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {}.getType());
    }

    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteInventory(Integer id){
        inventoryRepo.deleteById(id);
        return "Inventory deleted";
    }

    public InventoryDTO findInventoryById(Integer id) {
        Inventory inventory = inventoryRepo.getReferenceById(id);
        return modelMapper.map(inventory, InventoryDTO.class);
    }
}
