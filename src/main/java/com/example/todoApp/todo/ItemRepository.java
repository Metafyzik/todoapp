package com.example.todoApp.todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface ItemRepository extends ListCrudRepository<Item,Integer> {


}
