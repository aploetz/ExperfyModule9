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

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;

/**
 *
 * @author aploetz
 */
public class CassandraDAO {
    private static Cluster cluster;
    private Session session;
    private static String password;
    private static String username;
    private static String[] nodeList;
    private static String dataCenter;

    private static CassandraDAO instance = null;

    private CassandraDAO() {
       // Exists only to defeat instantiation.
    }
   
    public static CassandraDAO getIntance(String[] _nodes, String _username, String _password, String _dataCenter) {
        if(instance == null) {
            instance = new CassandraDAO();

            
            setCluster();
        }

        return instance;
    }

    private static void setCluster() {

    }

    public Session getSession() {

    }

    private static Cluster connect() {

        
    }
   
    public void closeConnection() {

    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String _password) {
        password = _password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String _username) {
        username = _username;
    }
    
    public String[] getNodeList() {
        return nodeList;
    }
    
    public void setNodeList(String[] _nodeList) {
        nodeList = _nodeList;
    }
}
