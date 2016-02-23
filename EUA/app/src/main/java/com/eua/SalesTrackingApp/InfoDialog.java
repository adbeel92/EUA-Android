package com.eua.SalesTrackingApp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by unobtainium on 22/02/16.
 */
public class InfoDialog extends DialogFragment {
    private static final String int_visitasappsVisitasId = "param1";
    private static final String int_visitasappsLoginID = "param2";
    private static final String str_visitasappsDesAtencion = "param3";
    private static final String int_visitasappsDejaStock = "param4";
    private static final String int_visitasappsDejaFolleteria = "param5";
    private static final String str_visitasappsDesComentarios = "param6";
    private static final String dtt_visitasappsFechaHora = "param7";
    private static final String dbl_visitasappsXCoord = "param8";
    private static final String dbl_visitasappsYCoord = "param9";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InfoDialog() {
    }

    public static InfoDialog newInstance(String visit, String userId, String intName, String stock, String broch, String comments, String dateTime, String latitude, String longitude) {
        InfoDialog fragment = new InfoDialog();
        Bundle args = new Bundle();
        args.putString(int_visitasappsVisitasId, visit);
        args.putString(int_visitasappsLoginID, userId);
        args.putString(str_visitasappsDesAtencion, intName);
        args.putString(int_visitasappsDejaStock, stock);
        args.putString(int_visitasappsDejaFolleteria, broch);
        args.putString(str_visitasappsDesComentarios, comments);
        args.putString(dtt_visitasappsFechaHora, dateTime);
        args.putString(dbl_visitasappsXCoord, latitude);
        args.putString(dbl_visitasappsYCoord, longitude);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ALERT);
        }*/
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = "int_visitasappsVisitasId: " + getArguments().getString(int_visitasappsVisitasId) + "\n" +
                "int_visitasappsLoginID: " + getArguments().getString(int_visitasappsLoginID) + "\n" +
                "str_visitasappsDesAtencion: " + getArguments().getString(str_visitasappsDesAtencion) + "\n" +
                "int_visitasappsDejaStock: " + getArguments().getString(int_visitasappsDejaStock) + "\n" +
                "int_visitasappsDejaFolleteria: " + getArguments().getString(int_visitasappsDejaFolleteria) + "\n" +
                "str_visitasappsDesComentarios: " + getArguments().getString(str_visitasappsDesComentarios) + "\n" +
                "dtt_visitasappsFechaHora: " + getArguments().getString(dtt_visitasappsFechaHora) + "\n" +
                "dbl_visitasappsXCoord: " + getArguments().getString(dbl_visitasappsXCoord) + "\n" +
                "dbl_visitasappsYCoord: " + getArguments().getString(dbl_visitasappsYCoord);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_logged_as, null));
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
