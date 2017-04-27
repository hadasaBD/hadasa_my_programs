//package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainServlet extends javax.servlet.http.HttpServlet {

    List<String> messages;

    @Override
    public void init() throws ServletException {
        super.init();

        messages = new ArrayList<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String queryString = request.getQueryString();
        if(queryString == null)
            return;
        Map<String, String> qs = new HashMap<>();
        String[] keyValuePairs = queryString.split("&");
        for(String keyValuePair : keyValuePairs){
            String[] keyValue = keyValuePair.split("=");
            if(keyValue.length != 2)
                continue;
            qs.put(keyValue[0], keyValue[1]);
        }
        String action = qs.get("action");
        if(action == null)
            return;
        switch (action){
            case "send":
                String message = qs.get("message");
                if(message == null)
                    return;
                messages.add(message);
                response.getWriter().write("ok");
                break;
            case "check":
                int from = 0;
                String fromString = qs.get("from");
                if(fromString == null)
                    return;
                try{
                    from = Integer.valueOf(fromString);
                }catch (Exception ex){
                    return;
                }
                if(from < 0)
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=from; i < messages.size(); i++) {
                    stringBuilder.append(messages.get(i) + "&");
                }
                if(messages.size() > 0){
                    stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
                }
                //if(messages.size() != 0 && from < messages.size())
                //    stringBuilder.append(messages.get(messages.size()-1));
                response.getWriter().write(stringBuilder.toString());
                break;
        }
    }
}
