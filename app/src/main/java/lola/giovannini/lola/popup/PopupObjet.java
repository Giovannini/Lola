package lola.giovannini.lola.popup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by giovannini on 10/21/14.
 */
public class PopupObjet extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] liste = {"Ajouter", "Retirer", "Vendre"};
        builder.setTitle("Gestion de l'objet")
                .setItems(liste, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:/*Ajouter*/

                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
