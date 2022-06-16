package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Doutor (var id: Long = -1, var nome: String, var dataNascimento: String, var especialidade: Long){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaDoutores.CAMPO_NOME_DOUTOR, nome)
            put(TabelaDoutores.CAMPO_DATA_DE_NASCIMENTO, dataNascimento)
            put(TabelaDoutores.CAMPO_ESPECIALIDADE, especialidade)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Doutor {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaDoutores.CAMPO_NOME_DOUTOR)
            val colDataNascimento = cursor.getColumnIndex(TabelaDoutores.CAMPO_DATA_DE_NASCIMENTO)
            val colEspecialidade = cursor.getColumnIndex(TabelaDoutores.CAMPO_ESPECIALIDADE)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val dataNascimento = cursor.getString(colDataNascimento)
            val especialidade = cursor.getLong(colEspecialidade)

            return Doutor(id, nome, dataNascimento, especialidade)
        }
    }
}