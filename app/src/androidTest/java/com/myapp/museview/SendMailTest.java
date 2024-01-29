package com.myapp.museview;

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.myapp.museview.SendMail;

public class SendMailTest {

    @Test
    public void testSendMailTask() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final boolean[] success = new boolean[1]; // Array to store success status


        new SendMail() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // Set success status based on your AsyncTask behavior
                success[0] = true;
                latch.countDown(); // Release latch
            }
        }.execute("username", "password", "to@example.com", "Test Subject", "Test Body");


        latch.await(10, TimeUnit.SECONDS);


        if (!success[0]) {
            fail("SendMail AsyncTask failed");
        }
    }
}