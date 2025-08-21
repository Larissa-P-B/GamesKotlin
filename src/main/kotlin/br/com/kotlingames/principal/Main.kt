package br.com.kotlingames.principal

import br.com.kotlingames.service.ConsumoAPI
import br.com.kotlingames.modelo.Games
import br.com.kotlingames.modelo.Usuario
import transformarEmIdade
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Usuario.criarGamer(leitura)
    println("Cadastro concluido com sucesso.Dados do usuario")
    println(gamer)
    println("Idade do Usuario: " + gamer.dataNascimento?.transformarEmIdade())


    do {
        println("Digite um código de jogo para buscar:")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoAPI()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        if (informacaoJogo != null) {
            // Se a busca retornar uma InfoJogo válida
            val meuJogo = Games(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )

            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo: ")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo.descricao = descricaoPersonalizada
            } else {
                meuJogo.descricao = meuJogo.titulo
            }

            gamer.jogosBuscados.add(meuJogo)

        } else {
            // Se a busca retornar null, significa que o jogo não foi encontrado
            println("Erro: Não foi possível buscar o jogo. Verifique o ID.")
        }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()
    }while (resposta.equals("s",true))

    println("Jogos Buscados")

    println(gamer.jogosBuscados)

    println("\nJogos ordenados por titulo: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman",true) ?: false
    }
    println("\nJogos Filtrados:")
    println(jogosFiltrados)

    println("Deseja excluir um jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s",true)){
        println(gamer.jogosBuscados)
        println("Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Lista atualizada: ")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso!!")
}