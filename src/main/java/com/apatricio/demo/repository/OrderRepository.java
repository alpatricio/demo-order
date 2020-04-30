package com.apatricio.demo.repository;

import com.apatricio.demo.model.OrderEntity;
import com.apatricio.demo.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Integer> {

    OrderEntity save(OrderEntity order);

    @Transactional(readOnly = true)
    Page<OrderEntity> findAll(Pageable page);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update OrderEntity x set x.status =:taken where x.id =:id and x.status =:unassigned")
    Integer findUpdateStatus(@Param("id") Integer id, @Param("taken") Status taken, @Param("unassigned") Status unassigned);
}
