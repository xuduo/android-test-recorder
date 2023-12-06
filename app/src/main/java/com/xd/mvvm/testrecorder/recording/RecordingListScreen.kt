package com.xd.mvvm.testrecorder.recording

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xd.mvvm.testrecorder.LocalNavController
import com.xd.mvvm.testrecorder.R
import com.xd.mvvm.testrecorder.data.Recording
import com.xd.mvvm.testrecorder.data.RecordingWithActionCount
import com.xd.mvvm.testrecorder.goToActionList
import com.xd.mvvm.testrecorder.logger.L
import com.xd.mvvm.testrecorder.util.LiveDataLoadingContent
import com.xd.mvvm.testrecorder.util.RefreshingLoadingContent
import com.xd.mvvm.testrecorder.widget.AppBar

@Composable
fun RecordingListScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppBar(R.string.recording_list) // Change the string resource to your app's name
        },
    ) {
        RecordingListScreenContent(
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun RecordingListScreenContent(
    viewModel: RecordingViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val data = viewModel.getAllRecordings()
    LiveDataLoadingContent(
        data
    ) {
        LazyColumn {
            L.d("WeatherContent RecordingListScreenContent ${it.size}")
            items(it.size) { index ->
                Item(
                    it[index]
                )
            }
        }
    }
}

@Composable
private fun Item(
    recording: RecordingWithActionCount
) {
    val nav = LocalNavController.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                nav.goToActionList(recording.recording.id)
            }
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
    ) {
        recording.recording.getIconBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "App Icon",
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.horizontal_margin))
                    .size(40.dp)
            )
            Text(
                text = "${recording.actionCount} Actions",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = recording.recording.getFormattedCreateTime(),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}
