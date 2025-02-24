/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.sort.singletenant.flink.cdc.debezium.table;

import io.debezium.relational.history.TableChanges;
import java.io.Serializable;
import javax.annotation.Nullable;
import org.apache.flink.annotation.Internal;
import org.apache.flink.table.data.RowData;
import org.apache.kafka.connect.source.SourceRecord;

/**
 * A converter converts {@link SourceRecord} metadata into Flink internal data structures.
 */
@FunctionalInterface
@Internal
public interface MetadataConverter extends Serializable {

    Object read(SourceRecord record);

    default Object read(SourceRecord record, @Nullable TableChanges.TableChange tableSchema) {
        return read(record);
    }

    default Object read(SourceRecord record, @Nullable TableChanges.TableChange tableSchema, RowData rowData) {
        return read(record);
    }
}
