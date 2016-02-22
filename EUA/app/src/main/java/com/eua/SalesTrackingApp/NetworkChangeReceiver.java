package com.eua.SalesTrackingApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.eua.SalesTrackingApp.models.VisitReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by unobtainium on 21/02/16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private AgencyReportActivity.SendReport mReportTask;

    @Override
    public void onReceive(Context context, Intent intent) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String dateHour = sdf.format(date);
        List<VisitReport> list = VisitReport.listAll(VisitReport.class);
        if (isNetworkConnected(context)){
            if (list.size()>0){
                VisitReport report = list.get(0);
                 mReportTask = new AgencyReportActivity().new SendReport(report.getVisitId(), report.getLoggedUserId(), report.getInterviewerName(), report.getStock(), report.getBrochureQty(), report.getComments(), dateHour, report.getLatitude(), report.getLongitude());
                mReportTask.execute((Void) null);
            }
        }
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
