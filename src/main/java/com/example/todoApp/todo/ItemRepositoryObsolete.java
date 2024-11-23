package com.example.todoApp.todo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class ItemRepositoryObsolete {

    List<Item> itemList = new ArrayList<>();
    public List<Item> findAll() {
        return itemList;
    }

    public void addItem(Item item) {

        //add check for correct types and already existing id

        itemList.add(item);
    }

    public void deleteItem(Integer id) {

        if (findById(id).isPresent()) {
            itemList.remove(findById(id).get());
        }
        else {
            System.out.println("There exist no such item with that id.");
        }

    }

    public Optional<Item> findById(Integer id) {
        Optional<Item> item  = itemList.stream().filter(i -> i.getId().equals(id)).findAny();

        return item;
    }
}
