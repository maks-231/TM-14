package com.tm14.exchangeservice.repository;


import com.tm14.exchangeservice.entity.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
  Optional<CustomerEntity> findById(Long id);

  List<CustomerEntity> findAll();
}
