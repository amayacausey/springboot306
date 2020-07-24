package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DirectorRepository extends CrudRepository<Director,Long> {
ArrayList<Director> findAllByIdGreaterThanOrderByName(long indexNum);
}
