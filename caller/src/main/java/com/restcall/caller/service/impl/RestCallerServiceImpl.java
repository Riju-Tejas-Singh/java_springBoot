package com.restcall.caller.service.impl;

import com.restcall.caller.config.ConfigProperties;
import com.restcall.caller.constants.RestConstants;
import com.restcall.caller.dto.RequestDto;
import com.restcall.caller.service.RestCallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RestCallerServiceImpl implements RestCallerService {

    private final RestTemplate restTemplate;
    private final ConfigProperties configProperties;
    private static final String POST_CALl_API = "/server/receive";
    private static final String GET_CALl_API = "/server/receive-get";

    @Override
    public <T> T call(RequestDto requestDto, Class<T> responseType) {
        try {
            String url = configProperties.getHost() + RestConstants.COLON + configProperties.getPort()
                    + POST_CALl_API;
            HttpHeaders headers = getHeaders();
            HttpEntity<RequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
            if (!response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Non 200 response status code from rest call");
            }
            System.out.println(response);
            return response.getBody();
        } catch (Exception ex) {
            System.out.println(RestConstants.REST_CALL_EXCEPTION + ex);
        }
        return null;
    }

    @Override
    public RequestDto getCall (String message, String priority) {
        try {
            String url = configProperties.getHost() + RestConstants.COLON + configProperties.getPort()
                    + GET_CALl_API + "/{message}/{priority}";
            ResponseEntity<RequestDto> response = restTemplate.getForEntity(url, RequestDto.class, message, priority);
            if (!response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Non 200 response status code from rest call");
            }
            System.out.println(response);
            return response.getBody();
        } catch (Exception ex) {
            System.out.println(RestConstants.REST_CALL_EXCEPTION + ex);
        }
        return null;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
