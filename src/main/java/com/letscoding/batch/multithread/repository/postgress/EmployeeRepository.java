package com.letscoding.batch.multithread.repository.postgress;

import com.letscoding.batch.multithread.dbmodel.postgress.Employee;
import com.letscoding.repository.common.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseJpaRepository<Employee,Long> {

    @Query(value="select * from emp.emp_user",nativeQuery = true)
    public List<Employee> getEmpList();

}
