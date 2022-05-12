package com.revature.comfybake.Bake;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BakeRepository extends CrudRepository<Bake, String> {
    @Override
    List<Bake> findAll();
}
