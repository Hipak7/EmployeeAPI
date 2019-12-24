package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.model.Employee;
import com.e.classworkvoli.model.EmployeeCUD;
import com.e.classworkvoli.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateDelete extends AppCompatActivity {
    private EditText etEmployeeNo,etEmpName,etEmpSalary,etEmpAge;
    private Button btnSearchUpdate,btnUpdate,btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        etEmpName=findViewById(R.id.etName);
        etEmpAge=findViewById(R.id.etAge);
        etEmpSalary=findViewById(R.id.etSalary);

        EmployeeCUD employee;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
            private void loadData(){

                EmployeeAPI employeeAPI= URL.createInstance().create(EmployeeAPI.class);
                Call<Employee>employeeCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));
                employeeCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        etEmpName.setText(response.body().getEmployee_name());
                        etEmpAge.setText(response.body().getEmployee_age());
                        etEmpSalary.setText(response.body().getEmployee_salary());
                        }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(UpdateDelete.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }

            private void updateEmployee(){
                EmployeeCUD employeeCUD=new EmployeeCUD(
                        etEmpName.getText().toString(),
                        Float.parseFloat(etEmpSalary.getText().toString()),
                        Integer.parseInt(etEmpAge.getText().toString())
                );
                EmployeeAPI employeeAPI= URL.createInstance().create(EmployeeAPI.class);
                etEmpName.getText().toString(),
                Float.parseFloat(etEmpSalary.getText().toString()),
                Integer.parseInt(etEmpAge.getText().toString())

            };
            Call<Void> voidCall=employeeApi.updateEmployee(Integer.parseInt(etEmployeeNo.getText().toString()),e);

            voidCa
        });

    }
}
