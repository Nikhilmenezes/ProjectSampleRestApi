package com.example.Items.service;

import com.example.Items.entity.Items;
import com.example.Items.exception.ResourceNotFoundException;
import com.example.Items.repository.ItemsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;


    @CircuitBreaker(name="ItemsService",fallbackMethod = "fallback")
    public Items getByID(Long id) {
        log.info("Fetching Items by ID {}", id);
        return itemsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Items not found " + id));
    }

    private Items fallback(Long id,Throwable t) {
        log.error("failed getting Items by ID {}:{}", id, t.getMessage());
        throw new ResourceNotFoundException("Service not available");
    }

    public Items create(Items items) {
        return itemsRepository.save(items);
    }

    public void delete(Long id){
        itemsRepository.deleteById(id);
    }
}
