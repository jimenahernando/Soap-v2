package com.jimenahernando.soap_v2;

import com.dataaccess.webservicesserver.NumberConversion;
import com.dataaccess.webservicesserver.NumberConversionSoapType;
import jakarta.xml.ws.BindingProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.X509TrustManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.cert.X509Certificate;

@Service
@Slf4j
public class NumberConversionService {

    private final NumberConversionSoapType soapPort;

    public NumberConversionService(@Value("${soap.numberconversion.url}") String endpointUrl) {
        NumberConversion service = new NumberConversion();
        NumberConversionSoapType port = service.getNumberConversionSoap();

        try {
            // Configura el endpoint dinámico
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            // Configuración de SSL inseguro
            Client client = ClientProxy.getClient(port);
            HTTPConduit conduit = (HTTPConduit) client.getConduit();

            TLSClientParameters tlsParams = new TLSClientParameters();
            tlsParams.setDisableCNCheck(true);
            tlsParams.setTrustManagers(new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }});

            conduit.setTlsClientParameters(tlsParams);
            log.info("✅ Cliente SOAP inicializado correctamente con endpoint: {}", endpointUrl);
        } catch (Exception e) {
            log.info("❌ Error inicializando NumberConversionService: {}", e.getMessage());
        }

        this.soapPort = port;
    }

    public String convertirADolares(double numero) {
        return soapPort.numberToDollars(BigDecimal.valueOf(numero));
    }

    public String convertirAPalabras(int numero) {
        return soapPort.numberToWords(BigInteger.valueOf(numero));
    }
}
