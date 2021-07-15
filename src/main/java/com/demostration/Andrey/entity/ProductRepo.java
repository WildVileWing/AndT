package com.demostration.Andrey.entity;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepo extends JpaRepository<Product, Long> {
    String GET_TOP_POPULAR_QUERY = "select o.product from OrderProduct as o group by o.product.id order by sum(o.countProduct) desc";

    @Query(GET_TOP_POPULAR_QUERY)
    List<Product> getTopPopular(@Param("top") Integer top);

    String GET_CATEGORY_PRODUCTS = "from Product as p where p.category.id=:id";

    @Query(GET_CATEGORY_PRODUCTS)
    List<Product> getCategoryProducts(@Param("id") Long id);

    String GET_CLIENT_PRODUCTS = "select o.product from OrderProduct as o where o.clientOrder.client.id =:id";

    @Query(GET_CLIENT_PRODUCTS)
    List<Product> getClientProducts(@Param("id") Long id);

}
