/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import ch.zhaw.mdp.lhb.citr.activities.ErrorActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/**
 * @author Daniel Brun
 * 
 *         Handler for all Uncaught-Exceptions
 */
public class CitrExceptionHandler implements UncaughtExceptionHandler {

	private final String LINE_BREAK = "\n";

	private Activity activity;

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anActivity
	 *            The underlining activity.
	 */
	public CitrExceptionHandler(Activity anActivity) {
		activity = anActivity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang
	 * .Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable exception) {
		// Get stack trace.
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));

		// Write report.
		StringBuilder errorReport = new StringBuilder();
		errorReport
				.append("In der Applikation ist ein schwerwiegender Fehler aufgetreten.\n");

		errorReport.append("\n---------- DEVICE INFORMATION ----------\n");
		errorReport.append("Brand: ");
		errorReport.append(Build.BRAND);
		errorReport.append(LINE_BREAK);
		errorReport.append("Device: ");
		errorReport.append(Build.DEVICE);
		errorReport.append(LINE_BREAK);
		errorReport.append("Model: ");
		errorReport.append(Build.MODEL);
		errorReport.append(LINE_BREAK);
		errorReport.append("Id: ");
		errorReport.append(Build.ID);
		errorReport.append(LINE_BREAK);
		errorReport.append("Product: ");
		errorReport.append(Build.PRODUCT);
		errorReport.append(LINE_BREAK);
		errorReport.append("\n---------- FIRMWARE ----------\n");
		errorReport.append("SDK: ");
		errorReport.append(Build.VERSION.SDK_INT);
		errorReport.append(LINE_BREAK);
		errorReport.append("Release: ");
		errorReport.append(Build.VERSION.RELEASE);
		errorReport.append(LINE_BREAK);
		errorReport.append("Incremental: ");
		errorReport.append(Build.VERSION.INCREMENTAL);
		errorReport.append(LINE_BREAK);

		errorReport.append("---------- CAUSE OF ERROR ----------\n\n");
		errorReport.append(stackTrace.toString());

		Intent intent = new Intent(activity, ErrorActivity.class);
		intent.putExtra("error", errorReport.toString());
		activity.startActivity(intent);

		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}

}
