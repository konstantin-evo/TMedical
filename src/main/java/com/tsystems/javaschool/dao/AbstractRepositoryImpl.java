package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractRepositoryImpl<Entity, ID> implements AbstractRepository<Entity, ID> {

   private final Class<Entity> entityClass;

   private SessionFactory session;

   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.session = sessionFactory;
   }


   public AbstractRepositoryImpl(Class<Entity> clazz) {
      this.entityClass = clazz;
   }

   @Override
   public Entity findById(ID id) {
      return session.getCurrentSession().find(entityClass, id);
   }

   @Override
   public List<Entity> findAll() {
      return session.getCurrentSession()
              .createQuery("from " + entityClass.getName(), entityClass)
              .getResultList();
   }

   @Override
   public Entity save(Entity entity) {
      session.getCurrentSession().persist(entity);
      session.getCurrentSession().flush();
      return entity;
   }

   @Override
   public Entity update(Entity entity){
      session.getCurrentSession()
              .merge(entity);
      return entity;
   }

}