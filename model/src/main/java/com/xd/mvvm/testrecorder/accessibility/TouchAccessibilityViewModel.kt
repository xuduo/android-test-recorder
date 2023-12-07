/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xd.mvvm.testrecorder.accessibility

import android.content.Context
import androidx.lifecycle.ViewModel
import com.xd.mvvm.testrecorder.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class TouchAccessibilityViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val logger = Logger("TouchAccessibilityViewModel")

    init {
        logger.d("TouchAccessibilityViewModel.init")
    }

    fun testClick(){
        TouchAccessibilityService.service?.testDispatch()
    }

}
