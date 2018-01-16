package com.example.krot.diffutil_workerthread.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krot.diffutil_workerthread.model.Item;

/**
 * Created by Krot on 1/15/18.
 */

public abstract class ItemBaseViewHolder<T extends Item> extends RecyclerView.ViewHolder {

    @NonNull
    protected T item;

    public ItemBaseViewHolder(ViewGroup parent, int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    public void bindData(@NonNull T item) {
        this.item = item;
    }

}
