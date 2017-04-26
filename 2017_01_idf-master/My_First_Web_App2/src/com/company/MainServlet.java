package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by eladlavi on 20/04/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {


    int counter = 0;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("in doPost");
        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int actuallyRead;
        StringBuilder stringBuilder = new StringBuilder();
        while((actuallyRead = inputStream.read(buffer)) != -1){
            stringBuilder.append(new String(buffer, 0, actuallyRead));
        }
        String body = stringBuilder.toString();
        System.out.println(body);

        String result = "this is the response " + counter;
        counter++;
        byte[] resultBytes = result.getBytes();
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(resultBytes);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        System.out.println("in doGet");
        //response.getWriter().write("hello!");
        String queryString = request.getQueryString();
        //System.out.println(queryString);
        Map<String, String> qs = new HashMap<>();
        if(queryString != null){
            String[] keyValues = queryString.split("&");
            for(String keyValue : keyValues){
                String[] keyValuePair = keyValue.split("=");
                if(keyValuePair.length != 2)
                    continue;
                qs.put(keyValuePair[0], keyValuePair[1]);
            }
        }
        //System.out.println(qs.get("username"));
        //System.out.println(qs.get("password"));



    }

}
