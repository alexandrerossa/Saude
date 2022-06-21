package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Consultas (var nome: String, var macasocupadas: Int, var macasdisponiveis: Int, var id: Long = -1){
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
            valores.put(TabelaConsultas.CAMPO_NOME_CONSULTA, nome)
            valores.put(TabelaConsultas.CAMPO_MACAS_OCUPADAS, macasocupadas)
            valores.put(TabelaConsultas.CAMPO_MACAS_DISPONIVEIS, macasdisponiveis)


        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Consultas {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaConsultas.CAMPO_NOME_CONSULTA)
            val posMacasOcupadas = cursor.getColumnIndex(TabelaConsultas.CAMPO_MACAS_OCUPADAS)
            val posMacasDisponiveis = cursor.getColumnIndex(TabelaConsultas.CAMPO_MACAS_DISPONIVEIS)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val macasOcupadas = cursor.getInt(posMacasOcupadas)
            val macasDisponiveis = cursor.getInt(posMacasDisponiveis)

            return Consultas(nome, macasOcupadas, macasDisponiveis, id)
        }
    }
}