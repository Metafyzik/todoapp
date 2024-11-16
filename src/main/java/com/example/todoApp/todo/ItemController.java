package com.example.todoApp.todo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ItemController {

    private final ItemRepository itemRepo;


    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }


    @GetMapping()
    public String findAll(Model model){
        List<Item> items = itemRepo.findAll();

        model.addAttribute("items",items);
        return "index";
    }

    /*
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void addItem (@RequestBody Item item) {
        //TODO add check if properly json contains proper data
        itemRepo.addItem(item);
    }
    */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void addItem (@RequestBody Item newItem)
    {
        newItem.setCreated(LocalDateTime.now());
        newItem.setId(Item.numberOfObjects);

        itemRepo.addItem(newItem);
        //TODO add id automatically
        //process json
        /*
        Gson gson = new Gson();
        gson.fromJson(jsonObject,String.class);

        item.created(LocalDateTime.now());
        System.out.println(item);
        */

        //jsonObject.
        //System.out.println("Email: " + task);

        //itemRepo.addItem(item);
    }

    //TODO add putmethod
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void adjustItem(@RequestBody String jsonString) {

        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(jsonString);
        JsonObject details = rootNode.getAsJsonObject();

        Integer id = details.get("id").getAsInt();
        String title = details.get("title").getAsString();
        String task = details.get("task").getAsString();

        System.out.println(id + title + task);

        //find item to ajdust
        //Optional<Item> itemToAdjust = itemRepo.findById(id);

        if( itemRepo.findById(id).isPresent()) {
            Item itemToAdjust = itemRepo.findById(id).get();

            itemToAdjust.setAdjusted(LocalDateTime.now());
            itemToAdjust.setTask(task);
            itemToAdjust.setTitle(title);
            //itemToAdjust.set
        } else {
            System.out.println("Item with id: " + id + "not found in the system.");
            //TODO throws adequate response
        }



       // itemRepo.findById()
    }











    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        //TODO add check if an item with such id is present
        itemRepo.deleteItem(id);
    }



    /*
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/")
    public String deleteItem(Model model) {
        //TODO add check if an item with such id is present
        //itemRepo.deleteItem(id);
        System.out.println("DELETE METHOD CALLED");
        return "index"; // :: test-delete";
    }
    */




}
