package com.tsystems.javaschool.dao.api;

import java.util.List;

public interface AbstractRepository<Entity, ID> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity save(Entity entity);

    Entity update(Entity entity);

}
