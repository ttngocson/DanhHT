package ntu.edu.vn.ttngocson.danhht.BankContent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ntu.edu.vn.ttngocson.danhht.BankContent.models.Bank;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.BankManager;
import ntu.edu.vn.ttngocson.danhht.R;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editName, editLsn, editLst;
    Button btnSave, btnDel, btnCancel;
    int position;
    String task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        task = bundle.getString("Task");
        AddControls();
        AddEvents();
        if(task.equals("Mod")){
            position =bundle.getInt("Position");
            AddView();
        }
        else
            btnDel.setVisibility(View.INVISIBLE);
    }

    private void AddControls() {
        editName = findViewById(R.id.editName);
        editLsn = findViewById(R.id.editLsn);
        editLst = findViewById(R.id.editLst);
        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);
        btnCancel = findViewById(R.id.btnCalcel);

    }

    private void AddEvents() {
        btnSave.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void AddView() {
        editName.setText(BankManager.getInstance().getBanks().get(position).getName());
        editLsn.setText(String.valueOf(BankManager.getInstance().getBanks().get(position).getLsn()));
        editLst.setText(String.valueOf(BankManager.getInstance().getBanks().get(position).getLst()));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave){
            Bank bank = new Bank();
            bank.setName(editName.getText().toString());
            bank.setLsn(Float.parseFloat(editLsn.getText().toString()));
            bank.setLst(Float.parseFloat(editLst.getText().toString()));

            if(task.equals("Mod"))
                BankManager.getInstance().modBank(position, bank);

            if(task.equals("Add"))
                BankManager.getInstance().createBank(bank);

            setResult(RESULT_OK);
            finish();
        }

        if(v.getId() == R.id.btnDel){
            BankManager.getInstance().deleteBank(position);
            setResult(RESULT_OK);
            finish();
        }

        if(v.getId() == R.id.btnCalcel){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
