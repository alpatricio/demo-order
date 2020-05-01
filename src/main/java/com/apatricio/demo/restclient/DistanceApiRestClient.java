package com.apatricio.demo.restclient;

import com.apatricio.demo.exception.ServiceException;
import com.apatricio.demo.model.dto.OrderRequest;
import com.apatricio.demo.model.google.DistanceMatrix;
import com.apatricio.demo.model.google.DistanceMatrixResponse;
import com.apatricio.demo.model.google.Element;
import com.apatricio.demo.model.google.Row;
import com.apatricio.demo.restclient.config.ApiBeanConfig;
import com.apatricio.demo.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource(factory = YamlPropertySourceFactory.class ,value="classpath:api-config.yml" )
public class DistanceApiRestClient
{

    public static final String INVALID_API_INPUT = "Invalid coordinates.";
    public static final String UNKNOWN_API_ERROR = "Something went wrong with Google Distance Matrix API.";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    ApiBeanConfig apiBeanConfig;

    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/";

    public Integer callGoogle(OrderRequest orderReq)
    {
        String startLat = orderReq.getOrigin()[0];
        String startLong = orderReq.getOrigin()[1];
        String endLat = orderReq.getDestination()[0];
        String endLong = orderReq.getDestination()[1];

        if (!validLat(startLat) || !validLat(endLat) || !validLong(startLong) || !validLong(endLong)) {
            throw new ServiceException(INVALID_API_INPUT);
        }

        String data = "json?units=metric&origins=" + startLat + "," + startLong + "&destinations=" + endLat + "," + endLong;
        String apiKey = apiBeanConfig.getDistanceApiKey();
        String key = "&key="+apiKey;

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> request = new HttpEntity<String>("", headers);

        ResponseEntity<DistanceMatrixResponse> response =
                restTemplate().exchange(API_URL + data + key, HttpMethod.GET, request, DistanceMatrixResponse.class);
        if (response != null && response.getBody() != null && !response.getBody().getRows().isEmpty()) {

            Row row = response.getBody().getRows().stream().findFirst().get();
            DistanceMatrix dm = row.getElements().stream().findFirst().get();
            if(dm.getStatus().equals("OK")){
                String distance = dm.getDistance().getText();
                Integer dist = Math.round(Float.parseFloat(distance.split(" ")[0]) * 1000);
                return dist;
            }else{
                throw new ServiceException(INVALID_API_INPUT);
            }
        }
        throw new ServiceException(UNKNOWN_API_ERROR);
    }

    private boolean validLat(String latitude) {
        try {
            Float lat = Float.parseFloat(latitude);
            return (lat <= 90 && lat >= -90);
        } catch (NumberFormatException e) {
            throw new ServiceException(INVALID_API_INPUT);
        }
    }

    private boolean validLong(String longitude) {
        try {
            Float lon = Float.parseFloat(longitude);
            return (lon <= 180 && lon >= -180);
        } catch (NumberFormatException e) {
            throw new ServiceException(INVALID_API_INPUT);
        }
    }
}
