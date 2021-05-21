package io.gitlab.robbinespu.ess.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class ObjectToJsonObjectNode {
    public String EntitiesToJsonParent(Object object) {
        String json = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
