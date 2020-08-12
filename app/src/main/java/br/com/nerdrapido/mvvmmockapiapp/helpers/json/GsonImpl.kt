package br.com.nerdrapido.mvvmmockapiapp.helpers.json

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class GsonImpl : JsonMapper {

    private val gson = Gson()

    override fun <T> fromString(jsonString: String, classOf: Class<T>): T {
        return gson.fromJson(jsonString, classOf)
    }

    override fun <T> fromString(jsonString: String, type: Type): T {
        return gson.fromJson(jsonString, type)
    }

    override fun toString(entity: Any): String {
        return gson.toJson(entity)
    }


}