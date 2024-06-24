package com.example.siperpus.config

import com.example.siperpus.buku.Buku
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MyModelDeserializer : JsonDeserializer<ArrayList<Buku>> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ArrayList<Buku> {
        val arrayList = ArrayList<Buku>()
        if (json.isJsonArray) {
            json.asJsonArray.forEach { element ->
                arrayList.add(context.deserialize(element, Buku::class.java))
            }
        } else {
            arrayList.add(context.deserialize(json, Buku::class.java))
        }
        return arrayList
    }
}