package com.e.classworkvoli.API;

import com.e.classworkvoli.model.Employee;
import com.e.classworkvoli.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    //Get employee on basic of EmpID
    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID")int empID);

    //Update employee on the basis of EmpId
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId,@Body EmployeeCUD employeeCUD);

    //Delete employee on basis of EmpId
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID")int empId);
}



