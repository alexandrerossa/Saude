package pt.ipg.saude

import androidx.fragment.app.Fragment

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        lateinit var listaDoutorFragment: ListaDoutorFragment
        lateinit var fragment: Fragment

        var doutorSelecionado : Doutor? = null
    }
}