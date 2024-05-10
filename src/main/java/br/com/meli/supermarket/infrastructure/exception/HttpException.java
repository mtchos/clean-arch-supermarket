package br.com.archdemo.supermarket.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class HttpException extends HttpServerErrorException {
    public HttpException(HttpStatus statusCode) {
        super(statusCode);
    }

    public HttpException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
