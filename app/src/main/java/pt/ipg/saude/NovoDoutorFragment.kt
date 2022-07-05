package pt.ipg.saude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipg.saude.databinding.FragmentNovoDoutorBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NovoDoutorFragment : Fragment() {

    private var _binding: FragmentNovoDoutorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.novoDoutorFragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_doutor


        _binding = FragmentNovoDoutorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaDoutor() {
        findNavController().navigate(R.id.action_ListaDoutorFragment_toNovoDoutorFragment)
    }

    fun guardar() {
        // todo: guardar enfermeiro
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_doutor -> guardar()
            R.id.action_cancelar_novo_doutor -> navegaListaDoutor()
            else -> return false
        }

        return true
    }
}