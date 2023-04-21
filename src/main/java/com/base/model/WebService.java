package com.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebService {
    private Long webServiceId;
    private String name;
    private Long status;
    private String url;
    private String userName;
    private String password;
    private String errorCodeTag;
    private String template;
    private Long webServiceType;
    private Long responseType;
    private Long timeOut;
    private String errorCodeRetry;
}
