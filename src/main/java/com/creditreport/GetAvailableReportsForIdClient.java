package com.creditreport;

import com.creditreport.wsdl.CigWsHeader;
import com.creditreport.wsdl.GetAvailableReportsForId;
import com.creditreport.wsdl.GetAvailableReportsForIdResponse;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.support.MarshallingUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringWriter;

public class GetAvailableReportsForIdClient extends WebServiceGatewaySupport {
    public GetAvailableReportsForIdResponse.GetAvailableReportsForIdResult getGetAvailableReportsForIdResult(String iin) throws ParserConfigurationException {
        CigWsHeader myheader = new CigWsHeader();
        myheader.setUserName("7752217879");
        myheader.setPassword("7752217879");
        myheader.setCulture("ru-RU");
        myheader.setSecurityToken("0");
        myheader.setVersion("1");
        myheader.setUserId(0);


        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element keyValue = document.createElement("keyValue");
        document.appendChild(keyValue);
        Element setIdTypeExportCode = document.createElement("idTypeExportCode");
        setIdTypeExportCode.appendChild(document.createTextNode("Entity.Identification.Type.Iin"));
        keyValue.appendChild(setIdTypeExportCode);

        GetAvailableReportsForId.XmlParams xmlParams = new GetAvailableReportsForId.XmlParams();
        xmlParams.getContent().add(document.getDocumentElement());
        GetAvailableReportsForId request1 = new GetAvailableReportsForId();
        request1.setIdNumber(iin);
        request1.setXmlParams(xmlParams);

        // разобраться с передаваемым объектом

        System.out.println();
        System.out.println("Requesting scoring for iin: " + iin);
//        GetAvailableReportsForIdClient.jaxbObjectToXML(request1);
        //JAXBElement<GetAvailableReportsForIdResponse> obj = (JAXBElement<GetAvailableReportsForIdResponse>)
                getWebServiceTemplate().sendAndReceive("http://test2.1cb.kz/FCBServices/Service",
                new WebServiceMessageCallback() {
                    public void doWithMessage(WebServiceMessage request) throws IOException, TransformerException {
                        SoapBody soapBody = ((SoapMessage) request1).getSoapBody();
                        SoapHeader soapHeader = ((SoapMessage) myheader).getSoapHeader();

                        try {
                            JAXBContext context = JAXBContext.newInstance(GetAvailableReportsForId.class);

                            Marshaller marshaller = context.createMarshaller();
                            marshaller.marshal(request1, soapBody.getPayloadResult());

                        } catch (JAXBException e) {
                            throw new IOException("error while marshalling body.");
                        }

                        try {
                            JAXBContext context1 = JAXBContext.newInstance(CigWsHeader.class);

                            Marshaller marshaller = context1.createMarshaller();
                            marshaller.marshal(myheader, soapHeader.getResult());

                        } catch (JAXBException e) {
                            throw new IOException("error while marshalling authentication.");
                        }
                    }
                },
                new WebServiceMessageExtractor<Object>() {
                    public Object extractData(WebServiceMessage response) throws IOException {
                        try {
                            JAXBContext context = JAXBContext.newInstance(GetAvailableReportsForIdResponse.class);
                            org.springframework.oxm.Unmarshaller unmarshaller = (org.springframework.oxm.Unmarshaller) context.createUnmarshaller();
                            MarshallingUtils.unmarshal(unmarshaller, response);
                        } catch (JAXBException e) {
                            throw new IOException("No unmarshaller registered. Check configuration of WebServiceTemplate.");
                        }
                        return response;
                    }
                });

        GetAvailableReportsForIdResponse test = obj.getValue();
        GetAvailableReportsForIdResponse.GetAvailableReportsForIdResult test2 = test.getGetAvailableReportsForIdResult();
        return test2;
    }

    //public void printResponse(GetAvailableReportsForIdResponse response) {
//    GetAvailableReportsForIdResponse.GetAvailableReportsForIdResult Return = response.getGetAvailableReportsForIdResult();
//
//    if (Return.getContent() == "0") {   // разобраться с возвращаемым объектом
//        System.out.println(Return.getErrorCode());
//    } else {
//        System.out.println("Error request " + Return.getErrorCode());
//    }
//}
//    private static String jaxbObjectToXML(GetAvailableReportsForId request1) {
//        String xmlString = "";
//        try {
//            JAXBContext context = JAXBContext.newInstance(GetAvailableReportsForId.class);
//            Marshaller m = context.createMarshaller();
//
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
//
//            StringWriter sw = new StringWriter();
//            m.marshal(request1, sw);
//            xmlString = sw.toString();
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        return xmlString;
//    }
}

