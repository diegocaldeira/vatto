package com.aoks.utils.webmvc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Implementacao das operacoess basicas do client para API REST.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
public class AbstractWebGateway {

    protected static final int SUCCESS_CODE = 200;
    protected static final int BAD_REQUEST = 400;
    protected static final int CREATED_CODE = 201;
    

    public String get(String url) {
        GetMethod method = new GetMethod(url);
        String result = makeRequest(method);

        return result;
    }

    public String delete(String url, Long id) {
        DeleteMethod method = new DeleteMethod(url + id);
        String result = makeRequest(method);

        return result;
    }

    public String post(String json, String url)  {
        PostMethod method = new PostMethod(url);
        StatusId statusId = makeRequestWithBody(method, json);

        return statusId.id;
    }

    public String put(String json, String url, Long id) {
        PutMethod method = new PutMethod(url + id);
        StatusId statusId = makeRequestWithBody(method, json);

        return statusId.status.toString();
    }

    /**
     * Executa POST e PUT
     * @param method
     * @param json
     * @return StatusId
     */
    private StatusId makeRequestWithBody(EntityEnclosingMethod method, String json) {
        StatusId statusId = new StatusId();
        try {
            method.addRequestHeader("Accept", "application/json");
            StringRequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
            method.setRequestEntity(requestEntity);
            method.setDoAuthentication(true);
            HttpClient client = new HttpClient();
            client = getBasicAuth(client);

            statusId.status = client.executeMethod(method);

            String messageBody = method.getResponseBodyAsString();

//            System.out.println("------------------------- CODIGO RETORNO " + method.getName() + ":---------------------");
//            System.out.println(statusId.status.toString());
//            System.out.println("------------------------- MESSAGE BODY " + method.getName() + ":-----------------------");
//            System.out.println(messageBody);

            messageBody = this.formataPut(messageBody);

            if(messageBody.contains("{\"data\":")){
               messageBody= formataPut(messageBody);
            }

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(messageBody);

            if(statusId.status.equals(CREATED_CODE) || statusId.status.equals(SUCCESS_CODE)){
                statusId.id = jsonObject.get("id").toString();
            }

        } catch (HttpException ex) {
        	System.out.println(ex.toString());
        } catch (UnsupportedEncodingException ex) {
        	System.out.println(ex.toString());
        } catch (IOException ex) {
        	System.out.println(ex.toString());
        } catch (ParseException ex) {
        	System.out.println(ex.toString());
        }
        return statusId;
    }
    

    /**
     * Executa GET e DELETE, retorna o codigo. 
     * 
     * @param method
     * @return int
     */ 
    private String makeRequest(HttpMethodBase method) {
        try {
            method.addRequestHeader("Accept", "application/json");
            method.setDoAuthentication(true);

            HttpClient client = new HttpClient();
            client = this.getBasicAuth(client);
            int result = client.executeMethod(method);

            String messageBody = method.getResponseBodyAsString();

//            System.out.println("------------------------- CODIGO RETORNO " + method.getName() + ":---------------------");
//            System.out.println(String.valueOf(result));
//            System.out.println("------------------------- MESSAGE BODY " + method.getName() + ":-----------------------");
//            System.out.println(messageBody);
            return messageBody;

        } catch (IOException ex) {
        	System.out.println(ex.toString());
            return String.valueOf(BAD_REQUEST);
        } finally {
            method.releaseConnection();
        }
    }

    /**
     * Seta dados para autenticação, busca no banco
     * usuário e senha atual da API.
     * 
     * @param client
     * @return 
     */
    private HttpClient getBasicAuth(HttpClient client) {
        client.getState().setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials("USERNAME", "PASSWORD"));

        return client;
    }

    private class StatusId {

        public Integer status;
        public String id;
    }

    /**
     * Coloca o retorno no formato adequado para o JSONObject
     * 
     * @param json
     * @return 
     */
    private String formataPut(String json) {
        if(json.contains("{\"data\":")){
            json = json.replace("{\"data\":", "");
            json = json.substring(0 ,json.length()-1);
        }
        return json;


    }
}
