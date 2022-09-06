package com.example.publish

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base_ui_compose.Layout

@Composable
fun PublishScreen(
    navigateUp: () -> Unit
) {
    PublishScreen(navigateUp = navigateUp, viewModel = hiltViewModel())
}

@Composable
internal fun PublishScreen(
    navigateUp: () -> Unit,
    viewModel: PublishViewModel
) {
    val uiState by viewModel.uiState

    val publishSuccess by viewModel.publishSuccess

    if (publishSuccess) {
        navigateUp()
    }

    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(
                horizontal = Layout.bodyMargin,
                vertical = Layout.gutter,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(80.dp))
            TitleField(uiState.title, viewModel::onTitleChange, Modifier.fillMaxWidth())
            Spacer(Modifier.height(20.dp))
            ContentField(uiState.content, viewModel::onContentChange, Modifier.fillMaxWidth())
            Spacer(Modifier.height(80.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                PublishButton(viewModel::addTopic)
            }
        }
    }
}

@Composable
fun TitleField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.title_input)) },
    )
}

@Composable
fun ContentField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = false,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.content_input)) },
    )
}

@Composable
fun PublishButton(publishAction: () -> Unit,
                        modifier: Modifier = Modifier) {
    Button(
        onClick = publishAction,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.publish_button))
    }
}