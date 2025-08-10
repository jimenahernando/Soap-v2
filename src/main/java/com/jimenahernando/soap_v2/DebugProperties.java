package com.jimenahernando.soap_v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DebugProperties {

    public DebugProperties(@Value("${soap.numberconversion.url:NOT_FOUND}") String url) {
        log.info("🔍 URL SOAP configurada: " + url);
    }
}
