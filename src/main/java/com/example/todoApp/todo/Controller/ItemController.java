package com.example.todoApp.todo.Controller;

import com.example.todoApp.todo.Entity.Item;
import com.example.todoApp.todo.Repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class ItemController {

    private final ItemRepository itemRepo;

    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping()
    public String findAll(Model model){

        model.addAttribute("items",itemRepo.findAll());
        return "index";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addItem (@Valid @RequestBody Item newItem, Model model) // TODO what does valid actually do here?
    {
        //TODO add check if properly json contains proper data
        newItem.setCreated(LocalDateTime.now());
        itemRepo.save(newItem);

        model.addAttribute("items",itemRepo.findAll());

        return "fragment :: table-content";
    }

    @PutMapping()
    public String adjustmentItem(@Valid @RequestBody Item itemAdjustement, Model model) {

        Item ItemToAdjust = itemRepo.findById(itemAdjustement.getId()).get();
        ItemToAdjust.setTitle(itemAdjustement.getTitle());
        ItemToAdjust.setTask(itemAdjustement.getTask());
        ItemToAdjust.setAdjusted(LocalDateTime.now());
        itemRepo.save(ItemToAdjust);

        model.addAttribute("items",itemRepo.findAll());
        return "fragment :: table-content";
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {
        //TODO add check if an item with such id is present
        itemRepo.deleteById(id);

        model.addAttribute("items",itemRepo.findAll());
        return "fragment :: table-content";
    }
}
