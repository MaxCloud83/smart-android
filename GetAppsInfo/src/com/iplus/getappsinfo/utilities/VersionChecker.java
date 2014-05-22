package com.iplus.getappsinfo.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

public class VersionChecker {
	private static final String TAG = "VersionChecker";
	private Activity activity;
	private Drawable icon;

	public VersionChecker(Activity act) {
		this.activity = act;
	}

	public void checkVersion() {

	}

	class VersionContentRequest extends AsyncTask<String, Void, String> {
		private Context context;
		private int statusCode;

		public VersionContentRequest(Context context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(String... params) {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			ByteArrayOutputStream baos = null;
			String responseBody = null;

			try {
				HttpGet httpget = new HttpGet(params[0]);
				httpResponse = client.execute(httpget);
				statusCode = httpResponse.getStatusLine().getStatusCode();

				if (statusCode == HttpStatus.SC_OK) {
					baos = new ByteArrayOutputStream();
					httpResponse.getEntity().writeTo(baos);
					responseBody = baos.toString();
				}

			} catch (Exception e) {
				Log.e(TAG, e.toString());
			} finally {
				if (baos != null) {
					try {
						baos.close();
					} catch (IOException e) {
						Log.e(TAG, e.toString());
					}
				}
			}

			return responseBody;
		}

		@Override
		protected void onPostExecute(String result) {
			int versionCode = 0;
			String content = null;
			if (statusCode != HttpStatus.SC_OK) {
				Log.e(TAG, "Response invalid. status code=" + statusCode);
			} else {
				if (!result.startsWith("{")) { // for response who append with
												// unknown char
					result = result.substring(1);
				}
				try {
					// json format from server:
					JSONObject json = (JSONObject) new JSONTokener(result)
							.nextValue();
					versionCode = json.optInt("version_code");
					content = json.optString("content");

					int currentVersionCode = getCurrentVersionCode();
					if (currentVersionCode < versionCode) {
						showAlert();
					}
				} catch (JSONException e) {
					Log.e(TAG,
							"is your server response have valid json format?");
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
			}
		}

	}

	public int getCurrentVersionCode() {
		int currentVersionCode = 0;
		PackageInfo pInfo;
		try {
			pInfo = activity.getPackageManager().getPackageInfo(
					activity.getPackageName(), 0);
			currentVersionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {
			// return 0
		}
		return currentVersionCode;
	}

	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);

		Drawable icon = activity.getApplicationInfo().loadIcon(
				activity.getPackageManager());
		builder.setIcon(icon);
		builder.setTitle("Update Alert");
		builder.setMessage("...");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		if (activity != null && !activity.isFinishing()) {
			dialog.show();
		}
	}
}
