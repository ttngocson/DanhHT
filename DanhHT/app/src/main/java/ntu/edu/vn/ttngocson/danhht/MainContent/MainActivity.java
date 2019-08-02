package ntu.edu.vn.ttngocson.danhht.MainContent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ntu.edu.vn.ttngocson.danhht.BankContent.BankActivity;
import ntu.edu.vn.ttngocson.danhht.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editGB, editTT;
    Button btnProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AddControls();
        AddEvents();

    }

    private void AddControls() {
        editGB = findViewById(R.id.editGB);
        editTT = findViewById(R.id.editTT);
        btnProcess = findViewById(R.id.btnProcess);
    }

    private void AddEvents() {
        btnProcess.setOnClickListener(this);
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
            Intent intent = new Intent(this, BankActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnProcess){
            Intent intent = new Intent(this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("GB", editGB.getText().toString());
            bundle.putString("TT", editTT.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
