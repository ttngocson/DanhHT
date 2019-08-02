package ntu.edu.vn.ttngocson.danhht.BankContent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ntu.edu.vn.ttngocson.danhht.BankContent.fragments.ListBankFragment;
import ntu.edu.vn.ttngocson.danhht.R;

public class BankActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragment_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        AddControls();
        AddEvents();
        OnAttackFragment();
    }

    private void OnAttackFragment() {
        if (fragment == null){
            fragment = new ListBankFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    private void AddControls() {
        fab = findViewById(R.id.fab);
    }

    private void AddEvents() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
            Intent intent = new Intent(this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Task", "Add");
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    }
}
