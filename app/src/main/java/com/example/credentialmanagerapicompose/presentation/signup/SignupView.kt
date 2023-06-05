package com.example.credentialmanagerapicompose.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.credentialmanagerapicompose.R
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import com.example.credentialmanagerapicompose.presentation.core.navigation.graphs.Graph


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignupView(
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = stringResource(R.string.sv_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier
                .padding(top = 21.dp),
            value = "",
            onValueChange = {},
            label = {
                Text(text = "")
            }
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (true) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 10.dp))
            }
            Text(text = stringResource(R.string.sv_cpi_text))
        }
        Text(
            text = stringResource(R.string.sv_passkey_hint),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                signupViewModel.doUIAction(
                    UIActions.NavigateTo(Graph.SIGN_UP)
                )
            },
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = stringResource(R.string.sv_btn_signup_passkey))
        }
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.sv_or_hint)
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.sv_signup_hint),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            onClick = {
                signupViewModel.doUIAction(
                    UIActions.NavigateTo(Graph.SIGN_UP)
                )
            },
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = stringResource(R.string.sv_btn_signup_password))
        }
    }
}