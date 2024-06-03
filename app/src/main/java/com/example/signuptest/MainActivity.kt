package com.example.signuptest

import android.content.Context
import android.content.Intent
import android.os.Bundle
//import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
//import com.example.signuptest.ui.theme.SignupTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()

        }
    }
}

@Composable
fun Test(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3)
            , contentDescription =null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .wrapContentSize()
                .padding(30.dp), elevation = 20.dp, shape = RoundedCornerShape(20.dp)
            ) {
                val context= LocalContext.current
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
                        })
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = passText, onValueChange ={passText=it}
                        , leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, label = {
                            Text(text = "password")
                        } , placeholder = {
                            Text(text = "password")
                        } )
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(modifier = Modifier.wrapContentWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange ={checkText=it} )
                        Text(text = "Remember me?")

                    }
                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick = {
                        validate(context, userText, passText)
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

fun validate(context: Context, userText: String, passText: String) {
    when {
        userText.isEmpty() -> {
            Toast.makeText(context, "Username cannot be empty", Toast.LENGTH_SHORT).show()
        }
        passText.isEmpty() -> {
            Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
        }
        passText.length < 6 -> {
            Toast.makeText(context, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Test()

}