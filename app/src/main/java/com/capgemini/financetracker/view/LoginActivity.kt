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
import com.capgemini.personalfinanacetracker.model.FinancialDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button

    lateinit var emailidEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var repo:FinancialDataRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.button2)

        emailidEditText = findViewById(R.id.EmailAddressE)
        passwordEditText = findViewById(R.id.PasswordE)

        repo=FinancialDataRepository(this)


        loginOnClick()


    }
    private fun loginOnClick() {

        fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS
            .matcher(this).matches()




        loginButton.setOnClickListener {
            //lambda expression is executed once the click event done
            Log.d("Main Activity", "Login clicked")

            val emailid = emailidEditText.text.toString()
            val password = passwordEditText.text.toString()
            val validatepassword = password.length >= 8 && password.length <= 16

            if (!validatepassword) {
                Toast.makeText(
                    this, "The password should be minimum of 8 digits and maximum of 16 digits",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (!emailid.isValidEmail()) {
                Toast.makeText(this, "Please enter a valid EmailId", Toast.LENGTH_LONG).show()
            }


            if (emailid.isValidEmail() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.Default).launch {
                    Log.d("LoginActivity", "first")

                    try {
                        repo.getPersonEmailWithException("$emailid", password.toInt())
                        CoroutineScope(Dispatchers.Main).launch{

                            Log.d("LoginActivity", "second")
                            val intent=Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                    } catch (err: Exception) {
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                this@LoginActivity,
                                "Invalid EmailId or Password",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("LoginActivity", "third")

                        }

                    }
                }
            } else {
                Toast.makeText(
                    this, "pls enter the fields",
                    Toast.LENGTH_LONG
                ).show()
            }
            emailidEditText.text.clear()
            passwordEditText.text.clear()


        }
    }

    fun regClick(view: View) {
        val regIntent= Intent(this@LoginActivity, RegistrationActivity::class.java)
        startActivity(regIntent)
    }
}