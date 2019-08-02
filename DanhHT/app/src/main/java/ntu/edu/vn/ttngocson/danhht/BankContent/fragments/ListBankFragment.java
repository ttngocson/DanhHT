package ntu.edu.vn.ttngocson.danhht.BankContent.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.edu.vn.ttngocson.danhht.BankContent.DetailActivity;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.Bank;
import ntu.edu.vn.ttngocson.danhht.BankContent.models.BankManager;
import ntu.edu.vn.ttngocson.danhht.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBankFragment extends Fragment {

    ArrayList<Bank> listBank = new ArrayList<>();
    RecyclerView rvBank;
    BankAdapter adapter;

    public ListBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_list_bank, container, false);
        rvBank = view.findViewById(R.id.rvBank);
        rvBank.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateRV();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK)
            adapter.notifyDataSetChanged();
    }

    private void UpdateRV() {
        listBank = BankManager.getInstance().getBanks();
        adapter = new BankAdapter(listBank);
        rvBank.setAdapter(adapter);
    }

    private class BankHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtLsn, txtLst;
        LinearLayout item;
        Bank b;

        public BankHolder(@NonNull View itemView) {
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
                Intent intent = new Intent(getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                int position = BankManager.getInstance().getBanks().indexOf(this.b);
                bundle.putString("Task", "Mod");
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        }

        public void onBind(Bank b){
            this.b = b;
            txtName.setText(this.b.getName());
            txtLsn.setText(String.valueOf(this.b.getLsn()));
            txtLst.setText(String.valueOf(this.b.getLst()));
        }
    }

    private class BankAdapter extends RecyclerView.Adapter<BankHolder>{
        ArrayList<Bank> listBank;
        public BankAdapter(ArrayList<Bank> listBank) {
            this.listBank = listBank;
        }

        @NonNull
        @Override
        public BankHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item_bank_rv, viewGroup, false);
            return new BankHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BankHolder bankHolder, int i) {
            bankHolder.onBind(listBank.get(i));
        }

        @Override
        public int getItemCount() {
            return listBank.size();
        }
    }

}
