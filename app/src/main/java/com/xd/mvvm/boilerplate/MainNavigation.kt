/*
 * Copyright 2022 The Android Open Source Project
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

package com.xd.mvvm.boilerplate

import androidx.navigation.NavHostController

/**
 * Destinations used in the [MainActivity]
 */
object MainDestinations {
    const val RECORD = "RECORD"
    const val MAIN = "main"
    const val WEATHER = "weather"
    const val CACHED_WEATHER = "cached_weather"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigate(dest: String) {
        navController.navigate(dest)
    }

}
