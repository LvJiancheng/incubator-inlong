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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.inlong.manager.common.enums.SourceType;
import org.apache.inlong.manager.common.pojo.source.SourceListResponse;

/**
 * Response of mongo source list
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Response of mongo source paging list")
public class MongoSourceListResponse extends SourceListResponse {

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

    public MongoSourceListResponse() {
        this.setSourceType(SourceType.MONGO.getType());
    }

}
