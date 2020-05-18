package com.smartherd.autocomplete.biometric


import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.UserNotAuthenticatedException
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.smartherd.autocomplete.R
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.KeyStore
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class BiometricImpl  :AppCompatActivity(){

    private var x = "Hi I am Biometric"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biometric_layout)

        // to see device support the biometric Hardware use BiometricManager
        val biometricManager = BiometricManager.from(this)
        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS){
            Toast.makeText(this,"Can use Biometric",Toast.LENGTH_LONG).show()
        }

//        when (biometricManager.canAuthenticate()) {
//            BiometricManager.BIOMETRIC_SUCCESS -> authUser(executor)
//            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Toast.makeText( this,
//                               getString(R.string.error_msg_no_biometric_hardware), Toast.LENGTH_LONG).show()
//            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->Toast.makeText( this,
//                    getString(R.string.error_msg_biometric_hw_unavailable),Toast.LENGTH_LONG).show()
//            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Toast.makeText( this,
//                    getString(R.string.error_msg_biometric_not_setup),Toast.LENGTH_LONG ).show()
//        }

        // got the refrence to Executor that perform tasks n th emain thread
       val executor = ContextCompat.getMainExecutor(this)
       // or    val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity =this
        

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
               Toast.makeText(this@BiometricImpl,"$errorCode :$errString",Toast.LENGTH_LONG).show()
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    // user clicked negative button
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@BiometricImpl,"Authentication failed for an unknown reason",Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
//                val plaintext ="X"
//                val string = "Y"
//                val encryptedInfo: ByteArray? = result.cryptoObject?.cipher?.doFinal(
//                  plaintext-string.toByteArray(Charset.defaultCharset()))
//                Log.d("MY_APP_TAG", "Encrypted information: " + Arrays.toString(encryptedInfo))
                Toast.makeText(this@BiometricImpl,"Authentication was successful",Toast.LENGTH_LONG).show()

            }

        }

        val biometricPrompt=BiometricPrompt(activity,executor,callback)



        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication Required!")
            .setSubtitle("Important authentication")
            .setDescription("Please authenticate to be able to view your account information")
            .setDeviceCredentialAllowed(true)
            //.setNegativeButtonText("Please Confirm")
            .setConfirmationRequired(true)
            .build()



        findViewById<Button>(R.id.authenticateButton ).setOnClickListener {
           biometricPrompt.authenticate(promptInfo)
//            val cipher = getCipher()
//            val secretKey = getSecretKey()
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
//            biometricPrompt.authenticate(promptInfo,
//                BiometricPrompt.CryptoObject(cipher))
            //The first parameter is the name of the preference file.
            // The second is the operation mode and is set to Context.MODE_PRIVATE so only this app can access this file.
//            val sharedPreferences: SharedPreferences =
//                getSharedPreferences("user_data", Context.MODE_PRIVATE)

            //1 You call this method when you want to begin to presist your data.
//            val editor = sharedPreferences.edit()
//             //2 ou use this when you want to save a String data type inside shared peferences. This takes two parameters.
//            // The  first parameter is the name of the value you want store while the second is the value you want to store under this name.
//            editor.putString("Name", etName.text.toString())
//            editor.putString("Email", etEmail.text.toString())
//            editor.putString("Phone", etPhone.text.toString())
//           //3  you call this when you want to save values inside
//            editor.apply()
//            //4 clear the screen
//            etName.setText("")
//            etEmail.setText("")
//            etPhone.setText("")
//
       }
//
////This takes two parameters: first is the name of the value that you want
//// to retrieve, while the second parameter is a default value that you provide in case the original value is empty.
//        etName.setText(sharedPreferences.getString("Name", ""))
//        etEmail.setText(sharedPreferences.getString("Email", ""))
//        etPhone.setText(sharedPreferences.getString("Phone", ""))

    }

    private fun authUser(executor: Executor) {
        //choose androidx.biometric
//        val promptInfo = BiometricPrompt.PromptInfo.Builder()
// 2 Gives the dialog a title.
//            .setTitle(getString(R.string.auth_title))
// 3 Assigns the dialog a subtitle that is placed below the main title.
//            .setSubtitle(getString(R.string.auth_subtitle))
// 4 Describes this dialog so the end-user understands what it is and the function behind it.
//            .setDescription(getString(R.string.auth_description))
// 5 Use this to let a user whose device doesn’t support fingerprint authentication use a password or a pattern instead.
//            .setDeviceCredentialAllowed(true)
// 6 Call this to build the dialog with the properties you specified above.
//            .build()

//        // 1
//        val biometricPrompt = BiometricPrompt(this, executor,
//            object : BiometricPrompt.AuthenticationCallback() {
//                // 2
//                override fun onAuthenticationSucceeded(
//                    result: BiometricPrompt.AuthenticationResult
//                ) {
//                    super.onAuthenticationSucceeded(result)
//                    main_layout.visibility = View.VISIBLE
//                }
//                // 3
//                override fun onAuthenticationError(
//                    errorCode: Int, errString: CharSequence
//                ) {
//                    super.onAuthenticationError(errorCode, errString)
//                    Toast.makeText(
//                        applicationContext,
//                        getString(R.string.error_msg_auth_error, errString),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                // 4
//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//                    Toast.makeText(applicationContext,
//                        getString(R.string.error_msg_auth_failed),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })

    }

//   private val KEY_NAME ="android_key"
//
//    // use the cryptographic solution
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun generateSecretKey(keyGenParameterSpec: KeyGenParameterSpec) {
//        val keyGenerator = KeyGenerator.getInstance(
//            KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
//        keyGenerator.init(keyGenParameterSpec)
//        keyGenerator.generateKey()
//    }
//
//    private fun getSecretKey(): SecretKey {
//        val keyStore = KeyStore.getInstance("AndroidKeyStore")
//        // Before the keystore can be accessed, it must be loaded.
//        keyStore.load(null)
//        return keyStore.getKey(KEY_NAME, null) as SecretKey
//    }
//
//    private fun getCipher(): Cipher {
//        return Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
//                + KeyProperties.BLOCK_MODE_CBC + "/"
//                + KeyProperties.ENCRYPTION_PADDING_PKCS7)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    val x1 =generateSecretKey(KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
//    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
//    .setUserAuthenticationRequired(true)
//        .setUserAuthenticationValidityDurationSeconds(60)
//    .build())
//
//    private fun encryptSecretInformation() {
//        // Exceptions are unhandled for getCipher() and getSecretKey().
//        val cipher = getCipher()
//        val secretKey = getSecretKey()
//        try {
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
//            val encryptedInfo: ByteArray = cipher.doFinal(
//                plaintext-string.toByteArray(Charset.defaultCharset()))
//            Log.d("MY_APP_TAG", "Encrypted information: " +
//                    Arrays.toString(encryptedInfo))
//        } catch (e: InvalidKeyException) {
//            Log.e("MY_APP_TAG", "Key is invalid.")
//        } catch (e: UserNotAuthenticatedException) {
//            Log.d("MY_APP_TAG", "The key's validity timed out.")
//            biometricPrompt.authenticate(promptInfo)
//        }

}


