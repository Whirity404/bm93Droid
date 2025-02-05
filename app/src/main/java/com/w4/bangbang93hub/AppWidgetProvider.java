package com.w4.bangbang93hub;

import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.RemoteViews;

public class AppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_layout);
            views.setOnClickPendingIntent(R.id.widget_icon, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

