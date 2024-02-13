package com.capgemini.financetracker.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capgemini.financetracker.R
import com.capgemini.financetracker.databinding.ActivityRegistrationBinding
import com.capgemini.financetracker.viewmodel.FinancialDataViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    lateinit var registerButton: Button
    lateinit var nameedittext: EditText
    lateinit var emailedittext: EditText
    lateinit var passwordsedittext: EditText
    lateinit var confirmpasswordedittext: EditText

    lateinit var financeVm:FinancialDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        registerButton=findViewById(R.id.registerB)
        nameedittext=findViewById(R.id.nameE)
        emailedittext=findViewById(R.id.emailE)
        passwordsedittext=findViewById(R.id.passE)
        confirmpasswordedittext=findViewById(R.id.cpassE)

        financeVm = ViewModelProvider(this).get(FinancialDataViewModel::class.java)



        registerButtonClick()

    }

    private fun registerButtonClick() {
        fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS
            .matcher(this).matches()



        registerButton.setOnClickListener {
            Log.d("Register Activity", "Register clicked")
            val personname = nameedittext.text.toString()
            val mail = emailedittext.text.toString()
            val passwords = passwordsedittext.text.toString()
            val confirmpassword = confirmpasswordedittext.text.toString()
            //val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
            val validatepassword = passwords.length >= 8 && passwords.length <= 16
            //validation for passwords...
            if (!validatepassword) {
                Toast.makeText(
                    this, "The password should be minimum of 8 digits and maximum of 16 digits",
                    Toast.LENGTH_LONG
                ).show()
            }
            if (passwords != confirmpassword) {
                Toast.makeText(
                    this, "re-enter the correct password that is given in password",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (!mail.isValidEmail()) {
                Toast.makeText(this, "Please enter a valid mail id", Toast.LENGTH_LONG).show()
            }

            if (personname.isNotEmpty() && mail.isValidEmail() && passwords.isNotEmpty() && confirmpassword.isNotEmpty() && passwords == confirmpassword) {

                CoroutineScope(Dispatchers.Default).launch {
                    //  val psonDao= FinancialDataDatabase.getInstance(this@RegistrationActivity).financeDao()
                    try {
                        financeVm.addUser(
                            personname,
                            mail,
                            passwords.toInt(),
                            confirmpassword.toInt()
                        )
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Registration s successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } catch (err: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Could not add email already exists",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                Snackbar.make(it, "Please enter all the fields", 5000).show()
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}