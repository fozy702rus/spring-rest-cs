package ru.fozydev.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fozydev.customerservice.dto.operations.CustomerCreateRequest;
import ru.fozydev.customerservice.dto.operations.CustomerResponseDTO;
import ru.fozydev.customerservice.service.customer.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search")
    public Page<CustomerResponseDTO> searchCustomers(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) UUID countryId,
            @PageableDefault(size = 25, sort = "country.name") Pageable pageable) {
        return customerService.searchCustomers(fullName, countryId, pageable);
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody CustomerCreateRequest dto) {
        return customerService.createCustomer(dto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable UUID id, @RequestBody CustomerCreateRequest dto) {
        return customerService.updateCustomer(id, dto);
    }
}