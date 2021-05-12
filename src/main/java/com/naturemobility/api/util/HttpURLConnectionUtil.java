package com.naturemobility.api.util;

import com.google.gson.Gson;
import com.naturemobility.api.enumeration.ApiResponseCode;
import com.naturemobility.api.exception.IntApiCustomException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpURLConnectionUtil {

    private final static String POST = "POST";

    public static <T> T post(URL url, Map<String, String> headers, String jsonBodyStr, Type returnType, Class<T> tClass) {

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(POST);

            // Header 세팅
            if (!headers.isEmpty()) {
                headers.forEach((key, value) -> {
                    con.setRequestProperty(key, value);
                });
            }

            // Body 세팅
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(jsonBodyStr.getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            System.out.println("-- API 요청 URL: " + url.getHost());
            System.out.println("-- API 요청 Body: ");
            System.out.println(jsonBodyStr);
            System.out.println("-- 응답코드: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("-- 응답바디: ");
                System.out.println(response.toString());

                T result = new Gson().fromJson(response.toString(), returnType);

                System.out.println("-- 결과: ");
                System.out.println(result);

                return result;

            } else {
                throw new IntApiCustomException(ApiResponseCode.HTTP_CONNECTION_FAIL_1);
            }

        } catch (IOException e) {
            throw new IntApiCustomException(ApiResponseCode.HTTP_CONNECTION_FAIL_2);
        }
    }
}
