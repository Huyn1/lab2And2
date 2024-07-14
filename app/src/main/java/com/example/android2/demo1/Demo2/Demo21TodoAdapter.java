package com.example.android2.demo1.Demo2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private Context context;
    private Demo21TodoDAO demo21TodoDAO;
    public Demo21TodoAdapter(Context context, List<demo21Todo> todoList , Demo21TodoDAO demo21TodoDAO) {
        this.context=context;
        this.todoList = todoList;
       this.demo21TodoDAO=demo21TodoDAO;
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
        // mo ham xoa
        holder.btnDelete.setOnClickListener(v -> {
            showDeleteComfirmDialag(holder.getAdapterPosition());
        });

        // mo ham sua
        holder.btnEdit.setOnClickListener(v -> {
            showEditDialog(holder.getAdapterPosition());
        });
    }
//
    private void showEditDialog(int pos){
        demo21Todo todo = todoList.get(pos);
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.demo31_item,null);
        EditText txtTitel = dialogView.findViewById(R.id.et31Title);
        EditText txtContent = dialogView.findViewById(R.id.et31Content);
        EditText txtDate = dialogView.findViewById(R.id.et31Date);
        EditText txtType =dialogView.findViewById(R.id.et31Type);
        Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        // set texxt
        txtTitel.setText(todo.getTitle());
        txtContent.setText(todo.getContent());
        txtDate.setText(todo.getDate());
        txtType.setText(todo.getType());
        // tao dialog

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        // xu lys su kiem
        btnUpdate.setOnClickListener(v -> {
            String newTitle = txtTitel.getText().toString();
            String newContent = txtContent.getText().toString();
            String newDate = txtDate.getText().toString();
            String newType = txtType.getText().toString();
           if (!newTitle.isEmpty()&& !newContent.isEmpty()&& !newDate.isEmpty()&& !newType.isEmpty()){
               todo.setTitle(newTitle);
               todo.setContent(newContent);
               todo.setDate(newDate);
               todo.setType(newType);
               Demo21TodoDAO.updateTodo(todo);
               notifyItemChanged(pos);
               dialog.dismiss();
           }
           else {
               Toast.makeText(context, "Can nhap du thong tin", Toast.LENGTH_SHORT).show();
           }
        });
        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();//hien thi dialog
    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    private void showDeleteComfirmDialag(int pos){
        new AlertDialog.Builder(context)
                .setTitle("Xac nhan xoa")
                .setMessage("Co muon xoa k")
                .setPositiveButton("Ok",(dialog, which) -> deleteTodoItem(pos))
                .setNegativeButton("Cancel",null)
                .show();
    }
    private void deleteTodoItem(int pos){
        demo21Todo todo = todoList.get(pos);
        Demo21TodoDAO.deleteTodo(todo.getId());
        todoList.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos,todoList.size());

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
                           // listener.onDeleteClick(position);
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
                           // listener.onEditClick(position);
                        }
                    }
                }
            });


        }
        // diolog cho ham xoa
    }
}
