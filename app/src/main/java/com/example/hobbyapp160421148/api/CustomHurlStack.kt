package com.example.hobbyapp160421148.api

import android.annotation.SuppressLint
import com.android.volley.toolbox.HurlStack
import java.net.HttpURLConnection
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@SuppressLint("CustomX509TrustManager")
class CustomHurlStack : HurlStack() {
    override fun createConnection(url: URL): HttpURLConnection {
        val connection = super.createConnection(url)
        if (connection is HttpsURLConnection) {
            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, arrayOf<TrustManager>(object : X509TrustManager {
                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                }), null)

                connection.sslSocketFactory = sslContext.socketFactory
                connection.hostnameVerifier = HostnameVerifier { _, _ -> true }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return connection
    }
}