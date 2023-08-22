package com.google.credentialmanager.sample.presentation.signup

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.credentialmanager.sample.domain.UIEventUser
import com.google.credentialmanager.sample.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignupView(
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalContext.current as FragmentActivity
    val executor = ContextCompat.getMainExecutor(activity)
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
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 21.dp),
            value = signupViewModel.userRequest.username,
            onValueChange = {
                signupViewModel.setValues(
                    UIEventUser.Username(username = it)
                )

            },
            label = {
                Text(text = stringResource(R.string.username_hint))
            },
            enabled = !signupViewModel.showProgressIndicator,
            isError = signupViewModel.userNameError,
            supportingText = {
                if (signupViewModel.userNameError) {
                    Text(
                        text = stringResource(id = R.string.user_name_error)
                    )
                }
            }
        )

        if (signupViewModel.showPasswordField) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 21.dp),
                value = signupViewModel.userRequest.password,
                enabled = !signupViewModel.showProgressIndicator,
                onValueChange = {
                    signupViewModel.setValues(
                        UIEventUser.Password(password = it)
                    )
                },
                label = {
                    Text(text = stringResource(R.string.password_hint))
                },
                isError = signupViewModel.passwordError,
                supportingText = {
                    if (signupViewModel.passwordError) {
                        Text(text = stringResource(id = R.string.password_error))
                    }
                }
            )
        }
        // TODO - Finish the ViewModel migration from XML with Profesor Jose and Big Mike
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (signupViewModel.showProgressIndicator) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 10.dp))
                Text(text = stringResource(R.string.sv_cpi_text))
            }
        }
        Text(
            text = stringResource(R.string.sv_passkey_hint),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                signupViewModel.signUpWithPasskeys(context as Activity)
            },
            enabled = !signupViewModel.showProgressIndicator,
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
                signupViewModel.signUpWithPassword(context)
            },
            enabled = !signupViewModel.showProgressIndicator,
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = stringResource(R.string.sv_btn_signup_password))
        }
    }
}