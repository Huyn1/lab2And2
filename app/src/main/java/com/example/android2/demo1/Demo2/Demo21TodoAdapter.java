package com.example.android2.demo1.Demo2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2.R;

import java.util.List;

public class Demo21TodoAdapter extends
        RecyclerView.Adapter<Demo21TodoAdapter.TodoViewHolder> {

    private List<demo21Todo> todoList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
        void onStatusChange(int position, boolean isDone);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Demo21TodoAdapter(List<demo21Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demo21_item_todo,
                        parent, false);
        return new TodoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TodoViewHolder holder,
            @SuppressLint("RecyclerView") int position) {
        demo21Todo currentTodo = todoList.get(position);
        holder.tvToDoName.setText(currentTodo.getTitle());
        holder.checkBox.setChecked(currentTodo.getStatus() == 1);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (listener != null) {
                    listener.onStatusChange(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvToDoName;
        public Button btnEdit, btnDelete;
        public CheckBox checkBox;

        public TodoViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvToDoName = itemView.findViewById(R.id.tvToDoName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            checkBox = itemView.findViewById(R.id.checkBox);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }
}
