package com.picpay.service;


import com.picpay.dto.authorization.AuthorizationDTO;
import com.picpay.dto.authorization.AuthorizationResponseDTO;
import com.picpay.dto.notification.NotificationDTO;
import com.picpay.dto.notification.NotificationResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class IntegrationApiService {

    private final RestTemplate restTemplate;

    @Value("${api.authorization.transaction}")
    private String authorizationUrl;

    @Value("${api.notification.transaction}")
    private String messageNotificationUrl;

    public IntegrationApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public Boolean authorizationRequest(AuthorizationDTO dto){
        HttpEntity<AuthorizationDTO> requestEntity = createRequestEntity(dto);

        try {
            ResponseEntity<AuthorizationResponseDTO> responseEntity = restTemplate.exchange(authorizationUrl, HttpMethod.GET,
                    requestEntity,
                    AuthorizationResponseDTO.class);

            AuthorizationResponseDTO authorizationResponseDTO = responseEntity.getBody();

            return authorizationResponseDTO.getData().getAuthorization();

        } catch (RestClientException e){
            log.error("Erro ao buscar autorização da transação {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error ao consultar a autorização");
        }
    }

    public Boolean sendNotificationRequest(NotificationDTO dto){
        HttpEntity<NotificationDTO> requestEntity = createRequestEntity(dto);

        try{
            ResponseEntity<NotificationResponseDTO> responseEntity = restTemplate.exchange(messageNotificationUrl,
                    HttpMethod.POST,
                    requestEntity,
                    NotificationResponseDTO.class);
            NotificationResponseDTO notificationResponse  = responseEntity.getBody();

            if(!notificationResponse.getStatus().equalsIgnoreCase("error")){
                return true;
            }
            return false;

        }catch (RestClientException e){
            log.error("Erro ao notificar lojistas {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error ao notificar lojista");
        }

    }



    private <T> HttpEntity<T> createRequestEntity(T body) {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(body, headers);
    }


}
