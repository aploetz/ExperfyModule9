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
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 *
 * @author aploetz
 */
public class BookAuthorDAO {
    private Session session;
    private Mapper<Book> bookMapper;
        
    public BookAuthorDAO(Session _session) {

        
    }
    
    public void getBooksByAuthor(String _author) {

        
    }

    public void getBooksByAuthorQB(String _author) {

        
    }
    
    public void upsertBookByAuthor(String _author, String _title,
            long _edition, String _isbn, String _publisher, long _year)
    {

        
    }
    
    public void deleteBookByAuthor(String _author, String _title, long _edition) {

        
    }
    
    public void getBookByAuthor(String _author, String _title, long _edition) {

        
    }
    
    public void saveBookByAuthor(String _author, String _title,
            long _edition, String _isbn, String _publisher, long _year) {

        
    }
    
    public void closeConnection() {
        session.close();
    }
}
