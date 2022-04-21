package com.robinwyss.currencyconverter.controller;

import com.robinwyss.currencyconverter.model.ConversionInput;
import com.robinwyss.currencyconverter.model.ConversionResult;
import com.robinwyss.currencyconverter.service.ConversionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyConversionController {

    private static final Logger logger = LogManager.getLogger(CurrencyConversionController.class);

    private ConversionService conversionService;

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public String getForm(Model model) {
        logger.info("loading form");
        model.addAttribute("input", new ConversionInput());
        return "convert";
    }


    @PostMapping("/convert")
    public String convert(@ModelAttribute ConversionInput conversionInput, Model model){
        logger.info("requesting conversion rate for {} to {} ", conversionInput.getFrom(), conversionInput.getTo());
        ConversionResult result = conversionService.Convert(conversionInput.getFrom(), conversionInput.getTo());
        model.addAttribute("input", conversionInput);
        model.addAttribute("result", result);
        return "convert";
    }
}
