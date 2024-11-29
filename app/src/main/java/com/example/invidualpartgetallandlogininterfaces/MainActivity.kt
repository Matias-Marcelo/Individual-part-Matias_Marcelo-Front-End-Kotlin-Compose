package com.example.invidualpartgetallandlogininterfaces

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.invidualpartgetallandlogininterfaces.ui.theme.InvidualPartGetAllAndLoginInterfacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InvidualPartGetAllAndLoginInterfacesTheme {
                //LoginPageForm()
                ShowAllNurses()

            }
        }
    }
}

// Lista estática de enfermeras
class Nurse(var user:String, var password:String)

val nurses = ArrayList<Nurse>().apply {
    add(Nurse("Alberto", "password1"))
    add(Nurse("Maria", "password2"))
    add(Nurse("Juan", "password3"))
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    InvidualPartGetAllAndLoginInterfacesTheme {
//        Greeting("Android")
//    }
//}



@Composable
fun LoginPageForm(){

    var loginInput by remember{
        mutableStateOf<String>(value = "")
    }

    var loginResult by remember {
        mutableStateOf<Boolean?>(null)
    }

    var passwordInput by remember {
        mutableStateOf<String>(value = "")
    }

    var whatShow by remember {

        mutableStateOf<String>(value = "Result")
    }

    var passwordIsVisible by remember{
        mutableStateOf<Boolean>(value = false)
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoginUserInput(
            loginInput = loginInput,
            onLoginChange = { loginInput = it }

        )

        LoginPasswordInput(

            passwordInput = passwordInput,
            onPasswordChange = {passwordInput = it},
            passwordIsVisible = passwordIsVisible,
            whatShow = whatShow


        )

        Button(onClick = {

            loginResult = nurses.any{it.user == loginInput && it.password == passwordInput}

        }, modifier = Modifier, colors = ButtonDefaults.buttonColors(

            containerColor = Color.Green
        )) {

            Text(text = "Login")

        }

        if (loginResult != null){
            ResultView(success = loginResult == true)
        }
    }

}


@Composable
fun LoginUserInput(
    loginInput: String,
    onLoginChange: (String) -> Unit, // Lambda para manejar el cambio
    labelId : String = "User"
)
{

    Text(text = "Login", fontSize = 28.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(value = loginInput, onValueChange = {
        onLoginChange(it)
    }, label = {

        Text(text = labelId)
    })
        Spacer(modifier = Modifier.height(16.dp))



}

@Composable
fun LoginPasswordInput(
    passwordInput: String,
    onPasswordChange: (String) -> Unit, // Lambda para manejar el cambio
    labelId: String = "Password",
    whatShow: String,
    passwordIsVisible: Boolean
){

    var visualTransformation = if(passwordIsVisible){
        VisualTransformation.None
    } else {

        PasswordVisualTransformation()

    }

    OutlinedTextField(
        value = passwordInput,
        onValueChange = {
            onPasswordChange(it)

        },
        label = {

            Text(text = "Password")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        visualTransformation = visualTransformation
    )


    Spacer(modifier = Modifier.height(16.dp))




}

@Composable
fun ResultView(success : Boolean) {


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(success){
            Text(text = "True")
        }else{
            Text(text = "False")
        }
    }
}


@Composable
fun ShowAllNurses(){


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "List of all Nurses:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(nurses){ nurse ->
                NurseItem(nurse = nurse)

            }
        }






    }




}

@Composable
fun NurseItem(nurse: Nurse) {
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {



    Text(text = "Nurse: ${nurse.user}", fontSize = 18.sp)


}
}


@Preview
@Composable
fun SimpleComposablePreview() {
    LoginPageForm()
}