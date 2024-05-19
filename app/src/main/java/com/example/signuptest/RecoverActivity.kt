package com.example.signuptest

import android.content.Intent
import android.os.Bundle
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
import com.example.signuptest.ui.theme.SignupTestTheme

class RecoverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Recover()

        }
    }
}

@Composable
fun Recover(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3)
            , contentDescription =null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .wrapContentSize()
                .padding(30.dp), elevation = 20.dp, shape = RoundedCornerShape(20.dp)
            ) {
                val context= LocalContext.current
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
                Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Recover Account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value = question, onValueChange ={question=it}
                         , label = {
                            Text(text = "question")
                        } , placeholder = {
                            Text(text = "question")
                        })
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
                                     Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show()
                        val intent= Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
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