/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.util.RestControllerHelper
 * Last modified:  5/27/21, 3:28 PM
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
    // User-defined SerialVersionUID
    private static final long SerialVersionUID = 1l;
    private static final Logger logger = LoggerFactory.getLogger(RestControllerHelper.class);

    public String ConvertToJsonString(Object entity) {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        return objectToJsonObjectNode.EntitiesToJsonParent(entity);
    }

    public Map SendStatusFailed(String reason) {
        HashMap processing_info = new HashMap<>();
        HashMap details = new HashMap<>();
        details.put("status", "Failed");
        details.put("reason", reason);
        processing_info.put("processing_info", details);
        logger.error("{} -> {}", details.get("status"), details.get("reason"));
        return processing_info;
    }

    public Map SendStatusSuccess(String reason) {
        HashMap processing_info = new HashMap<>();
        HashMap details = new HashMap<>();
        details.put("status", "Success");
        details.put("reason", reason);
        processing_info.put("processing_info", details);
        logger.info("{} -> {}", details.get("status"), details.get("reason"));
        return processing_info;
    }

    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;
        for (String key : keys) {
            value = ((Map) value).get(key);
        }
        return (T) value;
    }
}
