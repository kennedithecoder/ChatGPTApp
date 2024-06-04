package com.example.chatgptappfinal.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgptappfinal.data.ChatMessage
import com.example.chatgptappfinal.data.ChatResponse
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> get() = _messages

    private val api: ChatApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ChatApi::class.java)
    }

    fun sendMessage(text: String) {
        val userMessage = ChatMessage(text, true)
        _messages.add(userMessage)

        viewModelScope.launch {
            val response = api.getChatResponse(ChatRequest(text))
            val aiMessage = response.choices.first().text
            _messages.add(ChatMessage(aiMessage, false))
        }
    }
}

data class ChatRequest(
    val prompt: String,
    val max_tokens: Int = 150
)

interface ChatApi {
    @Headers("Authorization: Bearer ${BuildConfig.openAI_API_Key}")
    @POST("v1/engines/davinci-codex/completions")
    suspend fun getChatResponse(@Body request: ChatRequest): ChatResponse
}
