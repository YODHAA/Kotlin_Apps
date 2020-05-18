package com.saurabh.yourworld.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.saurabh.yourworld.R
import com.saurabh.yourworld.login.LoginActivity
import kotlinx.android.synthetic.main.signup_screen.*


class SignupActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        auth= FirebaseAuth.getInstance()

        signup.setOnClickListener{

          signup_check()

        }

        login.setOnClickListener {
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

    }

   private  fun signup_check(){
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

       if(password.text.toString().equals(confirmpwd.text.toString()) ){

       }else{
           confirmpwd.error="Please Enter the Same Password , As Password Mismatch is there."
           confirmpwd.requestFocus()
           return
       }

       auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   var user1=auth.currentUser
                   user1!!.sendEmailVerification()
                       .addOnCompleteListener { task ->
                           if (task.isSuccessful) {
                               Log.d("Msg", "Email sent.")
                               val intent=Intent(this,LoginActivity::class.java)
                               startActivity(intent)
                               finish()
                           }
                       }
                   // Sign in success, update UI with the signed-in user's information
                   Log.d( "z","createUserWithEmail:success")
                   val user = auth.currentUser
                   Log.d( "UserName: ","$user")

                   //updateUI(user)
               } else {
                   // If sign in fails, display a message to the user.
                   Log.d("z", "createUserWithEmail:failure  ... Try Again Aftre Sometime", task.exception)
                   Toast.makeText(baseContext, "Authentication failed.",
                       Toast.LENGTH_SHORT).show()
                  // updateUI(null)
               }

           }

   }


//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }

    fun updateUI(currentUser:FirebaseUser?){

        if(currentUser!=null){
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            Log.w("TAG", "signInWithEmail:failure")
            Toast.makeText(baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }
    }

}