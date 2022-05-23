/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.manager.common.pojo.source.mongo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.inlong.manager.common.enums.ErrorCodeEnum;
import org.apache.inlong.manager.common.exceptions.BusinessException;

import javax.validation.constraints.NotNull;

/**
 * mongo source information data transfer object.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MongoSourceDTO {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @ApiModelProperty("Mongo primaryKey")
    private String primaryKey;

    @ApiModelProperty("Mongo hosts")
    private String hosts;

    @ApiModelProperty("Mongo username")
    private String username;

    @ApiModelProperty("Mongo password")
    private String password;

    @ApiModelProperty("Mongo database")
    private String database;

    @ApiModelProperty("Mongo collection")
    private String collection;

    /**
     * Get the dto instance from the request
     */
    public static MongoSourceDTO getFromRequest(MongoSourceRequest request) {
        return MongoSourceDTO.builder()
                .primaryKey(request.getPrimaryKey())
                .hosts(request.getHosts())
                .username(request.getUsername())
                .password(request.getPassword())
                .database(request.getDatabase())
                .collection(request.getCollection())
                .build();
    }

    public static MongoSourceDTO getFromJson(@NotNull String extParams) {
        try {
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(extParams, MongoSourceDTO.class);
        } catch (Exception e) {
            throw new BusinessException(ErrorCodeEnum.SOURCE_INFO_INCORRECT.getMessage());
        }
    }

}
