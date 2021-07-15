package com.demostration.Andrey.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orderProduct", path = "orderProduct")
public interface OrderProductRepo extends  JpaRepository<OrderProduct, Long> {
}
