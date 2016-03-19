/*
The MIT License (MIT)

Copyright (c) 2016 Aaron A. Ploetz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aaronstechcenter.experfymodule9;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author aploetz
 */
public class AuthorSvc {
    private Session session;
    
    public AuthorSvc() {
    }
    
    public AuthorSvc(Session _session) {
        session = _session;
    }
    
    public void getBooksByAuthor(String _author) {
                String strCQL = "SELECT author,title,isbn,publisher,year "
                + "FROM experfy_class.books_by_author "
                + "WHERE author=?";
        PreparedStatement statement = session.prepare(strCQL);
        BoundStatement boundStatement = new BoundStatement(statement);
        boundStatement.bind(_author);
        
        System.out.println();
        
        ResultSet results = session.execute(boundStatement);
        for (Row row : results) {
            System.out.format("%s %25s %s %20s %d \n",
                    row.getString("author"), 
                    row.getString("title"),
                    row.getString("isbn"),
                    row.getString("publisher"),
                    row.getLong("year")
            );
        }
    }
}
