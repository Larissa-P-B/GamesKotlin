package br.com.kotlingames.service

import br.com.kotlingames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoAPI {

    fun buscaJogo(id: String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
        println(json)

        // Verifica se a resposta JSON é um array vazio ou está em branco
        if (json.trim().isBlank() || json.trim() == "[]") {
            return null
        }

        return try {
            val gson = Gson()
            gson.fromJson(json, InfoJogo::class.java)
        } catch (e: JsonSyntaxException) {
            // Se houver qualquer outro erro de sintaxe JSON, retorna null
            null
        }

    }

}