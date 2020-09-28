package br.com.angelorobson.templatemvi.view.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

var customDateAdapter: Any = object : Any() {

    var dateFormat: DateFormat? = null

    init {
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        (dateFormat as SimpleDateFormat).timeZone = TimeZone.getTimeZone("GMT")
    }

    @ToJson
    @Synchronized
    fun dateToJson(d: Date?): String? {
        return dateFormat?.format(d)
    }

    @FromJson
    @Synchronized
    @Throws(ParseException::class)
    fun dateToJson(s: String?): Date? {
        return dateFormat?.parse(s)
    }


}