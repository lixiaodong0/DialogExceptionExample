package com.lixd.dialog.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixd.dialog.test.R;
import com.lixd.dialog.test.bean.ItemBean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final Context mContext;
    private List<ItemBean> mList;
    private OnItemClickListener mItemClickListener;

    public ItemAdapter(Context context, List<ItemBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.recy_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final ItemBean itemBean = mList.get(position);
        holder.imgIcon.setImageResource(itemBean.resIcon);
        holder.tvTitle.setText(itemBean.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(itemBean, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgIcon;
        private final TextView tvTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(ItemBean bean, int position);
    }
}
