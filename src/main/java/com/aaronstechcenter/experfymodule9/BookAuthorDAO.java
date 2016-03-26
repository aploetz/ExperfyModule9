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
        session = _session;
        
        bookMapper = new MappingManager(session).mapper(Book.class);
    }
    
    public void getBooksByAuthor(String _author) {
        String strCQL = "SELECT author,title,isbn,publisher,year "
            + "FROM experfy_class.books_by_author "
            + "WHERE author=?";
        
        try {
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
        } catch (Exception ex) {
            System.out.println("No books found for this author");
        }
    }

    public void getBooksByAuthorQB(String _author) {
        Statement select = QueryBuilder.select()
            .from("experfy_class","books_by_author")
            .where(QueryBuilder.eq("author", _author));
        
        ResultSet results = session.execute(select);
        
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
    
    public void upsertBookByAuthor(String _author, String _title,
            long _edition, String _isbn, String _publisher, long _year)
    {
        String strCQL = "INSERT INTO experfy_class.books_by_author "
                + "(isbn,publisher,year,author,title,edition) "
                + "VALUES (?,?,?,?,?,?)";

//        String strCQL = "UPDATE experfy_class.books_by_author "
//                + "SET isbn=?, publisher=?, year=? "
//                + "WHERE author=? AND title=? AND edition=?";
        
        PreparedStatement statement = session.prepare(strCQL);
        BoundStatement boundStatement = new BoundStatement(statement);
        boundStatement.bind(_isbn,_publisher,_year,_author,_title,_edition);
        
        session.execute(boundStatement);
    }
    
    public void deleteBookByAuthor(String _author, String _title, long _edition) {
        String strCQL = "DELETE FROM experfy_class.books_by_author "
                + "WHERE author=? AND title=? AND edition=?";
        
        PreparedStatement statement = session.prepare(strCQL);
        BoundStatement boundStatement = new BoundStatement(statement);
        boundStatement.bind(_author,_title,_edition);
        
        session.execute(boundStatement);
    }
    
    public void getBookByAuthor(String _author, String _title, long _edition) {
        try {
            System.out.println();

            Book result = bookMapper.get(_author,_title,_edition);

            System.out.format("%s %25s %s %20s %d \n",
                result.getAuthor(), 
                result.getTitle(),
                result.getIsbn(),
                result.getPublisher(),
                result.getYear()
            );
        } catch (Exception ex) {
            System.out.println("Book not found.");
        }
    }
    
    public void saveBookByAuthor(String _author, String _title,
            long _edition, String _isbn, String _publisher, long _year) {
        System.out.println();
        
        Book book = new Book(_author, _title, _edition, _isbn, _publisher, _year);
        
        bookMapper.save(book);
    }
    
    public void closeConnection() {
        session.close();
    }
}
