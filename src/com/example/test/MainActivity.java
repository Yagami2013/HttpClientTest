package com.example.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.networkbench.agent.impl.NBSAppAgent;


public class MainActivity extends ActionBarActivity {
	private static String[] URL={
		"http://a",
		"http://1",
		"http://123.com",
		"http://192.168.2.161/test zental/",
		"https://jira.networkbench.com/browse/RUMSREPORT-769",
//		"http://192.168.2.161/中文/",
		"http://hao123.com",
		"http://192.168.2.161",
		"http://192.168.1.5/sdk-test/407.php",
		"http://192.168.1.5/sdk-test/407.php",
		"http://192.168.1.5/sdk-test/407.php?tn=test&pn=1234"
	};
	private final String TAG = "YttTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1e018f1b5d17467eb1b310b0b60a81bd,外网测试“test_atwork1”
        NBSAppAgent.setLicenseKey("1e018f1b5d17467eb1b310b0b60a81bd").start(this);

        try {
			HttpTest();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }
    
	private void HttpTest() throws InterruptedException {
		Thread.sleep(3*1000);
		// get http info in another thread
		//new HttpClientTestThread().start();
		for (int i = 0; i < URL.length; i++) {
			final String url = URL[i];
			new Thread(){
				public void run() {
					Log.d(TAG, "----"+url);
					
					HttpClient mClient = new DefaultHttpClient();
					HttpGet mGet=new HttpGet(url);
					Log.d(TAG, mGet.getRequestLine().toString());
					
					try {						
						HttpResponse response = mClient.execute(mGet);
						Log.d(TAG, response.getStatusLine().toString());
						InputStream mStream=response.getEntity().getContent();
						while (mStream.read()!=-1) {
													
						}
						Log.d(TAG, "read input finished");
						Log.d(TAG, "*****"+response.getEntity().getContentLength());
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				};
			}.start();
			
			
		}
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
