package com.tm14.exchangeservice.controller;


import com.tm14.exchangeservice.dto.CustomerDto;
import com.tm14.exchangeservice.entity.CustomerEntity;
import com.tm14.exchangeservice.repository.CustomerRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.annotation.Resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customer-details")
public class CustomerDetailsController {
  @Value("${spring.application.name}")
  private String appName;

  @Value("${server.port}")
  private String port;

  @Resource
  Environment environment;
  private static final Logger LOG = LoggerFactory.getLogger(CustomerDetailsController.class);

  @Resource
  CustomerRepository customerRepository;

  @GetMapping("/customers")
  public List<CustomerDto> getAllCustomers() {
    ModelMapper mapper = new ModelMapper();

    return customerRepository.findAll()
        .stream()
        .map(entity -> mapper.map(entity, CustomerDto.class))
        .map(customerDto -> {
          customerDto.setPort(port);
          customerDto.setAppName(appName);
          return customerDto;
        })
        .toList();
  }

  @GetMapping("/customers/{id}")
  public EntityModel<CustomerDto> getCustomerById(@PathVariable Long id) throws Exception {
    ModelMapper mapper = new ModelMapper();
    Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

    if (!customerEntityOptional.isPresent()) {
      throw new Exception("User not found");
    }
    CustomerDto customerDto = mapper.map(customerEntityOptional.get(), CustomerDto.class);

    customerDto.setPort(port);
    customerDto.setAppName(appName);
    EntityModel entityModel = EntityModel.of(customerDto);
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllCustomers());
    entityModel.add(link.withRel("customers"));
    return entityModel;
  }

  @PutMapping("/update-customer/{id}")
  public EntityModel<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) throws Exception {
    Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

    if (!customerEntityOptional.isPresent()) {
      throw new Exception("User not found");
    }

    CustomerEntity customerEntity = customerEntityOptional.get();

    if (!Objects.isNull(customerDto.getBirthDate())) {
      customerEntity.setBirthDate(customerDto.getBirthDate());
    }
    if (!Objects.isNull(customerDto.getFirstName())) {
      customerEntity.setFirstName(customerDto.getFirstName());
    }
    if (!Objects.isNull(customerDto.getLastName())) {
      customerEntity.setLastName(customerDto.getLastName());
    }

    ModelMapper mapper = new ModelMapper();
    CustomerDto customer = mapper.map(customerRepository.save(customerEntity), CustomerDto.class);

    customer.setPort(port);
    customer.setAppName(appName);

    EntityModel entityModel = EntityModel.of(customer);
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllCustomers());
    entityModel.add(link.withRel("customers"));
    return entityModel;
  }
}
