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
public class CassandraDAO {
    private static Cluster Cluster;
    private static ISession Session;

    public CassandraDAO()
    {
        SetCluster();
    }

    private void SetCluster()
    {
        if (Cluster == null)
        {
            Cluster = Connect();
        }
    }

    public ISession GetSession()
    {
        if (Cluster == null)
        {
            SetCluster();
            Session = Cluster.Connect();
        }
        else if (Session == null)
        {
            Session = Cluster.Connect();
        }

        return Session;
    }

    private Cluster Connect()
    {
        string user = getAppSetting("cassandraUser");
        string pwd = getAppSetting("cassandraPassword");
        string[] nodes = getAppSetting("cassandraNodes").Split(',');

        QueryOptions queryOptions = new QueryOptions()
            .SetConsistencyLevel(ConsistencyLevel.One);

        Cluster cluster = Cluster.Builder()
            .AddContactPoints(nodes)
            .WithCredentials(user, pwd)
            .WithQueryOptions(queryOptions)
            .Build();

        return cluster;
    }

    private string getAppSetting(string key)
    {
        return System.Configuration.ConfigurationManager.AppSettings[key];
    }
}
