package com.example.todoApp.todo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public String adjustItem(@RequestBody String jsonString, Model model) {

        //TODO add check if properly json contains proper data
        //TODO should this "logic" by in a controller?
        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(jsonString);
        JsonObject details = rootNode.getAsJsonObject();

        Integer id = details.get("id").getAsInt();
        String title = details.get("title").getAsString();
        String task = details.get("task").getAsString();


        if(itemRepo.findById(id).isPresent()) {
            Item itemToAdjust = itemRepo.findById(id).get();

            itemToAdjust.setAdjusted(LocalDateTime.now());
            itemToAdjust.setTask(task);
            itemToAdjust.setTitle(title);

            itemRepo.save(itemToAdjust);
        } else {
            System.out.println("Item with id: " + id + "not found in the system.");
            //TODO throws adequate response
        }
        //itemRepo.

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
