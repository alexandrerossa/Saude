package pt.ipg.saude

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        lateinit var listaDoutorFragment: ListaDoutorFragment
        var novoDoutorFragment: NovoDoutorFragment? = null
        var doutorSelecionado : Doutor? = null
    }
}