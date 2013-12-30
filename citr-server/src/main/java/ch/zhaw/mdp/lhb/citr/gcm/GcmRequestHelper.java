/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.gcm;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

/**
 * @author Daniel Brun
 * 
 *         Helper class for Google Cloud Messaging requests.
 */
public class GcmRequestHelper {

    private static final String GCM_URL = "https://android.googleapis.com/gcm/send";
    private static final String API_KEY = "AIzaSyCGeuPmENKcz9pmQPqiweyoL4xMrbCweBk";

    public void sendHTTPRequest(List<String> someRecipients) throws IOException {
	/*
	 * Create the POST request
	 */
	HttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(GCM_URL);

	httpPost.addHeader("Authorization", "key= " + API_KEY);
	httpPost.addHeader("Content-Type", "application/json");

	StringWriter jsonWriter = new StringWriter();
	JsonFactory factory = new JsonFactory();
	JsonGenerator generator = factory.createJsonGenerator(jsonWriter);

	generator.writeStartObject();
	generator.writeFieldName("registration_ids");

	generator.writeStartArray();

	for (String recp : someRecipients) {
	    generator.writeString(recp);
	}

	generator.writeEndArray();
	generator.writeEndObject();

	generator.close();

	System.out.println(jsonWriter.toString());

	// Request parameters and other properties.
	try {
	    httpPost.setEntity(new StringEntity(jsonWriter.toString()));
	} catch (UnsupportedEncodingException e) {
	    // writing error to Log
	    e.printStackTrace();
	}
	/*
	 * Making HTTP Request
	 */
	try {
	    HttpResponse response = httpClient.execute(httpPost);
	    HttpEntity respEntity = response.getEntity();

	    if (respEntity != null) {
		// EntityUtils to get the reponse content
		String content = EntityUtils.toString(respEntity);
		System.out.println(content);
	    }
	} catch (ClientProtocolException e) {
	    // writing exception to log
	    e.printStackTrace();
	} catch (IOException e) {
	    // writing exception to log
	    e.printStackTrace();
	}
    }
}
