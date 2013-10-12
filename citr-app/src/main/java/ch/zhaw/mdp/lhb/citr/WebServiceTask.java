package ch.zhaw.mdp.lhb.citr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class WebServiceTask extends AsyncTask<String, Integer, String> {

	public static final int POST_TASK = 1;
	public static final int GET_TASK = 2;

	private static final String TAG = "WebServiceTask";

	// connection timeout, in milliseconds (waiting to connect)
	private static final int CONN_TIMEOUT = 3000;

	// socket timeout, in milliseconds (waiting for data)
	private static final int SOCKET_TIMEOUT = 5000;

	private int taskType = GET_TASK;
	private Context mContext = null;
	private String processMessage = "Processing...";

	private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

	private ProgressDialog pDlg = null;

	private HelloAndroidActivity activity;

	public WebServiceTask(HelloAndroidActivity activity, int taskType,
			Context mContext, String processMessage) {

		this.taskType = taskType;
		this.mContext = mContext;
		this.processMessage = processMessage;
		this.activity = activity;
	}

	public void addNameValuePair(String name, String value) {

		params.add(new BasicNameValuePair(name, value));
	}

	private void showProgressDialog() {

		pDlg = new ProgressDialog(mContext);
		pDlg.setMessage(processMessage);
		pDlg.setProgressDrawable(mContext.getWallpaper());
		pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pDlg.setCancelable(false);
		pDlg.show();

	}

	@Override
	protected void onPreExecute() {

		activity.hideKeyboard();
		showProgressDialog();

	}

	protected String doInBackground(String... urls) {

		String url = urls[0];
		String result = "";

		HttpResponse response = doResponse(url);

		if (response == null) {
			return result;
		} else {

			try {

				result = inputStreamToString(response.getEntity().getContent());

			} catch (IllegalStateException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);

			} catch (IOException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
			}

		}

		return result;
	}

	@Override
	protected void onPostExecute(String response) {

		activity.handleResponse(response);
		pDlg.dismiss();

	}

	// Establish connection and socket (data retrieval) timeouts
	private HttpParams getHttpParams() {

		HttpParams htpp = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
		HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

		return htpp;
	}

	private HttpResponse doResponse(String url) {

		// Use our connection and data timeouts as parameters for our
		// DefaultHttpClient
		HttpClient httpclient = new DefaultHttpClient(getHttpParams());

		// ((AbstractHttpClient) httpclient).getCredentialsProvider()
		// .setCredentials(
		// new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,
		// AuthScope.ANY_REALM, "basic"),
		// new UsernamePasswordCredentials("user1", new String(Hex
		// .encodeHex(DigestUtils.md5("strongpassword1")))));

		HttpResponse response = null;
		System.out
				.println("MD5: "
						+ new String(Hex.encodeHex(DigestUtils
								.md5("strongpassword1"))));
		System.out
		.println("SHA512: "
				+ new String(Hex.encodeHex(DigestUtils
						.sha512("strongpassword1"))));
		String auth = new String(Base64.encode(
				("user1" + ":" + new String(Hex.encodeHex(DigestUtils
						.sha512("strongpassword1")))).getBytes(), Base64.NO_WRAP));
		
		// String auth = new String(Base64.encode(("user1" + ":" +
		// "strongpassword1").getBytes(),Base64.NO_WRAP));
		// String auth = "user1" + ":" + "strongpassword1";
		System.out.println("URL:" + url);
		System.out.println(auth);
		try {
			switch (taskType) {

			case POST_TASK:
				HttpPost httppost = new HttpPost(url);
				// Add parameters
				httppost.setEntity(new UrlEncodedFormEntity(params));
				httppost.addHeader("Authorization", "Basic " + auth);
				response = httpclient.execute(httppost);
				break;
			case GET_TASK:
				HttpGet httpget = new HttpGet(url);
				httpget.addHeader("Authorization", "Basic " + auth);
				response = httpclient.execute(httpget);
				break;
			}
		} catch (Exception e) {

			Log.e(TAG, e.getLocalizedMessage(), e);

		}

		return response;
	}

	private String inputStreamToString(InputStream is) {

		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		try {
			// Read response until the end
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (IOException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}

		// Return full string
		return total.toString();
	}

}
