package ru.rogoff.educationservice.exception;


import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String method, Response response) {
        if (response.status() == 404) {
            return new RuntimeException("404 Not Found ERROR");
        }
        return new RuntimeException("500 custom ERROR");
    }
}