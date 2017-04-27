package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hackeru on 4/23/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {
    String message = null;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String queryString = request.getQueryString();
        Map<String, String> qs = new HashMap<>();
        if (queryString != null) {
            String[] keyValues = queryString.split("&");
            for (String keyValue : keyValues) {
                String[] keyValuesPair = keyValue.split("=");
                if (keyValuesPair.length != 2)
                    continue;
                qs.put(keyValuesPair[0], keyValuesPair[1]);
            }

        }

        String action=qs.get("action");
        if(action.equals("send"))
            message = qs.get("message"+ '^'+'@'+'^');
        else
            response.getWriter().write("The message is:"+message);
    }
}
