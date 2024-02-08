package com.capgemini.financetracker.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.capgemini.financetracker.R
import com.capgemini.financetracker.model.Person
import com.capgemini.financetracker.model.PersonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button

    lateinit var emailidEditText: EditText
    lateinit var passwordEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton=findViewById(R.id.button2)

        emailidEditText=findViewById(R.id.editTextTextEmailAddress2)
        passwordEditText=findViewById(R.id.editTextNumberPassword)

        fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS
            .matcher(this).matches()


        loginButton.setOnClickListener{
            //lambda expression is executed once the click event done
            Log.d("Main Activity","Login clicked")

            val emailid=emailidEditText.text.toString()
            val password=passwordEditText.text.toString()
            //val pw=password.toInt()
            val validatepassword=password.length>=8&&password.length<=16

            if(!validatepassword){
                Toast.makeText(this,"The password should be minimum of 8 digits and maximum of 16 digits",
                    Toast.LENGTH_LONG).show()
            }

            if(!emailid.isValidEmail()){
                Toast.makeText(this, "Please enter a valid mail id", Toast.LENGTH_LONG).show()
            }


            if(emailid.isValidEmail()&&password.isNotEmpty()) {
                CoroutineScope(Dispatchers.Default).launch {
                    val psonDao=PersonDatabase.getInstance(this@LoginActivity).getDao()
                    Log.d("LoginActivity","first")

                    try {
                        psonDao.getpersonemailwithexception("$emailid",password.toInt())
                        withContext(Dispatchers.Main){


                            Toast.makeText(
                                this@LoginActivity,
                                "Registered mail id",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("LoginActivity", "second")
                            val intent = Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)

                        }

                    } catch (err:Exception){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Invalid emailid or password",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("LoginActivity", "third")

                        }

                    }
                }

                Toast.makeText(this, "Login is in progress $emailid",
                    Toast.LENGTH_LONG).show()
            }

            else{
                Toast.makeText(this,"pls enter the fields",
                    Toast.LENGTH_LONG).show()
            }


        }



    }

    fun regClick(view: View) {
        val regIntent= Intent(this@LoginActivity,RegistrationActivity::class.java)
        startActivity(regIntent)
    }
}