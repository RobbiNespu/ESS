/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.util.RestControllerHelper
 * Last modified:  5/25/21, 6:27 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RestControllerHelper implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(RestControllerHelper.class);

    public String ConvertToJsonString(Object entity) {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        return objectToJsonObjectNode.EntitiesToJsonParent(entity);
    }

    public Map SendFailedStatusWithReason(String reason) {
        HashMap map = new HashMap<>();
        map.put("status", "Failed");
        map.put("reason", reason);
        logger.debug("FAILED -> {}", reason);
        return map;
    }

    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;
        for (String key : keys) {
            value = ((Map) value).get(key);
        }
        return (T) value;
    }
}
