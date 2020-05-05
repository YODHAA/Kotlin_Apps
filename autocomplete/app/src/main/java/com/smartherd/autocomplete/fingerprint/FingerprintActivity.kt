package com.smartherd.autocomplete.fingerprint

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.smartherd.autocomplete.R
import java.io.IOException
import java.lang.Exception
import java.security.*
import java.security.cert.CertificateException
import java.util.jar.Manifest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

class FingerprintActivity : AppCompatActivity() {

    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null
    private var keyStore: KeyStore? = null
    private var keyGenerator: KeyGenerator? = null
    private val KEY_NAME = "example_key"
    private var cipher: Cipher? = null
    private var cryptoObject: FingerprintManager.CryptoObject? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)

        if (getManagers()) {
            generateKey()

            if (cipherInit()) {

                cipher?.let {
                    cryptoObject = FingerprintManager.CryptoObject(it)
                }

                val helper = FingerprintHandler(this)

                if (fingerprintManager != null && cryptoObject != null) {
                    helper.startAuth(fingerprintManager!!, cryptoObject!!)
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun getManagers(): Boolean {
        keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        if (keyguardManager?.isKeyguardSecure == false) {
            Toast.makeText(
                this,
                "Lock Screen Security is Not enabled in Settings",
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.USE_FINGERPRINT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this,
                "Fingerprint authentication permission not enabled",
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        if (fingerprintManager?.hasEnrolledFingerprints() == false) {
            Toast.makeText(
                this,
                "Registered at least one fingerprint in Settings",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")

        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")

        } catch (e: Exception) {
            throw RuntimeException(
                "Failed to get KeyGenerator instance", e
            )
        } catch (e: Exception) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        }

        try {
            keyStore?.load(null)
            keyGenerator?.init(
                KeyGenParameterSpec
                    .Builder(
                        KEY_NAME,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    )
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build()
            )
            keyGenerator?.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }


    private fun cipherInit(): Boolean {
        try {
            cipher = Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7
            )
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            throw RuntimeException("Failed to get Cipher", e)
        }

        try {
            keyStore?.load(null)
            val key = keyStore?.getKey(KEY_NAME, null) as SecretKey
            cipher?.init(Cipher.ENCRYPT_MODE, key)
            return true
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        }
    }


}