package com.example.auth.presentation.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.AuthActions
import com.example.auth.presentation.AuthState
import com.example.auth.presentation.components.AuthResponse
import com.example.cookfresh.R
import com.example.ui.theme.CookFreshTheme

@Composable
fun SignUpScreen(
    onAction: (AuthActions)-> Unit,
    state: AuthState,
    modifier: Modifier = Modifier,
    onRegisterComplete: ()-> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            modifier = modifier
                .padding(16.dp)
        ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.weight(0.4f),
                        painter = painterResource(R.drawable.login_page),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier.weight(0.7f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "Get Started!",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(20.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30),
                            value = state.email,
                            onValueChange = {
                                onAction(AuthActions.OnEmailChange(it))
                            },
                            placeholder = { Text("Email") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Email,
                                    contentDescription = null
                                )
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30),
                            value = state.password,
                            onValueChange = {
                                onAction(AuthActions.OnPasswordChange(it))
                            },
                            placeholder = { Text("Password") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Lock,
                                    contentDescription = null
                                )
                            },
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Spacer(Modifier.height(10.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onAction(AuthActions.OnSignUpWithEmail(context))
                                if(state.isSignedIn is AuthResponse.Authenticated) {
                                    onRegisterComplete()
                                }
                            }
                        ) {
                            Text(
                                text = "Sign-Up",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "or continue with"
                        )
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onAction(AuthActions.SignIn(context))
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.google),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Sign-Up With Google",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                    }
                }

            }


        }
    }

@PreviewLightDark
@Composable
private fun Preview(){
    CookFreshTheme {
        SignUpScreen(
            onAction = {
            },
            state = AuthState(),
            modifier = Modifier,
            onRegisterComplete = {}
        )

    }
}
