package com.myapp.museview;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendMail extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        try {
            // Specify your PHP script URL
            URL url = new URL("http://192.168.0.145/send-email.php");

            // Open a connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            urlConnection.setRequestMethod("POST");

            // Enable input and output streams
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            // Create a Map to store your parameters
            Map<String, String> postDataParams = new HashMap<>();
            postDataParams.put("username", params[0]);
            postDataParams.put("password", params[1]);
            postDataParams.put("to", params[2]);
            postDataParams.put("subject", params[3]);
            postDataParams.put("body", params[4]);

            // Encode the parameters and write them to the output stream
            OutputStream os = new BufferedOutputStream(urlConnection.getOutputStream());
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> entry : postDataParams.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            writer.write(postData.toString());
            writer.flush();
            writer.close();
            os.close();

            // Get the response from the server
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response from the server
                // You can use urlConnection.getInputStream() to read the response
                // ...

                // For simplicity, let's just log the response here
                Log.d("SendEmailTask", "Response: " + readStream(urlConnection.getInputStream()));
            } else {
                Log.e("SendEmailTask", "Failed to send email. Response Code: " + responseCode);
            }

            // Disconnect after the task is done
            urlConnection.disconnect();

        } catch (Exception e) {
            Log.e("SendEmailTask", "Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Helper method to convert InputStream to String
    private String readStream(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            Log.e("SendEmailTask", "Error reading InputStream: " + e.getMessage());
            return "";
        }
    }
}