/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.util.RestControllerHelper
 * Last modified:  5/25/21, 12:09 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.util;

public class RestControllerHelper {
    public String ConvertToJsonString(Object entity) {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        return objectToJsonObjectNode.EntitiesToJsonParent(entity);
    }
}
