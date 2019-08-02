package ntu.edu.vn.ttngocson.danhht.MainContent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.danhht.BankContent.fragments.ListBankFragment;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.Bank;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.BankManager;
import ntu.edu.vn.ttngocson.danhht.R;

public class BankLiskActivity extends AppCompatActivity {

    RecyclerView rvList;
    ArrayList<Bank> listBank = new ArrayList<>();
    ListBankAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_lisk);

        AddView();
    }

    private void AddView() {
        rvList = findViewById(R.id.rvListBank);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        listBank = BankManager.getInstance().getBanks();

        adapter = new ListBankAdapter(listBank);
        rvList.setAdapter(adapter);
    }

    private class ListBankAdapter extends RecyclerView.Adapter<ListBankHolder> {

        ArrayList<Bank> listBank;

        public ListBankAdapter(ArrayList<Bank> listBank) {
            this.listBank = listBank;
        }

        @NonNull
        @Override
        public ListBankHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_bank_rv, viewGroup, false);
            return new ListBankHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListBankHolder listBankHolder, int i) {
            listBankHolder.onBind(listBank.get(i));
        }

        @Override
        public int getItemCount() {
            return listBank.size();
        }
    }

    private class ListBankHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName, txtLsn, txtLst;
        LinearLayout item;
        Bank b;

        public ListBankHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtLsn = itemView.findViewById(R.id.txtLsn);
            txtLst = itemView.findViewById(R.id.txtLst);
            item = itemView.findViewById(R.id.item);

            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.item){
                int position = listBank.indexOf(this.b);
                Intent intent = new Intent();
                intent.putExtra("Position", position);
                setResult(RESULT_OK, intent);
                finish();
            }

        }

        public void onBind(Bank b){
            this.b = b;
            txtName.setText(this.b.getName());
            txtLsn.setText(String.valueOf(this.b.getLsn()));
            txtLst.setText(String.valueOf(this.b.getLst()));
        }
    }
}
