package com.robinwyss.currencyconverter.controller;

import com.robinwyss.currencyconverter.model.ConversionInput;
import com.robinwyss.currencyconverter.model.ConversionResult;
import com.robinwyss.currencyconverter.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyConversionController {

    private ConversionService conversionService;

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("input", new ConversionInput());
        return "convert";
    }


    @PostMapping("/convert")
    public String convert(@ModelAttribute ConversionInput conversionInput, Model model){
        ConversionResult result = conversionService.Convert(conversionInput.getFrom(), conversionInput.getTo());
        model.addAttribute("input", conversionInput);
        model.addAttribute("result", result);
        return "convert";
    }
}
