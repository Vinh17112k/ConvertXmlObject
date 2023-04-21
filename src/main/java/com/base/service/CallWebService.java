package com.base.service;

import com.base.model.WebService;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class CallWebService {
    static final String TEMPLATE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" + "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <tem:Add>\n" + "         <tem:intA>#valueA#</tem:intA>\n" + "         <tem:intB>#valueB#</tem:intB>\n" + "      </tem:Add>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";

    public CallWebService() {
    }

    public static String callSoapWS(WebService webService) {
        ConcurrentHashMap<String, String> htbParameters = new ConcurrentHashMap<>();
        StringBuilder response = new StringBuilder();
        String responseString;
        String soapRequest;
        String wsdl = webService.getUrl();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        HttpURLConnection httpURLConn = null;
        try {
            URL url = new URL(wsdl);
            soapRequest = buildWSRequest(webService, htbParameters);
            URLConnection connection = url.openConnection();
            httpURLConn = (HttpURLConnection) connection;
            httpURLConn.setConnectTimeout(webService.getTimeOut().intValue());
            httpURLConn.setReadTimeout(webService.getTimeOut().intValue());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1];
            if (soapRequest != null) {
                b = byteArrayOutputStream.toByteArray();
            }
            httpURLConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpURLConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpURLConn.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpURLConn.setRequestProperty("SOAPAction", "");
            httpURLConn.setRequestMethod("POST");
            httpURLConn.setDoOutput(true);
            httpURLConn.setDoInput(true);
            if (soapRequest != null) {
                out = new OutputStreamWriter(httpURLConn.getOutputStream(), StandardCharsets.UTF_8);
                out.write(soapRequest);
                out.close();
            }
            in = new BufferedReader(new InputStreamReader(httpURLConn.getInputStream(), StandardCharsets.UTF_8));
            while ((responseString = in.readLine()) != null) {
                response.append(responseString);
            }
            response = new StringBuilder(new String(response.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
            log.info(response.toString());
        } catch (Exception ex) {
            log.error("Soap error", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    log.info(ex.getMessage(), ex);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    log.info(ex.getMessage(), ex);
                }
            }
            if (httpURLConn != null) {
                httpURLConn.disconnect();
            }
        }
        return response.toString();
    }

    public static String getSoapTagValue(String tagName, String response) {
        String startTag = "<" + tagName;
        String endTag = "</" + tagName + ">";
        int start = response.indexOf(startTag) + startTag.length();
        int end = response.indexOf(endTag);
        if (start > -1 && end > -1 && end >= start) {
            String errorCode = response.substring(start, end);
            int endOfStart = errorCode.indexOf('>') + 1;
            return errorCode.substring(endOfStart);
        }
        return null;
    }

    public static String buildWSRequest(WebService webService, ConcurrentMap<String, String> htbParameters) {
        String request = webService.getTemplate();
        try {
            for (Map.Entry<String, String> entry : htbParameters.entrySet()) {
                String parameName = entry.getKey();
                String paramValue = entry.getValue();
                if (entry.getValue() != null) paramValue = "";
                if (entry.getValue() != null) request = request.replace(parameName, paramValue);
            }
        } catch (Exception ex) {
            log.info(ex.getMessage(), ex);
        }
        return request;
    }

    public static String callProductSync() {
        String result;
        WebService webService = new WebService();
        String oldTemplate = TEMPLATE;
        String template = replaceTemplate(oldTemplate, 2, 3);
        webService.setUrl("http://www.dneonline.com/calculator.asmx");
        webService.setTimeOut(10000L);
        webService.setTemplate(template);
        result = CallWebService.callSoapWS(webService);
        return result;
    }

    public static String replaceTemplate(String template, int a, int b) {
        template = template.replace("#valueA#", String.valueOf(a));
        template = template.replace("#valueB#", String.valueOf(b));
        return template;
    }
}
