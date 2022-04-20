package com.robinwyss.currencyconverter.service;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.robinwyss.currencyconverter.model.ConversionResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConversionService {

    public ConversionResult Convert(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();
        ConversionData data = restTemplate.getForObject("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+from+"/"+ to+".json", ConversionData.class);
        return new ConversionResult(from, to, data.date, data.result.get(to));
    }

    public static class ConversionData {

        public String date;
        public Map<String, Double> result = new HashMap<String, Double>();

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @JsonAnySetter
        void setResult(String key, double value) {
            result.put(key, value);
        }
    }
}
