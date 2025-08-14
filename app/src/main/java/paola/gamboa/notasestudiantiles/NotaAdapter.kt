package paola.gamboa.notasestudiantiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(private val notas: List<Nota>) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {
    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvContenidoCorto: TextView = itemView.findViewById(R.id.tvContenidoCorto)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.tvTitulo.text = nota.titulo
        holder.tvContenidoCorto.text = nota.contenidoCorto
        holder.tvFecha.text = nota.fecha
    }

    override fun getItemCount(): Int = notas.size
}
