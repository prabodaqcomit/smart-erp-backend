package lk.quantacom.smarterpbackend.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {


    public static CommonResponse  request(String type, String url_suffix, String req_body) {

        System.out.println(req_body);
        String getRes = null;
        CommonResponse commonResponse = new CommonResponse();

        try {
            int i = 0;
            HttpClient client = HttpClientBuilder.create().build();

            if (type.equals("post")) {
                HttpPost request = new HttpPost("http://localhost:8080/"+url_suffix);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-Type", "application/json");
                //request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                if (req_body != null) {
                    StringEntity body = new StringEntity(req_body);
                    request.setEntity(body);
                }

                HttpResponse response = client.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                getRes = "";
                while ((inputLine = in.readLine()) != null) {
                    getRes = getRes + inputLine + "\n";
                }
                in.close();
                i = response.getStatusLine().getStatusCode();


            } else if (type.equals("get")) {

                HttpGet request = new HttpGet("http://localhost:8080/"+url_suffix);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-Type", "application/json");
                //request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

                HttpResponse response = client.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                getRes = "";
                while ((inputLine = in.readLine()) != null) {
                    getRes = getRes + inputLine + "\n";
                }
                in.close();
                i = response.getStatusLine().getStatusCode();

            } else if (type.equals("put")) {

                HttpPut request = new HttpPut("http://localhost:8080/"+url_suffix);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-Type", "application/json");
                //request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                if (req_body != null) {
                    StringEntity body = new StringEntity(req_body);
                    request.setEntity(body);
                }

                HttpResponse response = client.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                getRes = "";
                while ((inputLine = in.readLine()) != null) {
                    getRes = getRes + inputLine + "\n";
                }
                in.close();
                i = response.getStatusLine().getStatusCode();


            } else if (type.equals("delete")) {
                HttpDelete request = new HttpDelete("http://localhost:8080/"+url_suffix);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-Type", "application/json");
                //request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

                HttpResponse response = client.execute(request);
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                getRes = "";
                while ((inputLine = in.readLine()) != null) {
                    getRes = getRes + inputLine + "\n";
                }
                in.close();
                i = response.getStatusLine().getStatusCode();

            }


            System.out.println("Response Code : "+i);


            commonResponse.setResponseCode(i);
            commonResponse.setResponseBody(getRes);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonResponse;
    }

}
