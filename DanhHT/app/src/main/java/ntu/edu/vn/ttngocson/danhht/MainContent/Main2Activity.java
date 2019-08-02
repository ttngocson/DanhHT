package ntu.edu.vn.ttngocson.danhht.MainContent;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import ntu.edu.vn.ttngocson.danhht.BankContent.BankActivity;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.Bank;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.BankManager;
import ntu.edu.vn.ttngocson.danhht.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    long GB, TT;
    TextView txtGB, txtTT, txtLSN, txtTBH, txtTLN, txtTLY, txtTLHT, txtTLHN, txtTPT;
    EditText editTHVay, editTienVay;
    Button btnBank, btnReload;
    ArrayList<Bank> list;
    int position = 0, THVay;
    long TienVay;

    double tienBH = 0, tienLaiN = 0, tienLaiY = 0, tienLaiHT = 0, tienLaiHN = 0, tienPT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        GB = Long.parseLong(bundle.getString("GB", "0"));
        TT = Long.parseLong(bundle.getString("TT", "0"));
        TienVay = GB - TT;
        list = BankManager.getInstance().getBanks();
        AddControls();
        AddEvents();
        editTienVay.setText(String.valueOf(TienVay));
        SaveValue();
        AddView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 12 && resultCode == RESULT_OK){
            position = data.getIntExtra("Position", 0);
            AddView();
        }
    }

    private void AddView() {
        txtGB.setText(String.valueOf(GB));
        txtTT.setText(String.valueOf(TT));
        btnBank.setText(list.get(position).getName());
        editTienVay.setText(String.valueOf(TienVay));
        txtLSN.setText(list.get(position).getLsn() + "%");

        if(TienVay != 0)
            Processing((double) TienVay, list.get(position).getLsn(), THVay);

        txtTBH.setText(String.valueOf((long)tienBH));
        txtTPT.setText(String.valueOf((long)tienPT));
        txtTLN.setText(String.valueOf((long)tienLaiN));
        txtTLY.setText(String.valueOf((long)tienLaiY));
        txtTLHT.setText(String.valueOf((long)tienLaiHT));
        txtTLHN.setText(String.valueOf((long)tienLaiHN));
    }

    private void AddControls() {
        txtGB = findViewById(R.id.txtGB);
        txtTT = findViewById(R.id.txtTT);
        txtLSN = findViewById(R.id.txtLSN);
        txtTBH = findViewById(R.id.txtTBH);
        txtTLN = findViewById(R.id.txtTLN);
        txtTLY = findViewById(R.id.txtTLY);
        txtTLHT = findViewById(R.id.txtTLHT);
        txtTLHN = findViewById(R.id.txtTLHN);
        txtTPT = findViewById(R.id.txtTPT);

        editTHVay = findViewById(R.id.editTHVay);
        editTienVay = findViewById(R.id.editTienVay);

        btnBank = findViewById(R.id.btnBank);
        btnReload = findViewById(R.id.btnReload);
    }

    private void AddEvents() {
        btnBank.setOnClickListener(this);
        btnReload.setOnClickListener(this);
    }

    private void SaveValue(){
        THVay = Integer.parseInt(editTHVay.getText().toString());
        TienVay = Long.parseLong(editTienVay.getText().toString());
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void Processing(double tienVay, float laiSuat, int thang){
        LocalDate ldSrc = LocalDate.now();
        LocalDate temp = ldSrc;
        ArrayList<Double> arrLT = new ArrayList<>();
        ArrayList<Long> arrDay = new ArrayList<>();
        for (int i = 0; i< thang; i++){
            LocalDate ldDst = ldSrc.plusMonths(i+1);
            long day = temp.until(ldDst, ChronoUnit.DAYS);
            arrDay.add(day);
            //System.out.print(temp + "\t");
            //System.out.print(ldDst + "\t");
            //System.out.print(String.valueOf(day) + "\t");
            arrLT.add((double)laiSuat/100*day/365);
            //System.out.println(arrLT.get(i) + "\t");
            //System.out.println("----------");
            temp = ldDst;
        }
        double tu = 1.0, mau = 1.0;
        for (int i = 0; i < thang; i++){
            tu *= (1+arrLT.get(i));
            if (i != 0)
                mau = mau*(1+arrLT.get(i)) + 1.0;
        }
        double _final = Math.ceil(tu*tienVay*1.055/mau/1000.0)*1000 + 12000;
        tienPT = _final;
        tienBH = tienVay*0.055;
        //System.out.println("final: " + _final);
        tienLaiN = Math.ceil((_final * thang - tienVay*1.055)/1000.0)*1000;
        //System.out.println("Tien lai chua co bao hiem: " + (long)tienLaiN);
        tienLaiY = Math.ceil((_final * thang - tienVay)/1000.0)*1000;
        //System.out.println("Tien lai da co bao hiem: " + (long)tienLaiY);
        tienLaiHT = Math.ceil(tienLaiY/thang/1000.0)*1000;
        //System.out.println("Tien lai hang thang: " + (long)tienLaiHT);
        tienLaiHN = Math.ceil(tienLaiHT/30/1000.0)*1000;
        //System.out.println("Tien lai hang ngay: " + (long)tienLaiHN);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBank){
            Intent intent = new Intent(this, BankLiskActivity.class);
            SaveValue();
            startActivityForResult(intent, 12);
        }
        if(v.getId() == R.id.btnReload){
            SaveValue();
            AddView();
        }
    }
}
