package com.jimenahernando.soap_v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NumberConversionController {

    private final NumberConversionService conversionService;

    @GetMapping("/convertir/dolares/{numero}")
    public String convertirADolares(@PathVariable double numero) {
        return conversionService.convertirADolares(numero);
    }

    @GetMapping("/convertir/palabras/{numero}")
    public String convertirAPalabras(@PathVariable int numero) {
        return conversionService.convertirAPalabras(numero);
    }
}
