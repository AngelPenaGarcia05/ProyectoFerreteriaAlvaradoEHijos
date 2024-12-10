package com.utp.ferreteria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.utp.ferreteria.services.CurrencyService;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public Double convertCurrency(
            @RequestParam Double amount,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency) {

        return currencyService.convertCurrency(amount, fromCurrency, toCurrency);
    }
}

