package com.creditreport;

import com.creditreport.wsdl.CigWsHeader;
import com.creditreport.wsdl.GetAvailableReportsForId;
import com.creditreport.wsdl.GetAvailableReportsForIdResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class GetAvailableReportsForIdClient extends WebServiceGatewaySupport {


    public static GetAvailableReportsForIdResponse getGetAvailableReportsForIdResult(String iin) {
        CigWsHeader header = new CigWsHeader();
        header.setUserName("7752217879");
        header.setPassword("7752217879");
        header.setCulture("ru-RU");
        header.setSecurityToken("0");
        header.setVersion("1");
        header.setUserId(0);
        GetAvailableReportsForId request = new GetAvailableReportsForId();
        request.setIdNumber(iin);

        // разобраться с передаваемым объектом

        System.out.println();
        System.out.println("Requesting scoring for iin: " + iin);

        JAXBElement<GetAvailableReportsForIdResponse> obj = (JAXBElement<GetAvailableReportsForIdResponse>) getWebServiceTemplate().marshalSendAndReceive("http://test2.1cb.kz/FCBServices/Service", request, new SecurityHeader(header));

        GetAvailableReportsForIdResponse response = obj.getValue();
        return response;
    }

    public void printResponse(GetAvailableReportsForIdResponse response) {
        GetAvailableReportsForIdResponse.GetAvailableReportsForIdResult Return = response.getGetAvailableReportsForIdResult();

        if (Return.getContent() == "0") {   // разобраться с возвращаемым объектом
            System.out.println(Return.getErrorCode());
        } else {
            System.out.println("Error request " + Return.getErrorCode());
        }
    }
}
