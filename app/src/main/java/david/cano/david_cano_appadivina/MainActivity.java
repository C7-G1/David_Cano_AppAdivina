    package david.cano.david_cano_appadivina;
    
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.DialogFragment;

    import android.app.Dialog;
    import android.content.Context;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.Random;
    
    public class MainActivity extends AppCompatActivity implements
            AccionesDialogo {
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

            et = (EditText) findViewById(R.id.tbNum);
            tInicio = (TextView) findViewById(R.id.textView);
    
            et.setOnKeyListener(new android.view.View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {

                    if ((event.getAction() == android.view.KeyEvent.ACTION_DOWN) && (keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {

                        return true;
                    } else
                        return false;
                }
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
            Log.i("InfoSolucion", String.valueOf(numeroAdivinar));
            numJugado = Integer.parseInt(et.getText().toString());

            if (numJugado > numeroAdivinar) {
                mensaje = getResources().getString(R.string.textoMenor);
                mensajeFinal = String.format(mensaje, numJugado);
    
            } else if (numJugado < numeroAdivinar) {
                mensaje = getResources().getString(R.string.textoMayor);
                mensajeFinal = String.format(mensaje, numJugado);

            } else {
                mensajeFinal = getResources().getString(R.string.textoIgual);
                mostrarMensaje(this);
                mostrarDialogo();
            }

            tInicio.setText(mensajeFinal);
            actualizarIntentos();
        }

        public void volverJugar(){
            numeroAdivinar();
            View bJugarDeNuevo = findViewById(R.id.btPrueba);
            bJugarDeNuevo.setVisibility(View.VISIBLE);
            numIntentos=0;
            View v = findViewById(R.id.tbNum);
            v.setVisibility(View.VISIBLE);

            TextView tv;
            tv = (TextView) findViewById(R.id.textView);
            tv.setText(R.string.textoInicio);
            tv = (TextView) findViewById(R.id.tvIntento);
            tv.setText("");

        }

    
        private void actualizarIntentos() {
            String mensaje;
            mensaje = getResources().getQuantityString(R.plurals.intentos, numIntentos, numIntentos);
            TextView tv;

            tv = (TextView) findViewById(R.id.tvIntento);
            tv.setText(mensaje);
        }
    
        private void finalPartida() {
            finish();
        }

        public void mostrarMensaje(Context contexto) {
            Toast toast = Toast.makeText(contexto, R.string.otra, Toast.LENGTH_LONG);
            toast.show();
        }

        public void mostrarDialogo() {
            dialogo d = new dialogo();
            d.show(getSupportFragmentManager(), "tagDialogo");
        }

        public void onDialogPositiveClick(DialogFragment dialog){
            volverJugar();
        }

        public void onDialogNegativeClick(DialogFragment dialog){
            finalPartida();

        }

    }