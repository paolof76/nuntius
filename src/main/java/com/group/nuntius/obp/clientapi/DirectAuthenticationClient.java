package com.group.nuntius.obp.clientapi;

import com.group.nuntius.obp.domain.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="account", url="${obp.api.rootUrl}")
public interface DirectAuthenticationClient {

    @PostMapping(value = "${obp.api.directLoginPath}")
    Token loginInternal(@RequestHeader("Authorization") String authHeader);

    default String login(String username, String password, String consumerKey) {
        String dlData = String.format("DirectLogin username=%s,password=%s,consumer_key=%s", username, password, consumerKey);
        return loginInternal(dlData).getToken();
    }
}
