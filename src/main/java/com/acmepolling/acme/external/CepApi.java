package com.acmepolling.acme.external;

import com.acmepolling.acme.entity.Localidade;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


@Component
public class CepApi
{
    private static final String API_ENDPOINT = "https://viacep.com.br/ws/";

    public static Localidade getLocalidade(String cep) {
        String callUrl = API_ENDPOINT + cep + "/json";

        JsonObject object = getCepResponse(callUrl);

        Localidade localidade = Localidade.builder()
                .cep(cep)
                .logradouro(object.getString("logradouro"))
                .complemento(object.getString("complemento"))
                .bairro(object.getString("bairro"))
                .cidade(object.getString("localidade"))
                .estado(object.getString("uf"))
                .build();

        return localidade;
    }

    private static JsonObject getCepResponse(String url) {
        JsonObject responseJO;

        try {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);

                HttpEntity entity = response.getEntity();

                try (JsonReader reader = Json.createReader(entity.getContent())) {
                    responseJO = reader.readObject();
                }

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException(response.getStatusLine().getReasonPhrase());
                }
                response.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseJO;
    }
}

