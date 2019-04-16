package omkar.com.budgetmanager.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import omkar.com.budgetmanager.data.Spend;

public class SpendAdapater extends BaseAdapter {
    public List<Spend> spends;

    @Override
    public int getCount() {
        if (spends == null){
            return 0;
        }else {
            return spends.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return spends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Spend spend = spends.get(position);
        SpendView spendView;
        if(convertView == null){
            spendView = new SpendView(parent.getContext());


        }else {
            spendView = (SpendView) convertView;
        }
        //QuoteView quoteView = new QuoteView(parent.getContext());
        spendView.setSpend(spend);
        return spendView;
    }
    public void setSpends(List<Spend> spends) {
        this.spends = spends;
        notifyDataSetChanged();
    }
}
