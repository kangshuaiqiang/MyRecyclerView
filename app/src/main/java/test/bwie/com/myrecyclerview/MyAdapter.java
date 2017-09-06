package test.bwie.com.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 黑白 on 2017/9/6.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickLitener onItemClickLitener;
    private ArrayList<String> arr;
    private Context context;

    public MyAdapter(ArrayList<String> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.textView.setText(arr.get(position));
            if (onItemClickLitener != null) {
                myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickLitener.onItemClick(myViewHolder.textView, position);
                    }
                });
            }
            if (onItemClickLitener != null) {
                myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onItemClickLitener.onItemLongClick(myViewHolder.textView, position);
                        return true;
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }

    public void add(int position) {
        arr.add(position, "添加数据");
        notifyItemInserted(position);
    }

    public void removo(int position) {
        arr.remove(position);
        notifyItemRemoved(position);
    }
}
