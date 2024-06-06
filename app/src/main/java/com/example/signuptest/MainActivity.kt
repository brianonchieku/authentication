package com.example.signuptest

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
//import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signuptest.ui.theme.UserDatabase
import com.example.signuptest.ui.theme.UserViewModel
import com.example.signuptest.ui.theme.Users
import com.example.signuptest.ui.theme.UsersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import com.example.signuptest.ui.theme.SignupTestTheme

class MainActivity : ComponentActivity() {
    private lateinit var database: UserDatabase
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = UserDatabase.getDatabase(this)
        setContent {
            Test(userViewModel)

        }
    }
}

@Composable
fun Test(userViewModel: UserViewModel){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3)
            , contentDescription =null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .wrapContentSize()
                .padding(30.dp), elevation = 20.dp, shape = RoundedCornerShape(20.dp)
            ) {
                val context= LocalContext.current
                val coroutineScope = rememberCoroutineScope()
                val db =UserDatabase.getDatabase(context)
                val userDao = db.userDao()
                var userText by remember{
                    mutableStateOf("")
                }
                var passText by remember{
                    mutableStateOf("")
                }
                var checkText by remember{
                    mutableStateOf(false)
                }
                Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Login", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = userText, onValueChange ={userText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription =null )
                        } , label = {
                            Text(text = "user name")
                        } , placeholder = {
                            Text(text = "user name")
                        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = passText, onValueChange ={passText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, label = {
                            Text(text = "password")
                        } , placeholder = {
                            Text(text = "password")
                        }, visualTransformation = PasswordVisualTransformation() )
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.wrapContentWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange ={checkText=it} )
                        Text(text = "Remember me?")

                    }
                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {
                        if (userText.isNotEmpty() && passText.isNotEmpty()) {
                            coroutineScope.launch {
                                // Check if the user exists in the database
                                val user = userViewModel.getUser(userText, passText)
                                userViewModel.login(userText, passText)
                                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }

                    }, modifier = Modifier.width(200.dp)) {
                        Text(text = "Login")

                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "forgot password?", modifier = Modifier.clickable {
                            val intent= Intent(context, RecoverActivity::class.java)
                            context.startActivity(intent)
                        })
                        Text(text = "Register", fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                            val intent= Intent(context, SignPage::class.java)
                            context.startActivity(intent)
                        })

                    }
                    
                }


                
            }
            
        }

    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val userViewModel = UserViewModel(Application())
    Test(userViewModel = userViewModel)

}