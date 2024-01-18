/**
 * AsyncTask pentru trimiterea unui email prin cererea HTTP POST.
 * Această sarcină trebuie executată într-un fir de execuție separat.
 *
 * Exemplu de utilizare:
 * new SendMail().execute(username, password, to, subject, body);
 */
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

    /**
     * Sarcina de fundal pentru trimiterea unui email.
     *
     * @param params [0] username, [1] password, [2] to, [3] subject, [4] body
     * @return null
     */
    @Override
    protected Void doInBackground(String... params) {
        try {

            URL url = new URL("http://192.168.56.1/send-email.php");


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setRequestMethod("POST");


            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);


            Map<String, String> postDataParams = new HashMap<>();
            postDataParams.put("username", params[0]);
            postDataParams.put("password", params[1]);
            postDataParams.put("to", params[2]);
            postDataParams.put("subject", params[3]);
            postDataParams.put("body", params[4]);


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


            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {



                Log.d("SendEmailTask", "Response: " + readStream(urlConnection.getInputStream()));
            } else {
                Log.e("SendEmailTask", "Failed to send email. Response Code: " + responseCode);
            }


            urlConnection.disconnect();

        } catch (Exception e) {
            Log.e("SendEmailTask", "Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Metodă ajutătoare pentru a converti InputStream în șir.
     *
     * @param is InputStream care trebuie convertit
     * @return Reprezentarea sub formă de șir a InputStream
     */
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