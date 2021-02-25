package com.lbee.common.auth.client;

public interface OauthContext {

    String getToken();

    OauthUser getLoginUser();

}
