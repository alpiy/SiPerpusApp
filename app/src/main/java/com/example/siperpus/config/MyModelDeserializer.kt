package com.example.siperpus.config

import com.example.siperpus.buku.Buku
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MyModelDeserializer : JsonDeserializer<List<Buku>> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<Buku> {
        val resultList = mutableListOf<Buku>()
        if (json.isJsonObject) {
            // Jika respons JSON adalah objek tunggal, kita bisa memprosesnya di sini
            val myModel = context.deserialize<Buku>(json, Buku::class.java)
            resultList.add(myModel)
        } else if (json.isJsonArray) {
            // Jika respons JSON adalah array, iterasi setiap elemen dan tambahkan ke resultList
            json.asJsonArray.forEach { element ->
                val myModel = context.deserialize<Buku>(element, Buku::class.java)
                resultList.add(myModel)
            }
        }

        return resultList
    }
}