package com.example.krot.diffutil_workerthread.callback;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.krot.diffutil_workerthread.model.Item;

import java.util.List;

/**
 * Created by Krot on 1/15/18.
 */

public class ItemUtilCallback extends DiffUtil.Callback {

    @Nullable
    private List<Item> mOldList;
    @Nullable
    private List<Item> mNewList;

    public ItemUtilCallback(List<Item> mOldList, List<Item> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return (mOldList != null ? mOldList.size() : 0);
    }

    @Override
    public int getNewListSize() {
        return (mNewList != null ? mNewList.size() : 0);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldList != null) {
            return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldList != null) {
            return mOldList.get(oldItemPosition).sameContent(mNewList.get(newItemPosition));
        } else {
            return false;
        }
    }
}
