/*
 * Copyright (C) 2010 Karl Ostmo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openintents.calendarpicker.activity;

import java.text.DateFormat;
import java.util.Date;

import org.openintents.calendarpicker.R;
import org.openintents.calendarpicker.contract.CalendarPickerConstants;
import org.openintents.calendarpicker.view.FlingableMonthView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DayEventsListActivity extends AbstractEventsListActivity {

	static final String TAG = "DayEventsListActivity";
	
    // ========================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        View main = findViewById(R.id.main);
        main.setBackgroundResource(R.drawable.panel_background);
    }
    
    // ========================================================================
	Cursor requery() {

        Uri intent_data = getIntent().getData();

        Date d = new Date(getIntent().getLongExtra(CalendarPickerConstants.CalendarDatePicker.IntentExtras.INTENT_EXTRA_EPOCH, 0));

        long day_begin = d.getTime();
        long day_end = day_begin + FlingableMonthView.MILLISECONDS_PER_DAY;

        
    	String selection = null;
    	if (getIntent().hasExtra(CalendarPickerConstants.CalendarEventPicker.ContentProviderColumns.CALENDAR_ID)) {
        	long cal_id = getIntent().getLongExtra(CalendarPickerConstants.CalendarEventPicker.ContentProviderColumns.CALENDAR_ID, -1);
    		selection = CalendarPickerConstants.CalendarEventPicker.ContentProviderColumns.CALENDAR_ID + "=" + cal_id;
    	}

		Cursor cursor = managedQuery(intent_data,
				PeriodBrowsingActivity.getAugmentedProjection(getIntent()),
				KEY_EVENT_TIMESTAMP + ">=? AND " + KEY_EVENT_TIMESTAMP + "<?"
				+ (selection == null ? "" : " AND " + selection),
				new String[] {Long.toString(day_begin), Long.toString(day_end)},
				constructOrderByString());

		int cursor_row_count = cursor.getCount();
		
		String header_text = cursor_row_count + " event(s) on " + MonthActivity.YMD_FORMATTER.format(d);
		((TextView) findViewById(R.id.list_header)).setText(header_text);
		
		return cursor;
	}

    // ========================================================================
	@Override
	DateFormat getDateFormat() {
		return DateFormat.getTimeInstance();
	}
}

