package com.smartherd.autocomplete.fingerprint

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

@RequiresApi(Build.VERSION_CODES.M)
class FingerprintHandler(private val appContext: Context) :
    FingerprintManager.AuthenticationCallback() {

    private var cancellationSignal: CancellationSignal? = null


    @RequiresApi(Build.VERSION_CODES.M)
    fun startAuth(
        manager: FingerprintManager,
        cryptoObject: FingerprintManager.CryptoObject
    ) {

        cancellationSignal = CancellationSignal()

        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.USE_FINGERPRINT
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(
        errMsgId: Int,
        errString: CharSequence
    ) {
        Toast.makeText(
            appContext,
            "Authentication error\n" + errString,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationHelp(
        helpMsgId: Int,
        helpString: CharSequence
    ) {
        Toast.makeText(
            appContext,
            "Authentication help\n" + helpString,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationFailed() {
        Toast.makeText(
            appContext,
            "Authentication failed.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onAuthenticationSucceeded(
        result: FingerprintManager.AuthenticationResult
    ) {

        Toast.makeText(
            appContext,
            "Authentication succeeded.",
            Toast.LENGTH_LONG
        ).show()
    }


}