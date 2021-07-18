package com.demostration.Andrey.entity;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clientOrder", path = "clientOrder")
public interface ClientOrderRepo extends JpaRepository<ClientOrder, Long> {
    String GET_CLIENT_ORDER_BY_NAME = "from ClientOrder as co where co.client.fullName=:name";

    @Query(GET_CLIENT_ORDER_BY_NAME)
    List<ClientOrder> getClientOrderByName(@Param("name") String name );


    String GET_CLIENT_ORDER_BY_CLIENT = "from ClientOrder as co where co.client.externalId=:externalId";

    @Query(GET_CLIENT_ORDER_BY_CLIENT)
    ClientOrder getClientOrderByClient(@Param("externalId") Long externalId);

}
