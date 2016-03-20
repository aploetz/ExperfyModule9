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
    private Cluster cluster;
    private Session session;
    private String password;
    private String username;
    private String[] nodeList;

    public CassandraDAO(String[] _nodes, String _username, String _password) {
        nodeList = _nodes;
        username = _username;
        password = _password;
        
        setCluster();
    }

    private void setCluster() {
        if (cluster == null) {
            cluster = connect();
        }
    }

    public Session getSession() {
        if (cluster == null) {
            setCluster();
            session = cluster.connect();
        } else if (session == null) {
            session = cluster.connect();
        }

        return session;
    }

    private Cluster connect() {
        QueryOptions queryOptions = new QueryOptions()
            .setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);

        DCAwareRoundRobinPolicy dcPol = new DCAwareRoundRobinPolicy.Builder()
            .withLocalDc("AaronsLab")
            .build();
        
        cluster = Cluster.builder()
            .addContactPoints(nodeList)
            .withSSL()
            .withCredentials(username, password)
            .withQueryOptions(queryOptions)
            .withLoadBalancingPolicy(new TokenAwarePolicy(dcPol))
            .build();
        
        return cluster;
    }
   
    public void closeConnection() {
        cluster.close();
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
