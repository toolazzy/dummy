package secondapp.dummy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ravi upreti on 12/10/2018.
 */

public class dummyadapter extends RecyclerView.Adapter<dummyadapter.viewholder>{

    private String[] data;
    public dummyadapter(StringBuilder data){
        this.data=data.toString();
    }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.text_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        String title=data[position];
        holder.text.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView text;
        public viewholder(View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.textview);
        }
    }
}
