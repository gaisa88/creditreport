package com.creditreport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class GetAvailableReportsForIdConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.creditreport.wsdl");
        return marshaller;
    }
    @Bean
    public GetAvailableReportsForIdClient getAvailableReportsForIdClient(Jaxb2Marshaller marshaller) {
        GetAvailableReportsForIdClient client = new GetAvailableReportsForIdClient();
        client.setDefaultUri("http://test2.1cb.kz/FCBServices/Service");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

