package com.home.funny.gateway.security.dto;

import java.io.Serializable;

public record HFUserDetail(Long id, String username, String password,
                           Boolean enabled) implements Serializable {
}