package com.example.signuptest

import android.app.Application
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

import com.example.signuptest.ui.theme.UserDatabase
import com.example.signuptest.ui.theme.UserViewModel
import com.example.signuptest.ui.theme.Users
//import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignPage : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUp(userViewModel)

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUp(userViewModel: UserViewModel){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3)
            , contentDescription =null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
            val context= LocalContext.current
            val db =UserDatabase.getDatabase(context)
            val userDao = db.userDao()
            val coroutineScope = rememberCoroutineScope()
            var surnameText by remember{
                mutableStateOf("")
            }
            var namesText by remember{
                mutableStateOf("")
            }
            var userNameText by remember{
                mutableStateOf("")
            }
            var passwordText by remember{
                mutableStateOf("")
            }
            var confirmPasswordText by remember{
                mutableStateOf("")
            }
            var emailText by remember{
                mutableStateOf("")
            }
            var phoneText by remember{
                mutableStateOf("")
            }
            var questionText by remember{
                mutableStateOf("")
            }
            var answerText by remember{
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


            Card(modifier = Modifier
                .fillMaxSize()
                .padding(30.dp) , elevation = 20.dp, shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Register", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = surnameText, onValueChange ={surnameText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription =null )
                        } , label = {
                            Text(text = "surname")
                        } , placeholder = {
                            Text(text = "surname")
                        })
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = namesText, onValueChange ={namesText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, label = {
                            Text(text = "other names")
                        } , placeholder = {
                            Text(text = "other names")
                        } )
                    OutlinedTextField(value = userNameText, onValueChange ={userNameText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription =null )
                        }, label = {
                            Text(text = "username")
                        } , placeholder = {
                            Text(text = "username")
                        } )
                    OutlinedTextField(value = passwordText, onValueChange ={passwordText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, label = {
                            Text(text = "password")
                        } , placeholder = {
                            Text(text = "password")
                        } )
                    OutlinedTextField(value = confirmPasswordText, onValueChange ={confirmPasswordText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, label = {
                            Text(text = "confirm password")
                        } , placeholder = {
                            Text(text = "confirm password")
                        } )
                    OutlinedTextField(value = emailText, onValueChange ={emailText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_email_24), contentDescription =null )
                        }, label = {
                            Text(text = "email")
                        } , placeholder = {
                            Text(text = "email")
                        } )
                    OutlinedTextField(value = phoneText, onValueChange ={phoneText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_local_phone_24), contentDescription =null )
                        }, label = {
                            Text(text = "phone number")
                        } , placeholder = {
                            Text(text = "phone number")
                        } )
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
                    OutlinedTextField(value = answerText, onValueChange ={answerText=it}
                        , label = {
                            Text(text = "answer")
                        } , placeholder = {
                            Text(text = "answer")
                        } )

                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {
                        if (surnameText.isNotEmpty() && namesText.isNotEmpty() && userNameText.isNotEmpty()
                            && passwordText.isNotEmpty() && confirmPasswordText.isNotEmpty() && emailText.isNotEmpty()
                            && phoneText.isNotEmpty() && questionText.isNotEmpty() && answerText.isNotEmpty()
                        ) {
                            if (passwordText == confirmPasswordText && passwordText.length > 6) {
                                val user = Users(
                                    surname = surnameText,
                                    otherNames = namesText,
                                    userName = userNameText,
                                    password = passwordText,
                                    email = emailText,
                                    phone = phoneText,
                                    question = questionText,
                                    answer = answerText
                                )
                                coroutineScope.launch {
                                    userViewModel.addUser(user)
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Passwords do not match or are too short", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }

                    }, modifier = Modifier.width(200.dp)) {
                        Text(text = "SIGNUP")

                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "already have an account", modifier = Modifier.clickable {
                        val intent=Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    })


                }



            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SignUp(UserViewModel(LocalContext.current.applicationContext as Application))

}