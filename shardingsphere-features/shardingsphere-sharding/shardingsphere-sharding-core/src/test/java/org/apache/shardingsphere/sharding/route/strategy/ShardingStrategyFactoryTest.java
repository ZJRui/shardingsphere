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

package org.apache.shardingsphere.sharding.route.strategy;

import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.route.strategy.fixture.ComplexKeysShardingAlgorithmFixture;
import org.apache.shardingsphere.sharding.route.strategy.fixture.HintShardingAlgorithmFixture;
import org.apache.shardingsphere.sharding.route.strategy.fixture.StandardShardingAlgorithmFixture;
import org.apache.shardingsphere.sharding.route.strategy.type.complex.ComplexShardingStrategy;
import org.apache.shardingsphere.sharding.route.strategy.type.hint.HintShardingStrategy;
import org.apache.shardingsphere.sharding.route.strategy.type.none.NoneShardingStrategy;
import org.apache.shardingsphere.sharding.route.strategy.type.standard.StandardShardingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class ShardingStrategyFactoryTest {
    
    @Mock
    private StandardShardingStrategyConfiguration standardShardingStrategyConfig;
    
    @Mock
    private StandardShardingAlgorithmFixture standardShardingAlgorithmFixture;
    
    @Mock
    private ComplexShardingStrategyConfiguration complexShardingStrategyConfig;
    
    @Mock
    private ComplexKeysShardingAlgorithmFixture complexKeysShardingAlgorithmFixture;
    
    @Mock
    private HintShardingStrategyConfiguration hintShardingStrategyConfig;
    
    @Mock
    private HintShardingAlgorithmFixture hintShardingAlgorithmFixture;
    
    @Test
    public void assertNewInstance() {
        when(standardShardingStrategyConfig.getShardingColumn()).thenReturn("standard_sharding_column");
        ShardingStrategy actualStandardShardingStrategy = ShardingStrategyFactory.newInstance(standardShardingStrategyConfig, standardShardingAlgorithmFixture, null);
        assertTrue(actualStandardShardingStrategy instanceof StandardShardingStrategy);
        when(complexShardingStrategyConfig.getShardingColumns()).thenReturn("complex_sharding_column");
        ShardingStrategy actualComplexShardingStrategy = ShardingStrategyFactory.newInstance(complexShardingStrategyConfig, complexKeysShardingAlgorithmFixture, null);
        assertTrue(actualComplexShardingStrategy instanceof ComplexShardingStrategy);
        ShardingStrategy actualHintShardingStrategy = ShardingStrategyFactory.newInstance(hintShardingStrategyConfig, hintShardingAlgorithmFixture, null);
        assertTrue(actualHintShardingStrategy instanceof HintShardingStrategy);
        ShardingStrategy actualNoneShardingStrategy = ShardingStrategyFactory.newInstance(null, null, null);
        assertTrue(actualNoneShardingStrategy instanceof NoneShardingStrategy);
        ShardingStrategy actualStandardWithDefaultColumnStrategy = ShardingStrategyFactory.newInstance(mock(StandardShardingStrategyConfiguration.class), standardShardingAlgorithmFixture, "order_id");
        assertTrue(actualStandardWithDefaultColumnStrategy.getShardingColumns().contains("order_id"));
    }
}
