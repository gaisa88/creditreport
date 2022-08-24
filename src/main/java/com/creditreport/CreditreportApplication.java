package com.creditreport;

import com.creditreport.wsdl.GetAvailableReportsForIdResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CreditreportApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CreditreportApplication.class, args);

        GetAvailableReportsForIdClient getAvailableReportsForIdClient = ctx.getBean(GetAvailableReportsForIdClient.class);

        String iin = "881203350381";
        if (args.length > 0) {
            iin = args[0];
        }
        GetAvailableReportsForIdResponse response = GetAvailableReportsForIdClient.getGetAvailableReportsForIdResult(iin);
        getAvailableReportsForIdClient.printResponse(response);
    }
}

