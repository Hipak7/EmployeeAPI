package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSearchUpdate = findViewById(R.id.btnSearch);
        etEmployeeNo = findViewById(R.id.etEmpID);
        etEmpName = findViewById(R.id.etName);
        etEmpAge = findViewById(R.id.etAge);
        etEmpSalary = findViewById(R.id.etSalary);

        btnSearchUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }


        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             deleteEmployee();
                                         }
                                     }

        );
    }

    private void deleteEmployee() {
        final EmployeeAPI employeeApi = URL.createInstance().create(EmployeeAPI.class);
        Call<Void> voidCall = employeeApi.deleteEmployee(Integer.parseInt(etEmployeeNo.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Sucessfully deleted", Toast.LENGTH_SHORT).show();
                etEmployeeNo.setText("");
                etEmpName.setText("");
                etEmpAge.setText("");
                etEmpSalary.setText("");


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
            private void updateEmployee() {
        EmployeeCUD employeeCUD=new EmployeeCUD(
                etEmpName.getText().toString(),
                Float.parseFloat(etEmpSalary.getText().toString()),
                Integer.parseInt(etEmpSalary.getText().toString())
        );
        Call<Void> voidCall=employeeAPI.updateEmployee(Integer.parseInt(etEmployeeNo.getText().toString()),employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        if (TextUtils.isEmpty(etEmployeeNo.getText())){
            etEmployeeNo.setError("Please enter employee id");
            return;
        }
        employeeAPI =URL.createInstance().create(EmployeeAPI.class);
        Call<Employee> listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etEmpName.setText(response.body().getEmployee_name());
                etEmpSalary.setText(response.body().getEmployee_salary());
                etEmpAge.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }






