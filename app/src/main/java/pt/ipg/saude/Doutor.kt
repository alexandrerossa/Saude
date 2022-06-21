package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Doutor (
    var nome_doutor: String,
    var dataNascimento: String,
    var especialidade: String,
    var id: Long = -1
    ) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

            valores.put(TabelaDoutores.CAMPO_NOME_DOUTOR, nome_doutor)
            valores.put(TabelaDoutores.CAMPO_DATA_DE_NASCIMENTO, dataNascimento)
            valores.put(TabelaDoutores.CAMPO_ESPECIALIDADE, especialidade)


        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Doutor {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNomeDoutor = cursor.getColumnIndex(TabelaDoutores.CAMPO_NOME_DOUTOR)
            val posDataNascimento = cursor.getColumnIndex(TabelaDoutores.CAMPO_DATA_DE_NASCIMENTO)
            val posEspecialidade = cursor.getColumnIndex(TabelaDoutores.CAMPO_ESPECIALIDADE)

            val id = cursor.getLong(posId)
            val nomedoutor = cursor.getString(posNomeDoutor)
            val dataNascimento = cursor.getString(posDataNascimento)
            val especialidade = cursor.getString(posEspecialidade)

            return Doutor(nomedoutor, dataNascimento, especialidade,id)
        }
    }
}