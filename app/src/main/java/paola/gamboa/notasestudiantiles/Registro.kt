package paola.gamboa.notasestudiantiles


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRegresarLogin = findViewById<Button>(R.id.btnRegresarLogin)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            when {
                nombre.isEmpty() -> {
                    etNombre.error = getString(R.string.error_nombre)
                    etNombre.requestFocus()
                }
                correo.isEmpty() -> {
                    etCorreo.error = getString(R.string.error_correo)
                    etCorreo.requestFocus()
                }
                contrasena.isEmpty() -> {
                    etContrasena.error = getString(R.string.error_contrasena)
                    etContrasena.requestFocus()
                }
                else -> {
                    // Guardar en SharedPreferences
                    val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("nombre", nombre)
                    editor.putString("correo", correo)
                    editor.putString("contrasena", contrasena)
                    editor.apply()

                    Toast.makeText(this, getString(R.string.registro_exitoso), Toast.LENGTH_SHORT).show()

                    // Ir a Login
                    val intent = Intent(this, Login::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

        btnRegresarLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}