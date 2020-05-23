package com.saurabh.yourworld.login

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.saurabh.yourworld.MainActivity
import com.saurabh.yourworld.R
import com.saurabh.yourworld.signup.SignupActivity
import kotlinx.android.synthetic.main.login_screen.*
import java.util.*
import java.util.concurrent.Executors

class LoginActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private val MY_REQUEST_CODE:Int =7117
    private var mDelayHandler: Handler? = null
     var  providers= Arrays.asList<AuthUI.IdpConfig>(
         AuthUI.IdpConfig.EmailBuilder().build(),
//         AuthUI.IdpConfig.FacebookBuilder().build(),
         AuthUI.IdpConfig.GoogleBuilder().build(),
         AuthUI.IdpConfig.PhoneBuilder().build()
     )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        auth= FirebaseAuth.getInstance()



        showsignoptions()

        val signup = findViewById(R.id.signup) as TextView
        val email=findViewById(R.id.email) as EditText
        val password=findViewById(R.id.password) as EditText
        val login=findViewById(R.id.login)as Button

        showsignoptions()

        login.setOnClickListener {
            doLogin()
        }


        signup.setOnClickListener {

            var intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        navclick.setOnClickListener{


            val executor = Executors.newSingleThreadExecutor()
            val activity: FragmentActivity = this // reference to activity

            val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        // user clicked negative button
                        Toast.makeText(applicationContext, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
                    }
//                    else {
//
//                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                   // Toast.makeText(applicationContext, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    return
                }
            })

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Enter phone screen lock patter, PIN , password or fingerprint.")
                .setSubtitle("Unlock Your World")
//            .setDescription("Touch the fingerprint sensor")
                .setNegativeButtonText("Cancel")
                .build()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                this.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
               // Toast.makeText(this,"hi higher version out there ",Toast.LENGTH_LONG).show()
            }
            biometricPrompt.authenticate(promptInfo)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {

                val user=FirebaseAuth.getInstance().currentUser
                updateUI(user)
            }
        }
    }

    private fun showsignoptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(),MY_REQUEST_CODE
        )
    }

    private fun doLogin() {

        if(email.text.toString().isEmpty()){
            email.error="Please Enter the Email."
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error="Please Enter the Valid Email Address."
            email.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()){
            password.error="Please Enter the Password."
            password.requestFocus()
        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    Log.d( "UserName: ","$user")
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }



//    public override fun onStart() {
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }

   private fun updateUI(currentUser: FirebaseUser?){

        if(currentUser!=null){
            if(currentUser.isEmailVerified){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
//            else{
//                Toast.makeText(baseContext, "Please verify your Email Address.",
//                    Toast.LENGTH_SHORT).show()
//            }

        }else{
            Log.w("TAG", "signInWithEmail:failure")
            Toast.makeText(baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }

       val intent=Intent(this,MainActivity::class.java)
       startActivity(intent)
       finish()
    }


}