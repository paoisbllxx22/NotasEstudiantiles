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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etCorreo = findViewById<EditText>(R.id.etCorreoLogin)
        val etContrasena = findViewById<EditText>(R.id.etContrasenaLogin)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnIrRegistro = findViewById<Button>(R.id.btnIrRegistro)

        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            when {
                correo.isEmpty() -> {
                    etCorreo.error = getString(R.string.error_correo)
                    etCorreo.requestFocus()
                }
                contrasena.isEmpty() -> {
                    etContrasena.error = getString(R.string.error_contrasena)
                    etContrasena.requestFocus()
                }
                else -> {
                    val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
                    val correoGuardado = prefs.getString("correo", null)
                    val contrasenaGuardada = prefs.getString("contrasena", null)
                    if (correoGuardado == null || contrasenaGuardada == null) {
                        Toast.makeText(this, getString(R.string.error_no_registrado), Toast.LENGTH_SHORT).show()
                    } else if (correo == correoGuardado && contrasena == contrasenaGuardada) {
                        Toast.makeText(this, getString(R.string.login_exitoso), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ListadoNotas::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnIrRegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}