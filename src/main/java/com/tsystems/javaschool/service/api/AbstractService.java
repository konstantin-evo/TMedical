package com.tsystems.javaschool.service.api;

import java.util.List;

public interface AbstractService<Entity, DTO, ID> {
    DTO findById(ID id);

    List<DTO> findAll();

    DTO save(DTO dto);

    DTO update(DTO dto);

    DTO delete(DTO dto);

    DTO deleteById(ID entityId);

    DTO convertToDTO(Entity entity);

    List<DTO> convertToDTO(List<Entity> entities);

    Entity convertToEntity(DTO dto);

    List<Entity> convertToEntity(List<DTO> dtoList);
}