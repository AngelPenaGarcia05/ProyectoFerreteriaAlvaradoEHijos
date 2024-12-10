package com.utp.ferreteria.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    private static final String API_KEY = "2f82288534cb372a64132eed";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    @SuppressWarnings("unchecked")
    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        String url = API_URL + fromCurrency;

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Realizar la solicitud a la API y obtener la respuesta como un Map
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // Validar que la respuesta tenga éxito
            if (response != null && "success".equalsIgnoreCase((String) response.get("result"))) {
                // Extraer las tasas de conversión
                Map<String, Double> conversionRates = (Map<String, Double>) response.get("conversion_rates");

                if (conversionRates != null && conversionRates.containsKey(toCurrency)) {
                    Double exchangeRate = conversionRates.get(toCurrency);
                    return amount * exchangeRate;
                }
            }
        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
        }

        return null;
    }
}


