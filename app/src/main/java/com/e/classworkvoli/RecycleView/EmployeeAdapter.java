package com.e.classworkvoli.RecycleView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.classworkvoli.R;
import com.e.classworkvoli.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context context;
    List<Employee> employeeViewList;

    public EmployeeAdapter(Context context, List<Employee> employeeViewList) {
        this.context = context;
        this.employeeViewList = employeeViewList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_layout_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        Employee employeeView = employeeViewList.get(position);
        holder.tvId.setText(Integer.toString(employeeView.getId()));
        holder.tvName.setText(employeeView.getEmployee_name());
        holder.tvSalary.setText(employeeView.getEmployee_salary());
        holder.tvAge.setText(Integer.toString(employeeView.getEmployee_age()));
    }

    @Override
    public int getItemCount() {
        return employeeViewList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvSalary, tvAge;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvEmployeeId);
            tvName = itemView.findViewById(R.id.tvEmployeeName);
            tvSalary = itemView.findViewById(R.id.tvEmployeeSalary);
            tvAge = itemView.findViewById(R.id.tvEmployeeAge);
        }
    }
}
