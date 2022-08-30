package com.creditreport;

import com.creditreport.wsdl.GetAvailableReportsForIdResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

@SpringBootApplication
public class CreditreportApplication {

    public static void main(String[] args) throws ParserConfigurationException, SOAPException {
        ApplicationContext ctx = SpringApplication.run(CreditreportApplication.class, args);

        GetAvailableReportsForIdClient getAvailableReportsForIdClient = ctx.getBean(GetAvailableReportsForIdClient.class);

        String iin = "881203350381";
        if (args.length > 0) {
            iin = args[0];
        }
        GetAvailableReportsForIdResponse.GetAvailableReportsForIdResult response = getAvailableReportsForIdClient.getGetAvailableReportsForIdResult(iin);
        //getAvailableReportsForIdClient.printResponse(response);
    }
}

