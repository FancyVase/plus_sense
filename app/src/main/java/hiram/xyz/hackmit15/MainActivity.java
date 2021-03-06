package hiram.xyz.hackmit15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Map;

import io.indico.Indico;
import io.indico.enums.FacialEmotion;
import io.indico.network.IndicoCallback;
import io.indico.results.IndicoResult;
import io.indico.utils.IndicoException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText sentimentEditText = (EditText) findViewById(R.id.sentimentEditText);
        final Button checkButton = (Button) findViewById(R.id.checkButton);
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        Indico.init(this, getString(R.string.indico_api_key), null);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filepath = "../../../../res/drawable/happy_selfie.png";
                IndicoResult image = Indico.fer.predict(filepath);

                try {
                    Map<FacialEmotion, Double> result = image.getFer();
                    Log.i("Facial Emotion Recognition:", result.toString());
            } catch (IndicoException e) {
                    e.printStackTrace();
                }
        };
        checkButton.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
