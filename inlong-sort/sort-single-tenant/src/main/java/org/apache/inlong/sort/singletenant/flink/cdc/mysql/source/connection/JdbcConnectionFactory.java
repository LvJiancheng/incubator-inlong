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

package org.apache.inlong.sort.singletenant.flink.cdc.mysql.source.connection;

import com.zaxxer.hikari.HikariDataSource;
import io.debezium.jdbc.JdbcConfiguration;
import io.debezium.jdbc.JdbcConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.flink.util.FlinkRuntimeException;
import org.apache.inlong.sort.singletenant.flink.cdc.mysql.source.config.MySqlSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A factory to create JDBC connection for MySQL. */
public class JdbcConnectionFactory implements JdbcConnection.ConnectionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcConnectionFactory.class);

    private final MySqlSourceConfig sourceConfig;

    public JdbcConnectionFactory(MySqlSourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    @Override
    public Connection connect(JdbcConfiguration config) throws SQLException {
        final int connectRetryTimes = sourceConfig.getConnectMaxRetries();

        final ConnectionPoolId connectionPoolId =
                new ConnectionPoolId(sourceConfig.getHostname(), sourceConfig.getPort());

        HikariDataSource dataSource =
                JdbcConnectionPools.getInstance()
                        .getOrCreateConnectionPool(connectionPoolId, sourceConfig);

        int i = 0;
        while (i < connectRetryTimes) {
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                if (i < connectRetryTimes - 1) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ie) {
                        throw new FlinkRuntimeException(
                                "Failed to get connection, interrupted while doing another attempt",
                                ie);
                    }
                    LOG.warn("Get connection failed, retry times {}", i + 1);
                } else {
                    LOG.error("Get connection failed after retry {} times", i + 1);
                    throw new FlinkRuntimeException(e);
                }
            }
            i++;
        }
        return dataSource.getConnection();
    }
}
