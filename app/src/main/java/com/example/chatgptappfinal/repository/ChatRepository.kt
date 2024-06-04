package com.example.chatgptappfinal.repository

import com.example.chatgptappfinal.viewmodel.ChatApi
import com.example.chatgptappfinal.viewmodel.ChatRequest

class ChatRepository(private val api: ChatApi) {
    suspend fun getChatResponse(text: String): String {
        val response = api.getChatResponse(ChatRequest(text))
        return response.choices[0].text
    }
}