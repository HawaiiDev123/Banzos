package com.mns.banzosapp.helper.deserializer

import com.google.gson.*
import com.mns.banzosapp.model.base.ResponseData
import java.lang.reflect.Type


class BaseDeserializer<T : ResponseData>(private val typeParameterClass: Class<T>) :
    JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): T {
        val jsonObject = json.asJsonObject

        val status = jsonObject.get("status").asBoolean
        val message = jsonObject.get("message").asString
        var data: JsonObject? = null

        if (jsonObject.has("data") && !jsonObject.get("data").isJsonNull && jsonObject.get("data").isJsonObject) {
            data = jsonObject.get("data").asJsonObject
        }

        // Delegate the deserialization to the context
        //Author[] authors = context.deserialize(jsonObject.get("authors"), Author[].class);
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        var dataObj: T? = null
        try {
            dataObj = typeParameterClass.newInstance()
        } catch (e: InstantiationException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        if (data != null) {
            dataObj = gson.fromJson(data, typeParameterClass)
        }

        dataObj!!.status = status
        dataObj.message = message

        return dataObj
    }
}

