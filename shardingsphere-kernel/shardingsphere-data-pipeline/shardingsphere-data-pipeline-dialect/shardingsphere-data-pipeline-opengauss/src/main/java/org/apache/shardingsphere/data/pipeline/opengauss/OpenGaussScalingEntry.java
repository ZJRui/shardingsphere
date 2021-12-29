/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.data.pipeline.opengauss;

import org.apache.shardingsphere.data.pipeline.opengauss.importer.OpenGaussImporter;
import org.apache.shardingsphere.data.pipeline.opengauss.ingest.OpenGaussPositionInitializer;
import org.apache.shardingsphere.data.pipeline.opengauss.ingest.OpenGaussWalDumper;
import org.apache.shardingsphere.data.pipeline.opengauss.sqlbuilder.OpenGaussPipelineSQLBuilder;
import org.apache.shardingsphere.data.pipeline.postgresql.ingest.PostgreSQLInventoryDumper;
import org.apache.shardingsphere.scaling.core.spi.ScalingEntry;

/**
 * OpenGauss scaling entry.
 */
public final class OpenGaussScalingEntry implements ScalingEntry {
    
    @Override
    public Class<PostgreSQLInventoryDumper> getInventoryDumperClass() {
        return PostgreSQLInventoryDumper.class;
    }
    
    @Override
    public Class<OpenGaussWalDumper> getIncrementalDumperClass() {
        return OpenGaussWalDumper.class;
    }
    
    @Override
    public Class<OpenGaussPositionInitializer> getPositionInitializerClass() {
        return OpenGaussPositionInitializer.class;
    }
    
    @Override
    public Class<OpenGaussImporter> getImporterClass() {
        return OpenGaussImporter.class;
    }
    
    @Override
    public Class<OpenGaussEnvironmentChecker> getEnvironmentCheckerClass() {
        return OpenGaussEnvironmentChecker.class;
    }
    
    @Override
    public Class<OpenGaussPipelineSQLBuilder> getSQLBuilderClass() {
        return OpenGaussPipelineSQLBuilder.class;
    }
    
    @Override
    public String getDatabaseType() {
        return "openGauss";
    }
}