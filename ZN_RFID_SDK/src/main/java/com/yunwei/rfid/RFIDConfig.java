package com.yunwei.rfid;

import android.content.Context;
import android.content.Intent;
import android.hardware.uhf.magic.DevBeep;
import android.hardware.uhf.magic.reader;
import android.os.Handler;
import android.util.Log;

public class RFIDConfig {

	private static Context context;
	private static RFIDResultListener listener;
	private static boolean showResultView;

	private static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == reader.msgreadepc) {
				readmode model = new readmode();
				// 在此对数据进行处理：分2中情况进行处理，读取EPC和读取TID：
				String readerdata = (String) msg.obj;
				if (readerdata.contains(":")) {
					String arrString[] = readerdata.split(":");
					model.setEPCNo(arrString[0]);
					model.setTIDNo(arrString[1]);
				} else {
					model.setEPCNo(readerdata);
				}
				if (showResultView) {
					Intent intent = new Intent(context, RFIDReadRusult.class);
					intent.putExtra("mode", model);
					context.startActivity(intent);
					Log.d("Aaron", "EPC==" + model.getEPCNo());
				}

				if (listener != null) {
					listener.RFIDResultCallBack(model);
				}
				// mTextView.setText(model.getEPCNo());
				// 插入前做判断是否已经读取过该标签：
				// IshavaCode(model, 1);
			}
		}

		;
	};

	/**
	 * @param @param context
	 * @return void
	 * @throws
	 * @Title: initUHF
	 * @Description: 初始化UHF标签：
	 */
	public static void initUHF(Context context, boolean showResultView) {
		RFIDConfig.context = context;
		RFIDConfig.showResultView = showResultView;
		android.hardware.uhf.magic.reader.init("/dev/ttyMT1");
		android.hardware.uhf.magic.reader.Open("/dev/ttyMT1");
		if (reader.SetTransmissionPower(1950) != 0x11) {
			if (reader.SetTransmissionPower(1950) != 0x11) {
				reader.SetTransmissionPower(1950);
			}
		}
		DevBeep.init(context);
	}

	public static void setListener(RFIDResultListener listener) {
		RFIDConfig.listener = listener;
	}


	/**
	 * 连续读取标签EPC
	 */
	public static void initReadLoop() {
		reader.m_handler = handler;
		android.hardware.uhf.magic.reader.InventoryLablesLoop();
	}

	/**
	 * 停止连续读取
	 */
	public static void stopRFIDReadLoop() {
		android.hardware.uhf.magic.reader.StopLoop();
	}

	/**
	 * @return void 返回类型
	 * @throws
	 * @Title: closeUHF
	 * @Description:关闭
	 */
	public static void closeUHF() {
		DevBeep.release();
		android.hardware.uhf.magic.reader.Close();

		reader.StopLoop();
	}
}
