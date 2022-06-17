package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Paciente (var id: Long = -1, var nome: String, var dataNascimento: String, var sexo: String, var tipo_consulta: String, var doutor_responsavel: String){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaPacientes.CAMPO_NOME_PACIENTE, nome)
            put(TabelaPacientes.CAMPO_DATA_DE_NASCIMENTO, dataNascimento)
            put(TabelaPacientes.CAMPO_SEXO, sexo)
            put(TabelaPacientes.CAMPO_TIPO_CONSULTA, tipo_consulta)
            put(TabelaPacientes.CAMPO_DOUTOR_RESPONSAVEL, doutor_responsavel)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Paciente {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaPacientes.CAMPO_NOME_PACIENTE)
            val colDataNascimento = cursor.getColumnIndex(TabelaPacientes.CAMPO_DATA_DE_NASCIMENTO)
            val colSexo = cursor.getColumnIndex(TabelaPacientes.CAMPO_SEXO)
            val colTipoConsulta = cursor.getColumnIndex(TabelaPacientes.CAMPO_TIPO_CONSULTA)
            val colDoutorResponsavel = cursor.getColumnIndex(TabelaPacientes.CAMPO_DOUTOR_RESPONSAVEL)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val dataNascimento = cursor.getString(colDataNascimento)
            val sexo = cursor.getString(colSexo)
            val tipoConsulta = cursor.getString(colTipoConsulta)
            val doutorResponsavel = cursor.getString(colDoutorResponsavel)

            return Paciente(id, nome, dataNascimento ,sexo , tipoConsulta, doutorResponsavel)
        }
    }
}