package omkar.com.budgetmanager.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import omkar.com.budgetmanager.data.Receive;

public class ReceiveAdapter extends BaseAdapter {
    public List<Receive> receives;

    @Override
    public int getCount() {
        if (receives == null) {
            return 0;
        } else {
            return receives.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return receives.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Receive receive = receives.get(position);
        ReceiveView receiveView;
        if(convertView == null){
            receiveView = new ReceiveView(parent.getContext());


        }else {
            receiveView = (ReceiveView) convertView;
        }
        //QuoteView quoteView = new QuoteView(parent.getContext());
        receiveView.setReceive(receive);
        return receiveView;

    }
    public void setReceives(List<Receive> receives) {
        this.receives = receives;
        notifyDataSetChanged();
    }
}
