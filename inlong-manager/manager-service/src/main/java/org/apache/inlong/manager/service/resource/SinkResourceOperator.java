/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.manager.service.resource;

import org.apache.inlong.manager.common.enums.SinkType;
import org.apache.inlong.manager.common.pojo.sink.SinkInfo;

/**
 * Interface of the sink resource operator
 */
public interface SinkResourceOperator {

    /**
     * Determines whether the current instance matches the specified type.
     */
    Boolean accept(SinkType sinkType);

    /**
     * Create sink resource.
     *
     * @param groupId The inlong group id.
     * @param sinkInfo The sink response info.
     */
    default void createSinkResource(String groupId, SinkInfo sinkInfo) {
    }

    /**
     * Update sink resource.
     *
     * @param groupId The inlong group id.
     * @param sinkInfo The sink response info.
     */
    default void updateSinkResource(String groupId, SinkInfo sinkInfo) {
    }

    /**
     * Delete sink resource.
     *
     * @param groupId The inlong group id.
     * @param sinkInfo The sink response info.
     */
    default void deleteSinkResource(String groupId, SinkInfo sinkInfo) {
    }

}
