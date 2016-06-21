package com.scopevisio.webservicekit.examples;
zzz
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import com.scopevisio.webservicekit.util.URLPost;
import com.scopevisio.webservicekit.util.URLPost.PostResult;

public static void main(String[] args) {
        try {
                // get args
                String url = "https://appload.scopevisio.com/api/soap/accounting/accounting.TaskDetailsSave";
                String customer = "1000001";
                String user = "test_user@scopevisio.com";
                String pass = "test_password";
                String language = "de_DE";
                String organisation = "Scopevisio Demo AG";

                // prepare SOAP
                MessageFactory mf = MessageFactory.newInstance();
                SOAPMessage request = mf.createMessage();
                SOAPBody body = request.getSOAPBody();
                SOAPElement requestElement = body.addChildElement("req", "ns1", "http://www.scopevisio.com/");

                // authorization tag
                SOAPElement authnElement = requestElement.addChildElement("authn");
                authnElement.addChildElement("customer").setTextContent(customer);
                authnElement.addChildElement("user").setTextContent(user);
                authnElement.addChildElement("pass").setTextContent(pass);
                authnElement.addChildElement("language").setTextContent(language);
                authnElement.addChildElement("organisation").setTextContent(organisation);
                SOAPElement req = (SOAPElement) body.getChildElements().next();

                // args/data tag
                SOAPElement configElement = req.addChildElement("args");
                configElement.addChildElement("id").setTextContent("0");
                configElement.addChildElement("mode").setTextContent("new");
		configElement.addChildElement("data").setTextContent("{\"topic\":\"Testaufgabe\",\"tags\":\"Testaufgabe\",\"description\":\"Diese Testaufgabe wurde über den Service erzeugt\",\"status\":\"0\",\"dueDateTs\":\"1396656000000\"}");

                // post SOAP
                PostResult result = new URLPost().postSoap(url, request);
                String reply = result.getReply();
                System.err.print("responseCode: " + result.getResponseCode() + ", reply: " + reply);
        } catch (Exception e) {
                // handle error
                e.printStackTrace();
        }
}
