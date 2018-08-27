package com.example.user.recyclerviewexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class ViewHolderFactory {

    private SparseArray<ViewHolderWithLayout> holders;

    public ViewHolderFactory() {
        holders = new SparseArray<>();
        holders.put(0, new ViewHolderWithLayout(R.layout.separator_layout, SeparatorViewHolder.class));
        holders.put(1, new ViewHolderWithLayout(R.layout.operation_success_layout, OperationSuccessViewHolder.class));
        holders.put(2, new ViewHolderWithLayout(R.layout.operation_fail_layout, OperationFailViewHolder.class));
        holders.put(3, new ViewHolderWithLayout(R.layout.operation_multy_layout, OperationMultyViewHolder.class));
    }

    public RecyclerView.ViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = holders.get(viewType).getViewHolder(parent);
        return vh;
    }

    class ViewHolderWithLayout {
        public int layoutId;
        public Class<RecyclerView.ViewHolder> aClass;

        public ViewHolderWithLayout(int layoutId, Class aClass) {
            this.layoutId = layoutId;
            this.aClass = aClass;
        }

        public View getView(@NonNull ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        }

        public RecyclerView.ViewHolder getViewHolder(@NonNull ViewGroup parent) {

            View view = getView(parent);

            try {
                return aClass.getConstructor(View.class).newInstance(view);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
