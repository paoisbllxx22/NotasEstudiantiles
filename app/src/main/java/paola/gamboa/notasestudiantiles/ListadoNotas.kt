package paola.gamboa.notasestudiantiles


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListadoNotas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_notas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listaNotas = listOf(
            Nota(1, "Examen AppMov", "Repaso para el parcial", "10/07/2025"),
            Nota(2, "Usabilidad y Acces.", "Enviar informe antes del viernes", "14/08/2025"),
            Nota(3, "Business Intelligence ", "Enviar Lab de ELT", "15/08/2025")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerNotas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NotaAdapter(listaNotas)

        val btnSalir = findViewById<android.widget.Button>(R.id.btnSalir)
        btnSalir.setOnClickListener {
            val intent = android.content.Intent(this, Login::class.java)
            intent.flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}