package com.example.Items.controller;

import com.example.Items.entity.Items;
import com.example.Items.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemsController {

    @Autowired
    private ItemsService itemsService;



    @GetMapping("/{id}")
    public ResponseEntity<Items> getById(@PathVariable Long id){
        return ResponseEntity.ok(itemsService.getByID(id));
    }


    @PostMapping("/api/items")
    public ResponseEntity<Items> create(@RequestBody Items items){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemsService.create(items));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Items> delete(@PathVariable Long id){
        itemsService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
