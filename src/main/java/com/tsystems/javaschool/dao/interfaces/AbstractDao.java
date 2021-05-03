package com.tsystems.javaschool.dao.interfaces;

import java.util.List;

public interface AbstractDao<Entity, ID> {

    Entity findById(ID id);

    List<Entity> findAll();

    Entity save(Entity entity);

    Entity update(Entity entity);

    Entity delete(Entity entity);

    Entity deleteById(ID entityId);

}
