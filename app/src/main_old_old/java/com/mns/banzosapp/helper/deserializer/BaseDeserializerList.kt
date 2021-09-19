package com.mns.banzosapp.helper.deserializer

/**
 * Created by RJ on 08-01-2015.
 */

import com.google.gson.*
import com.mns.banzosapp.model.base.ResponseData
import java.lang.reflect.Type
import java.util.*


class BaseDeserializerList<T : ResponseData, U>(
    internal val typeParameterClass: Class<T>,
    internal val st: Class<U>
) :
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
        // JsonObject data = null;
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        var dataObj: U? = null
        try {
            dataObj = st.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        var tObj: T? = null
        try {
            tObj = typeParameterClass.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        val mapValues = TreeMap<Int, U>()
        if (jsonObject.has("data") && !jsonObject.get("data").isJsonNull) {

            if (jsonObject.get("data").isJsonArray) {

                val arrayJson = jsonObject.get("data").asJsonArray

                for (j in 0 until arrayJson.size()) {

                    val itemData = arrayJson.get(j)
                    dataObj = gson.fromJson(itemData.asJsonObject, st)
                    mapValues[j] = dataObj!!
                }
            } else {

                val entries = jsonObject.getAsJsonObject("data").entrySet()

                for ((key, itemData) in entries) {

                    dataObj = gson.fromJson(itemData.asJsonObject, st)
                    mapValues[Integer.parseInt(key)] = dataObj!!
                }
            }
            tObj!!.status = status
            tObj.message = message
            tObj.data = LinkedList(mapValues.values)

        } else {
            mapValues[1] = dataObj!!
            tObj!!.status = status
            tObj.message = message
            tObj.data = LinkedList(mapValues.values)
        }
        return tObj
    }

}

