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
import ru.fozydev.customerservice.dto.customer.CustomerRequestDTO;
import ru.fozydev.customerservice.dto.customer.CustomerResponseDTO;
import ru.fozydev.customerservice.facade.CustomerFacade;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerFacade customerFacade;

    @Autowired
    public CustomerRestController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerFacade.getCustomersDTO();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable("id") UUID customerId) {
        return customerFacade.getCustomerDTO(customerId);
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO dto) {
        return customerFacade.createCustomer(dto);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable("id") UUID id, @RequestBody CustomerRequestDTO dto) {
        return customerFacade.updateCustomer(id, dto);
    }

    @GetMapping("/search")
    public Page<CustomerResponseDTO> searchCustomers(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) UUID countryId,
            @PageableDefault(size = 25, sort = "country.name") Pageable pageable) {
        return customerFacade.searchCustomers(fullName, countryId, pageable);
    }
}