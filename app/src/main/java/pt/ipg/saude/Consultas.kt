package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Consultas (var id: Long = -1, var nome: String, var ocupadas: String, var disponiveis: String){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaConsultas.CAMPO_NOME_CONSULTA, nome)
            put(TabelaConsultas.CAMPO_MACAS_OCUPADAS, ocupadas)
            put(TabelaConsultas.CAMPO_MACAS_DISPONIVEIS, disponiveis)
        }

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Consultas {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaConsultas.CAMPO_NOME_CONSULTA)
            val colMacasOcupadas = cursor.getColumnIndex(TabelaConsultas.CAMPO_MACAS_OCUPADAS)
            val colMacasDisponiveis = cursor.getColumnIndex(TabelaConsultas.CAMPO_MACAS_DISPONIVEIS)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val macasOcupadas = cursor.getString(colMacasOcupadas)
            val macasDisponiveis = cursor.getString(colMacasDisponiveis)

            return Consultas(id, nome, macasOcupadas, macasDisponiveis)
        }
    }
}