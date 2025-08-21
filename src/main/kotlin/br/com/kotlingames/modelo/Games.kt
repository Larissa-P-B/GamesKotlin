package br.com.kotlingames.modelo

data class Games(val titulo: String,
            val capa: String ) {

    var descricao: String? = null
    override fun toString(): String {
        return "Meu Jogo: \n" +
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao\n"
    }


}


