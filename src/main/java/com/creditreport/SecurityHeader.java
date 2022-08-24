package com.creditreport;

import com.creditreport.wsdl.CigWsHeader;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class SecurityHeader implements WebServiceMessageCallback {
    private final CigWsHeader cigWsHeader;

    public SecurityHeader(CigWsHeader cigWsHeader) {
        this.cigWsHeader = cigWsHeader;
    }

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
        SoapHeader soapHeader = ((SoapMessage)message).getSoapHeader();

        try {
            JAXBContext context = JAXBContext.newInstance(CigWsHeader.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(cigWsHeader, soapHeader.getResult());

        } catch (JAXBException e) {
            throw new IOException("error while marshalling authentication.");
        }
    }
}
