package br.com.nerdrapido.mvvmmockapiapp.helpers.json

import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface JsonMapper {

    /**
     * Transforma a string de um json em um aentidade.
     *
     * @param jsonString é a string do json.
     * @param classOf é a classe [T] que o json deve ser mapeado.
     * @return [T] inflado com jsonString
     */
    fun <T> fromString(jsonString: String, classOf: Class<T>): T

    /**
     * Transforma a string de um json em um aentidade.
     *
     * @param jsonString é a string do json.
     * @param type é o type da classe [T] que o json deve ser mapeado.
     * @return [T] inflado com jsonString
     */
    fun <T> fromString(jsonString: String, type: Type): T

    fun toString(entity: Any): String
}
