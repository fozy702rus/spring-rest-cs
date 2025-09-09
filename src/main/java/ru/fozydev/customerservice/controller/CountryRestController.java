package ru.fozydev.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fozydev.customerservice.model.Country;
import ru.fozydev.customerservice.service.country.CountryService;

@RestController
@RequestMapping("/country")
public class CountryRestController {

    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

//    @PostMapping
//    public Country create(@RequestBody CountryCreateDTO countryCreateDTO) {
//
//        return countryService.createCountry(countryCreateDTO);
//    }
}
