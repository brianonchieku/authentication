package com.example.signuptest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signuptest.ui.theme.SignupTestTheme
import com.example.signuptest.ui.theme.UserDatabase
import com.example.signuptest.ui.theme.UserViewModel
import kotlinx.coroutines.launch

class RecoverActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Recover(userViewModel)

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Recover(userViewModel: UserViewModel){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3)
            , contentDescription =null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .wrapContentSize()
                .padding(30.dp), elevation = 20.dp, shape = RoundedCornerShape(20.dp)
            ) {
                val context= LocalContext.current
                val db =UserDatabase.getDatabase(context)
                val userDao = db.userDao()
                val coroutineScope = rememberCoroutineScope()
                var question by remember{
                    mutableStateOf("")
                }
                var answer by remember{
                    mutableStateOf("")
                }
                var newPass by remember{
                    mutableStateOf("")
                }
                var confirmNewPass by remember{
                    mutableStateOf("")
                }

                val securityQuestions = listOf(
                    "What is your pet's name?",
                    "What is your mother's maiden name?",
                    "What was your first car?",
                    "What elementary school did you attend?",
                    "What is the name of your first pet?"
                )

                var expanded by remember { mutableStateOf(false) }
                var selectedQuestion by remember { mutableStateOf(securityQuestions[0]) }
                Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Recover Account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(20.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedQuestion,
                            onValueChange = { selectedQuestion = it },
                            readOnly = true,
                            label = { Text(text = "security question") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            securityQuestions.forEach { question ->
                                DropdownMenuItem(onClick = {
                                    selectedQuestion = question
                                    expanded = false
                                }) {
                                    Text(text = question)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = answer, onValueChange ={answer=it}
                        , label = {
                            Text(text = "answer")
                        } , placeholder = {
                            Text(text = "answer")
                        } )
                    Spacer(modifier = Modifier.size(5.dp))
                    OutlinedTextField(value = newPass, onValueChange ={newPass=it}
                        , label = {
                            Text(text = "new pasword")
                        } , placeholder = {
                            Text(text = "new password")
                        } )
                    OutlinedTextField(value = confirmNewPass, onValueChange ={confirmNewPass=it}
                        , label = {
                            Text(text = "confirm password")
                        } , placeholder = {
                            Text(text = "confirm password")
                        } )

                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {
                        if (selectedQuestion.isNotEmpty() && answer.isNotEmpty()) {
                            // Call ViewModel function to check security question and answer
                            val isCorrect = userViewModel.checkSecurityQuestionAnswer(selectedQuestion, answer)
                            if (isCorrect) {
                                // Proceed with updating password
                                if (newPass.isNotEmpty() && confirmNewPass.isNotEmpty() && newPass == confirmNewPass) {
                                    // Call ViewModel function to update password
                                    coroutineScope.launch {
                                        val success = userViewModel.recoverPassword(selectedQuestion, answer, newPass)
                                        if (success) {
                                            Toast.makeText(context, "Password updated", Toast.LENGTH_SHORT).show()
                                            // Navigate to the login screen or any other screen as needed
                                            val intent = Intent(context, MainActivity::class.java)
                                            context.startActivity(intent)
                                        } else {
                                            Toast.makeText(context, "Failed to update password", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Incorrect security question or answer", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                    }, modifier = Modifier.width(200.dp)) {
                        Text(text = "SAVE")

                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "login", modifier = Modifier.clickable {
                        val intent= Intent(context, MainActivity::class.java)
                         context.startActivity(intent)
                    })
                }
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    Recover()

}