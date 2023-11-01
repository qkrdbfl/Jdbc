package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {


    public static void main(String[] args) {
        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        /* 한 행의 정보를 담을 DTO */
        EmployeeDTO row = null;

        /* 여러 DTO를 하나의 인스턴스로 처리하기 위한 List */
        List<EmployeeDTO> empList = null;

        String query = "SELECT * FROM EMPLOYEE";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            empList = new ArrayList<>();

            /* 조회 된 행이 있을 경우 EmployeeDTO 객체를 생성해서 담는다. 해당 객체를 List에 add() 한다. */
            while(rset.next()) {
                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
        for (EmployeeDTO emp : empList){
            System.out.println(emp);
        }
    }
}

