package com.example.todoApp.todo.Repostiory;

import com.example.todoApp.todo.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ListCrudRepository<Item,Integer> {

}
