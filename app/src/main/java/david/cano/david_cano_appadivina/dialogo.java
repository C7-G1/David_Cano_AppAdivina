package david.cano.david_cano_appadivina;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class dialogo extends DialogFragment {
    AccionesDialogo accionesDialogo;
    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder alertaDialogo= new AlertDialog.Builder(getActivity());
        alertaDialogo.setMessage("Otra partida");
        alertaDialogo.setMessage(("Adivina mi número"));
        alertaDialogo.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int id){
                new MainActivity().mostrarMensaje(getActivity());
                accionesDialogo.onDialoguePositiveClick(dialogo.this);

            }
        });
        alertaDialogo.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                accionesDialogo.onDialogueNegativeClick(dialogo.this);
            }
        });
        return alertaDialogo.create();
    }
    @Override
    public void onAttach(Context contexto){
        super.onAttach(contexto);
        accionesDialogo=(AccionesDialogo)contexto;
    }
}
