package com.google.credentialmanager.sample.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.credentialmanager.sample.domain.navigation.UIActions
import com.google.credentialmanager.sample.presentation.core.navigation.graphs.Graph
import com.google.credentialmanager.sample.R


@Preview
@Composable
fun MainView(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = stringResource(R.string.title),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            onClick = {
                mainViewModel.doUIAction(
                    UIActions.NavigateTo(Graph.SIGN_UP)
                )
            },
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = stringResource(R.string.btn_sign_up))
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            onClick = {
                mainViewModel.doUIAction(
                    UIActions.NavigateTo(Graph.SIGN_IN)
                )
            },
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = stringResource(R.string.btn_sign_in))
        }

    }
}