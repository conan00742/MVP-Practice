package com.example.krot.diffutil_workerthread.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.krot.diffutil_workerthread.callback.ItemUtilCallback;
import com.example.krot.diffutil_workerthread.model.Item;
import com.example.krot.diffutil_workerthread.viewholder.ItemBaseViewHolder;

import java.util.List;

/**
 * Created by Krot on 1/15/18.
 */

public abstract class ItemBaseAdapter extends RecyclerView.Adapter<ItemBaseViewHolder> {

    @Nullable
    private List<Item> itemList;

    public void updateListItem(@Nullable List<Item> newItemList) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ItemUtilCallback(itemList,
                newItemList), false);
        itemList = newItemList;
        result.dispatchUpdatesTo(ItemBaseAdapter.this);
    }

    @Override
    public void onBindViewHolder(ItemBaseViewHolder holder, int position) {
        holder.bindData(getItemAt(position));
    }

    @Nullable
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(@Nullable List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (itemList != null ? itemList.size() : 0);
    }

    public Item getItemAt(int position) {
        return (itemList != null ? itemList.get(position) : null);
    }
}
