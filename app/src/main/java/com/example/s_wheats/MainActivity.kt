package com.example.s_wheats

import android.os.Bundle
import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webView)

        myWebView.settings.run {
            //Allow Javascript for the WebView App
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        myWebView.webViewClient = WebViewClient()
        myWebView.webChromeClient = object : WebChromeClient(){
            override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGusture: Boolean, resultMsg: Message): Boolean {
                val newWebView = WebView(this@MainActivity).apply{
                    webViewClient = WebViewClient()
                }

                val transport = resultMsg.obj as WebView.WebViewTransport
                transport.webView = newWebView
                resultMsg.sendToTarget()

                return true
            }
        }
        myWebView.loadUrl("https://s-wheats.github.io/front-end/") //웹뷰에 띄울 URL 정의부
    }

    override fun onBackPressed() {

        val myWebView: WebView = findViewById(R.id.webView)
        if(myWebView.canGoBack()){
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}