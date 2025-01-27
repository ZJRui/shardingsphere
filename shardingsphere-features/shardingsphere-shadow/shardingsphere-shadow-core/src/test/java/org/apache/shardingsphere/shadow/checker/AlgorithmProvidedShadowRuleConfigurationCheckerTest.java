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

package org.apache.shardingsphere.shadow.checker;

import org.apache.shardingsphere.infra.config.checker.RuleConfigurationChecker;
import org.apache.shardingsphere.shadow.algorithm.config.AlgorithmProvidedShadowRuleConfiguration;
import org.apache.shardingsphere.shadow.algorithm.shadow.column.ColumnRegexMatchShadowAlgorithm;
import org.apache.shardingsphere.shadow.api.config.datasource.ShadowDataSourceConfiguration;
import org.apache.shardingsphere.shadow.api.config.table.ShadowTableConfiguration;
import org.apache.shardingsphere.shadow.spi.ShadowAlgorithm;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public final class AlgorithmProvidedShadowRuleConfigurationCheckerTest {
    
    private final RuleConfigurationChecker<AlgorithmProvidedShadowRuleConfiguration> checker = new AlgorithmProvidedShadowRuleConfigurationChecker();
    
    @Test
    public void assertCheck() {
        checker.check("", createAlgorithmProvidedShadowRuleConfiguration());
    }
    
    private AlgorithmProvidedShadowRuleConfiguration createAlgorithmProvidedShadowRuleConfiguration() {
        AlgorithmProvidedShadowRuleConfiguration result = new AlgorithmProvidedShadowRuleConfiguration();
        result.setShadowAlgorithms(createShadowAlgorithms());
        result.setDataSources(createDataSources());
        result.setTables(createTables());
        return result;
    }
    
    private Map<String, ShadowTableConfiguration> createTables() {
        Map<String, ShadowTableConfiguration> result = new LinkedHashMap<>();
        Collection<String> dataSourceNames = new LinkedList<>();
        Collection<String> shadowAlgorithmNames = new LinkedList<>();
        shadowAlgorithmNames.add("user-id-insert-match-algorithm");
        result.put("t_order", new ShadowTableConfiguration(dataSourceNames, shadowAlgorithmNames));
        return result;
    }
    
    private Map<String, ShadowDataSourceConfiguration> createDataSources() {
        Map<String, ShadowDataSourceConfiguration> result = new LinkedHashMap<>();
        result.put("shadow-data-source", new ShadowDataSourceConfiguration("ds", "ds_shadow"));
        return result;
    }
    
    private Map<String, ShadowAlgorithm> createShadowAlgorithms() {
        Map<String, ShadowAlgorithm> result = new LinkedHashMap<>();
        result.put("user-id-insert-match-algorithm", createColumnRegexMatchShadowAlgorithm());
        return result;
    }
    
    private ShadowAlgorithm createColumnRegexMatchShadowAlgorithm() {
        ColumnRegexMatchShadowAlgorithm result = new ColumnRegexMatchShadowAlgorithm();
        result.setProps(createProperties());
        result.init();
        return result;
    }
    
    private Properties createProperties() {
        Properties result = new Properties();
        result.setProperty("column", "shadow");
        result.setProperty("operation", "insert");
        result.setProperty("regex", "[1]");
        return result;
    }
}
