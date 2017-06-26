package com.yunwei.rfid;

import android.app.Activity;
import android.hardware.uhf.magic.reader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RFIDReadRusult extends Activity {
	private readmode mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(RFID_Res_R.layout.show_epc_layout());
		TextView mTextView = (TextView) findViewById(RFID_Res_R.id.show_epc_id());
		mode = (readmode) getIntent().getSerializableExtra("mode");
		Log.d("Aaron", "TID=-="+mode.getTIDNo()+", epcId=="+mode.getEPCNo());
		if (mode != null) {
			mTextView.setText("读取结果：\n      "+mode.getTIDNo());
		}
		findViewById(RFID_Res_R.id.rfid_back_id()).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RFIDReadRusult.this.finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}
}
