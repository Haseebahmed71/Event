package com.fleato.asadullah.event;

/**
 * Created by Asadullah on 6/6/2015.
 */

import com.facebook.AccessToken;

/**
 * Created by Asadullah on 6/6/2015.
 */
public class User
{
    private String id;

    private AccessToken accessToken;

    private String email;

    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public AccessToken getAccessToken ()
    {
        return accessToken;
    }

    public void setAccessToken (AccessToken accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "[id = "+id+", accessToken = "+accessToken.toString()+", email = "+email+", name = "+name+"]";
    }
}