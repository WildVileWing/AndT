package com.demostration.Andrey.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderProduct", path = "orderProduct")
public interface OrderProductRepo extends  JpaRepository<OrderProduct, Long> {
    String GET_ORDER_PRODUCT_BY_CLIENT_ORDER_AND_PRODUCT = "select op from OrderProduct as op where op.clientOrder = clientOrder and op.product = product";

    @Query(GET_ORDER_PRODUCT_BY_CLIENT_ORDER_AND_PRODUCT)
    OrderProduct getOrderProductByClientOrderAndProduct(@Param("clientOrder")ClientOrder clientOrder, @Param("product")Product product);

    String GET_ORDER_PRODUCTS_BY_CLIENT_ORDER= "select op from OrderProduct as op where op.clientOrder.id = clientOrder.id";

    @Query(GET_ORDER_PRODUCTS_BY_CLIENT_ORDER)
    List<OrderProduct> getOrderProductsByClientOrder(@Param("clientOrder")ClientOrder clientOrder);
}
