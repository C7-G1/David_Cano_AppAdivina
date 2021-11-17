    package david.cano.david_cano_appadivina;
    
    import androidx.appcompat.app.AppCompatActivity;
    
    import android.content.Context;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.TextView;
    
    import org.w3c.dom.Text;
    
    import java.util.Random;
    
    public class MainActivity extends AppCompatActivity {
        EditText et = null;
        TextView tInicio = null;
        int numeroAdivinar = 0;
        int numJugado = 0;
        int numIntentos = 0;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            numeroAdivinar();
            et = (EditText) findViewById(R.id.textoBoton);
            tInicio = (TextView) findViewById(R.id.textView);
    
            et.setOnKeyListener(new android.view.View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
                    // Han pulsado (o soltado) una tecla…
                    if ((event.getAction() == android.view.KeyEvent.ACTION_DOWN) && (keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {
                        //Aquí puedes llamar al método que llamabas en el onClick del botón.
                        return true;
                    } else
                        return false;
                } // onKey
            });
    
        }
        private void numeroAdivinar(){
            Random dado = new Random();
            numeroAdivinar = dado.nextInt(100) + 1;
        }
    
        public void probar(View v) {
            String mensajeFinal;
            String mensaje;
            numIntentos++;
            numJugado = Integer.parseInt(et.getText().toString());
            if (numJugado > numeroAdivinar) {
                mensaje = getResources().getString(R.string.textoMenor);
                mensajeFinal = String.format(mensaje, numJugado);
    
            } else if (numJugado < numeroAdivinar) {
                mensaje = getResources().getString(R.string.textoMayor);
                mensajeFinal = String.format(mensaje, numJugado);
            } else {
                mensajeFinal = getResources().getString(R.string.textoIgual);
                finalPartida();
            }
            tInicio.setText(mensajeFinal);
            actualizarIntentos();
        }
    
        public void volverJugar(View v2) {
            View bJugarDeNuevo = findViewById(R.id.btReintentar);
            bJugarDeNuevo.setVisibility(View.INVISIBLE);
            numIntentos=0;
            numeroAdivinar();
            View bTaparFuncionText = findViewById(R.id.textoBoton);
            bTaparFuncionText.setVisibility(View.VISIBLE);
            View bTaparBoton = findViewById(R.id.button);
            bTaparFuncionText.setVisibility(View.VISIBLE);
            TextView tv;
            tv = (TextView) findViewById(R.id.textView);
            tv.setText(R.string.textoInicio);
            tv = (TextView) findViewById(R.id.textIntento);
            tv.setText("");
        }
    
        private void actualizarIntentos() {
            String mensaje;
            mensaje = getResources().getQuantityString(R.plurals.intentos, numIntentos, numIntentos);
            TextView tv;
            tv = (TextView) findViewById(R.id.textIntento);
            tv.setText(mensaje);
        }
    
        private void finalPartida() {
            // Hacemos visible el botón de jugar de nuevo...
            View bJugarDeNuevo = findViewById(R.id.btReintentar);
            bJugarDeNuevo.setVisibility(View.VISIBLE);
            // ... e invisible el cuadro de texto, el botón de "Probar"
            View bTaparFuncionText = findViewById(R.id.textoBoton);
            bTaparFuncionText.setVisibility(View.INVISIBLE);
            View bTaparBoton = findViewById(R.id.button);
            bTaparFuncionText.setVisibility(View.INVISIBLE);
            // y la etiqueta número de intentos.
        }
    }