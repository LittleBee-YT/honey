package com.lbee.common.auth.client;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OauthUser implements Serializable {

    private static final long serialVersionUID = 3511817197819217587L;

    private Long userId;

    private String userName;

    private boolean enabled;

    private List<String> authorities;

}
