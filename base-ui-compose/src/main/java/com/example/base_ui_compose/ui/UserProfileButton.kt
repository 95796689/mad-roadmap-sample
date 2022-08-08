package com.example.base_ui_compose.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.base_ui_compose.R

@Composable
fun UserProfileButton(
    loggedIn: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        when {
//            loggedIn && user?.avatarUrl != null -> {
//                AsyncImage(
//                    model = user.avatarUrl!!,
//                    requestBuilder = { crossfade(true) },
//                    contentDescription = stringResource(R.string.cd_profile_pic, user.name ?: user.username),
//                    modifier = Modifier
//                        .size(32.dp)
//                        .clip(MaterialTheme.shapes.small),
//                )
//            }
            else -> {
                Icon(
                    imageVector = when {
                        loggedIn -> Icons.Default.Person
                        else -> Icons.Outlined.Person
                    },
                    contentDescription = stringResource(R.string.cd_profile_pic)
                )
            }
        }
    }
}