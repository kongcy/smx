package com.xtxk.core.encrypt.jwt;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/** see : https://github.com/auth0/java-jwt */
public class JWTAudienceException extends JWTVerifyException {
    private JsonNode audienceNode;

    public JWTAudienceException(JsonNode audienceNode) {
        this.audienceNode = audienceNode;
    }

    public JWTAudienceException(String message, JsonNode audienceNode) {
        super(message);
        this.audienceNode = audienceNode;
    }

    public List<String> getAudience() {
        ArrayList<String> audience = new ArrayList<String>();
        if (audienceNode.isArray()) {
            for (JsonNode jsonNode : audienceNode) {
                audience.add(jsonNode.textValue());
            }
        } else if (audienceNode.isTextual()) {
            audience.add(audienceNode.textValue());
        }
        return audience;
    }
}
