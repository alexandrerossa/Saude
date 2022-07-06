package pt.ipg.saude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.saude.databinding.FragmentNovoDoutorBinding
import pt.ipg.saude.ContentProviderSaude

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NovoDoutorFragment : Fragment() {

    private var _binding: FragmentNovoDoutorBinding? = null

    private lateinit var editTextNome: EditText
    private lateinit var editTextDataNascimento: EditText
    private lateinit var editTextEspecialidade: EditText

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_doutor


        _binding = FragmentNovoDoutorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextDoutorNome)
        editTextDataNascimento = view.findViewById(R.id.editTextDoutorDataNascimento)
        editTextEspecialidade = view.findViewById(R.id.editTextDoutorEspecialidade)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaDoutor() {
        findNavController().navigate(R.id.action_ListaDoutorFragment_toNovoDoutorFragment)
    }

    fun guardar() {
        val nomeDoutor = editTextNome.text.toString()
        if (nomeDoutor.isEmpty()) {
            editTextNome.setError(getString(R.string.preencha_nome))
            return
        }

        val dataNascimentoDoutor = editTextDataNascimento.text.toString()
        if (dataNascimentoDoutor.isEmpty()) {
            editTextDataNascimento.setError(getString(R.string.preencha_dataNascimento))
            return
        }

        val especialidadeDoutor = editTextEspecialidade.text.toString()
        if (especialidadeDoutor.isEmpty()) {
            editTextEspecialidade.setError(getString(R.string.preencha_especialidade))
            return
        }


        val doutor = Doutor(nome_doutor = nomeDoutor, dataNascimento = dataNascimentoDoutor, especialidade = especialidadeDoutor)

        val uri = activity?.contentResolver?.insert(
            ContentProviderSaude.TABELA_DOUTOR_PATH,
            doutor.toContentValues()
        )

        if (uri == null) {
            Snackbar.make(
                editTextNome,
                getString(R.string.erro_inserir_doutor),
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        navegaListaDoutor()
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