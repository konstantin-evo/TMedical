package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.AbstractDao;
import com.tsystems.javaschool.service.api.AbstractService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractServiceImpl<Entity, DAO extends AbstractDao<Entity, ID>, DTO, ID> implements AbstractService<Entity, DTO, ID> {

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private DAO dao;

    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private ModelMapper mapper;


    private final Class<DTO> dtoClass;
    private final Class<Entity> entityClass;

    @Override
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        return convertToDTO(dao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return convertToDTO(dao.findAll());
    }

    @Override
    @Transactional
    public DTO save(DTO dto) {
        return convertToDTO(dao.save(convertToEntity(dto)));
    }

    @Override
    @Transactional
    public DTO update(DTO dto) {
        return convertToDTO(dao.update(convertToEntity(dto)));
    }

    @Override
    public DTO convertToDTO(Entity entity) {
        return getMapper().map(entity, dtoClass);
    }

    @Override
    public List<DTO> convertToDTO(List<Entity> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Entity convertToEntity(DTO dto) {
        return getMapper().map(dto, entityClass);
    }

    @Override
    public List<Entity> convertToEntity(List<DTO> entities) {
        return entities.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
