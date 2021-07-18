package com.demostration.Andrey.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepo extends JpaRepository<Category, Long> {

    String GET_CATEGORY_BY_ID = "from Category as c where c.id=:id";

    @Query(GET_CATEGORY_BY_ID)
    Category getCategoryById(@Param("id") Long id);
}

