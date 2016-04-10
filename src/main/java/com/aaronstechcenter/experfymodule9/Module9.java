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

/**
 *
 * @author aploetz
 */
public class Module9 {
    public static void main(String[] args) {
        //define Cassandra connection
        
        String[] nodeList = {"192.168.0.100"};
        String username = "module9";
        String password = "6Bg|~fs9Ji79";
        String dataCenter = "AaronsLab";
        
        CassandraDAO cassDao = CassandraDAO.getIntance(nodeList,username,password,dataCenter);

        BookAuthorDAO bookAuthorDao = new BookAuthorDAO(cassDao.getSession());

        bookAuthorDao.getBooksByAuthor("Tom Clancy");
        
//        bookAuthorDao.upsertBookByAuthor("Rick Greenwald", "Oracle Essentials", 
//                4, "978-0-596-51454-9", "O'Reilly", 2008);
        
//        bookAuthorDao.deleteBookByAuthor("Rick Greenwald", "Oracle Essentials", 4);
//        
//        System.out.println();
//        
//        bookAuthorDao.getBooksByAuthorQB("David A. Lien");
//        bookAuthorDao.getBooksByAuthor("Rick Greenwald");
//        bookAuthorDao.getBookByAuthor("Rick Greenwald", "Oracle Essentials", 4);
//        
//        bookAuthorDao.saveBookByAuthor("Ernest Cline", "Ready Player One", 1,
//                "978-0307887443", "Broadway Books", 2012);
//        bookAuthorDao.getBookByAuthor("Ernest Cline", "Ready Player One", 1);
//        
//        SecurityLogDAO securityLogDao = new SecurityLogDAO(cassDao.getSession());
//
//        securityLogDao.upsertSecurityLogs("NC2B", "A99844", "Sam", "Flynn");
//        
//        securityLogDao.getSecurityLogsByLocation("NC2B");
        
        bookAuthorDao.closeConnection();
        //securityLogDao.closeConnection();
        cassDao.closeConnection();
    }
}
