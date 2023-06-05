package com.example.credentialmanagerapicompose.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.credentialmanagerapicompose.R
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import com.example.credentialmanagerapicompose.presentation.core.navigation.graphs.Graph

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = viewModel.signedInText.value,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    viewModel.doUIAction(
                        UIActions.NavigateTo(Graph.SIGN_IN)
                    )
                }) {
                Text(text = stringResource(R.string.sign_out_and_try_again))
            }

            val context = LocalContext.current
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    viewModel.doUIAction(
                        UIActions.ShowToast(
                            context.getString(R.string.showing_toast)
                        )
                    )
                }
            ) {
                Text(text = "Sign out and try again")
            }
        }
    }

}