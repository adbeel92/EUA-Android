package com.eua.SalesTrackingApp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by rubymobile on 22/02/16.
 */
public class InfoDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String visitId = "param1";
    private static final String loggedUserId = "param2";
    private static final String interviewerName = "param3";
    private static final String stockQty = "param4";
    private static final String brochureQty = "param5";
    private static final String commentsText = "param6";
    private static final String date = "param7";
    private static final String lat = "param8";
    private static final String lng = "param9";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;
    private String mParam7;
    private String mParam8;
    private String mParam9;

    private OnFragmentInteractionListener mListener;

    public InfoDialogFragment() {
    }

    public static InfoDialogFragment newInstance(String visit, String userId, String intName, String stock, String broch, String comments, String dateTime, String latitude, String longitude) {
        InfoDialogFragment fragment = new InfoDialogFragment();
        Bundle args = new Bundle();
        args.putString(visitId, visit);
        args.putString(loggedUserId, userId);
        args.putString(interviewerName, intName);
        args.putString(stockQty, stock);
        args.putString(brochureQty, broch);
        args.putString(commentsText, comments);
        args.putString(date, dateTime);
        args.putString(lat, latitude);
        args.putString(lng, longitude );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(visitId);
            mParam2 = getArguments().getString(loggedUserId);
            mParam3 = getArguments().getString(interviewerName);
            mParam4 = getArguments().getString(stockQty);
            mParam5 = getArguments().getString(brochureQty);
            mParam6 = getArguments().getString(commentsText);
            mParam7 = getArguments().getString(date);
            mParam8 = getArguments().getString(lat);
            mParam9 = getArguments().getString(lng);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = "int_visitasappsVisitasId: " + getArguments().getString(visitId) + "\n" +
        "int_visitasappsLoginID: " + getArguments().getString(loggedUserId) + "\n" +
        "str_visitasappsDesAtencion: " + getArguments().getString(interviewerName) + "\n" +
        "int_visitasappsDejaStock: " + getArguments().getString(stockQty) + "\n" +
        "int_visitasappsDejaFolleteria: " + getArguments().getString(brochureQty) + "\n" +
        "str_visitasappsDesComentarios: " + getArguments().getString(commentsText) + "\n" +
        "dtt_visitasappsFechaHora: " + getArguments().getString(date) + "\n" +
        "dbl_visitasappsXCoord: " + getArguments().getString(lat) + "\n" +
        "dbl_visitasappsYCoord: " + getArguments().getString(lng)        ;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
