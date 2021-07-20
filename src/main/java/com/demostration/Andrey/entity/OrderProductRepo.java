package com.demostration.Andrey.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderProduct", path = "orderProduct")
public interface OrderProductRepo extends  JpaRepository<OrderProduct, Long> {
    String GET_ORDER_PRODUCT_BY_CLIENT_ORDER_AND_PRODUCT = "from OrderProduct as op where op.clientOrder.id = clientOrderId and op.product.id = productId";

    @Query(GET_ORDER_PRODUCT_BY_CLIENT_ORDER_AND_PRODUCT)
    OrderProduct getOrderProductByClientOrderAndProduct(@Param("clientOrderId")Long clientOrderId, @Param("productId")Long productId);

    String GET_ORDER_PRODUCTS_BY_CLIENT_ORDER= "select o from OrderProduct as o where o.clientOrder = ?1";

    @Query(GET_ORDER_PRODUCTS_BY_CLIENT_ORDER)
    List<OrderProduct> getOrderProductsByClientOrder(ClientOrder clientOrder);
}
