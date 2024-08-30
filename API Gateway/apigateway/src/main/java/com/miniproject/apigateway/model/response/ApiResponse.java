package com.miniproject.apigateway.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private Header header;
    private Status status;
    private String errorMessage;
    private T content;

    // Getter dan Setter
    
    @Getter
    @Setter
    public static class Header {
        private String requestId;
        private String timestamp;

        // Getter dan Setter
    }

    @Getter
    @Setter
    public static class Status {
        private int code;
        private String description;

        // Getter dan Setter
    }
}
