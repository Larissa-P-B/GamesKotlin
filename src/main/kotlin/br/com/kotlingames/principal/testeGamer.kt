import br.com.kotlingames.modelo.Usuario

fun main(){
    val gamer1 = Usuario("Jacque","jacque@email.com")
    println(gamer1)

    val gamer2 = Usuario("Jeni",
        "jeni@email.com",
        "19/19/1992"
        ,"fweefewf")


    println(gamer2)


    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "jacqueskywalker"

    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "jacque"
    println(gamer1)
}