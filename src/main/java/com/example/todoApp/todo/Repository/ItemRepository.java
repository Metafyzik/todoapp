package com.example.todoApp.todo.Repository;

import com.example.todoApp.todo.Entity.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ListCrudRepository<Item,Integer> {

}
