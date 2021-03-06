/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.map;

import com.hazelcast.map.listener.MapListener;
import com.hazelcast.test.HazelcastParametersRunnerFactory;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test event types as published by {@link com.hazelcast.map.impl.event.DefaultEntryEventFilteringStrategy}
 */
@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(HazelcastParametersRunnerFactory.class)
@Category({QuickTest.class, ParallelTest.class})
public class EntryListenerEventTypesTest extends AbstractEntryEventTypesTest {

    @Before
    public void setup() {
        instance = createHazelcastInstance();
        map = instance.getMap("EntryListenerEventTypesTestMap");
        eventCounter.set(0);
    }

    @After
    public void tearDown() {
        instance.shutdown();
    }

    @Override
    MapListener mapListenerFor_entryUpdatedEvent_whenOldValueOutside_newValueMatchesPredicate() {
        return new CountEntryUpdatedListener(eventCounter);
    }

    @Override
    MapListener mapListenerFor_entryUpdatedEvent_whenOldValueMatches_newValueOutsidePredicate() {
        return new CountEntryUpdatedListener(eventCounter);
    }

    @Override
    Integer expectedCountFor_entryUpdatedEvent_whenOldValueMatches_newValueOutsidePredicate() {
        return 0;
    }

}
