package com.example.texttospeech

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.texttospeech.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tts: TextToSpeech

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this){
            if (it == TextToSpeech.SUCCESS) {

                val result = tts.setLanguage(Locale.getDefault())
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("tts", "Language not supported.")
                }
            }else {
                Log.e("tts", "Initialization failed")
            }
        }

        binding.apply {

            button.setOnClickListener {
                val text = Input.text.toString()
                if (text.isNotEmpty() && text.isNotBlank()) {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }

            }
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

}