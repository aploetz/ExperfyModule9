/*
 * The MIT License
 *
 * Copyright 2016 Aaron A. Ploetz.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.aaronstechcenter.experfymodule9;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.Date;

public class SecurityLogDAO {
    private Session session;
        
    public SecurityLogDAO(Session _session) {
        session = _session;
    }
    
    public void getSecurityLogsByLocation(String _location) {
        String strCQL = "SELECT location,checkpoint_time,employee_id,"
            + "employee_first,employee_last "
            + "FROM experfy_class.security_logs "
            + "WHERE location=?";
        
        try {
            PreparedStatement statement = session.prepare(strCQL);
            BoundStatement boundStatement = new BoundStatement(statement);
            boundStatement.bind(_location);

            System.out.println();

            ResultSet results = session.execute(boundStatement);
            for (Row row : results) {
                System.out.format("%s %25s %s %20s %20s \n",
                        row.getString("location"), 
                        row.getTimestamp("checkpoint_time"),
                        row.getString("employee_id"),
                        row.getString("employee_first"),
                        row.getString("employee_last")
                );
            }
        } catch (Exception ex) {
            System.out.println("No security logs found for this location.");
        }
    }
    
    public void upsertSecurityLogs(String _location, 
            String _employee_id, String _employee_first_name, 
            String _employee_last_name) {
        
        upsertSecurityLogs(_location,_employee_id,_employee_first_name,_employee_last_name, new Date());
    }
    
    public void upsertSecurityLogs(String _location, 
            String _employee_id, String _employee_first_name, 
            String _employee_last_name, Date _checkpoint_time) {
        
        String strCQL = "INSERT INTO experfy_class.security_logs "
                + "(location,checkpoint_time,employee_id,employee_first,employee_last) "
                + "VALUES (?,?,?,?,?)";
        
        PreparedStatement statement = session.prepare(strCQL);
        BoundStatement boundStatement = new BoundStatement(statement);
        boundStatement.bind(_location, _checkpoint_time, _employee_id,
                _employee_first_name, _employee_last_name);
        
        session.execute(boundStatement);
    }
    
    public void closeConnection() {
        session.close();
    }
}
