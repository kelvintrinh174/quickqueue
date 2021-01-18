package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Item;

public interface IItemDao extends JpaRepository<Item, Integer> {

}
