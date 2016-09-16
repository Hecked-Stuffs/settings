/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.core.instrumentation;

import android.content.Context;

import com.android.settings.TestConfig;
import com.android.settings.overlay.FeatureFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = TestConfig.MANIFEST_PATH, sdk = TestConfig.SDK_VERSION)
public class MetricsFeatureProviderTest {

    private ShadowApplication mApplication;
    private Context mContext;

    @Mock
    private LogWriter mLogWriter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mApplication = ShadowApplication.getInstance();
        mContext = mApplication.getApplicationContext();
    }

    @Test
    public void getFactory_shouldReuseCachedInstance() {
        MetricsFeatureProvider feature1 =
                FeatureFactory.getFactory(mContext).getMetricsFeatureProvider();
        MetricsFeatureProvider feature2 =
                FeatureFactory.getFactory(mContext).getMetricsFeatureProvider();

        assertThat(feature1 == feature2).isTrue();
    }
}
