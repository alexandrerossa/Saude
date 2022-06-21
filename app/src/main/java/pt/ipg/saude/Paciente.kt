package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Paciente (var nome: String, var dataNascimento: String, var sexo: String, var tipo_consulta: String, var doutor_responsavel: String,var id: Long = -1){
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
            valores.put(TabelaPacientes.CAMPO_NOME_PACIENTE, nome)
            valores.put(TabelaPacientes.CAMPO_DATA_DE_NASCIMENTO, dataNascimento)
            valores.put(TabelaPacientes.CAMPO_SEXO, sexo)
            valores.put(TabelaPacientes.CAMPO_TIPO_CONSULTA, tipo_consulta)
            valores.put(TabelaPacientes.CAMPO_DOUTOR_RESPONSAVEL, doutor_responsavel)


        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Paciente {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaPacientes.CAMPO_NOME_PACIENTE)
            val posDataNascimento = cursor.getColumnIndex(TabelaPacientes.CAMPO_DATA_DE_NASCIMENTO)
            val posSexo = cursor.getColumnIndex(TabelaPacientes.CAMPO_SEXO)
            val posTipoConsulta = cursor.getColumnIndex(TabelaPacientes.CAMPO_TIPO_CONSULTA)
            val colDoutorResponsavel = cursor.getColumnIndex(TabelaPacientes.CAMPO_DOUTOR_RESPONSAVEL)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val dataNascimento = cursor.getString(posDataNascimento)
            val sexo = cursor.getString(posSexo)
            val tipoConsulta = cursor.getString(posTipoConsulta)
            val doutorResponsavel = cursor.getString(colDoutorResponsavel)

            return Paciente( nome, dataNascimento ,sexo , tipoConsulta, doutorResponsavel,id)
        }
    }
}