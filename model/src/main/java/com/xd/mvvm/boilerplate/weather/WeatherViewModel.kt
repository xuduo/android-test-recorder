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

package com.xd.mvvm.boilerplate.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xd.mvvm.boilerplate.coroutine.io
import com.xd.mvvm.boilerplate.data.D
import com.xd.mvvm.boilerplate.data.Loading
import com.xd.mvvm.boilerplate.logger.Logger
import com.xd.mvvm.boilerplate.net.HttpService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class WeatherViewModel @Inject constructor(
    @Named("weather")
    private val http: HttpService
) : ViewModel() {
    private val logger = Logger("WeatherViewModel")
    private val _weather = MutableLiveData<D<Weather>>(Loading())
    val weather: LiveData<D<Weather>> get() = _weather

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        _weather.postValue(Loading(_weather.value?.value))
        io {
            val data = http.fetchData(
                "forecast",
                Weather::class.java,
                mapOf(
                    "latitude" to 52.52,
                    "longitude" to 13.41,
                    "current" to "temperature_2m,windspeed_10m",
                    "hourly" to "temperature_2m,relativehumidity_2m,windspeed_10m"
                )
            )
            logger.d("fetchWeather postValue")
            _weather.postValue(data)
        }
    }

}