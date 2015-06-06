package com.fleato.asadullah.event;


import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;



import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class FacebookFragment extends Fragment{

    private CallbackManager mcallBackManager;
    private FacebookCallback<LoginResult> mCallBack=new FacebookCallback<LoginResult>() {
        @Override
 
        public void onSuccess(LoginResult loginResult) {

            final AccessToken accessToken=loginResult.getAccessToken();
            Profile profile=Profile.getCurrentProfile();


            if(profile!=null){

                final User[] u = new User[1];
                final String[][] arr = new String[1][1];
               final Gson gson = new Gson();
                final Context context=getActivity().getApplicationContext();
                Log.v("verbose", "loggedin");
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {


                                u[0] = gson.fromJson(object.toString(), User.class);

                                u[0].setAccessToken(accessToken);
                                String json = gson.toJson(u[0]);
                                Toast toast = Toast.makeText(context, u[0].toString(),Toast.LENGTH_LONG);
                                toast.show();
                                Log.v("verbose",u[0].toString());

                                SharedPreferences sharedPreferences= context.getSharedPreferences("mydata",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("user", json);
                                editor.commit();
                                getActivity().finish();

                                //editor.putString("email",object)
                                // Application code
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");


                request.setParameters(parameters);
                request.executeAsync();








/*                GraphRequestAsyncTask graphRequest = new GraphRequest(
                        accessToken,
                        "/me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                Log.v("verbose", response.toString());

                            }
                        }
                ).executeAsync();
                */





















            }



        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
    public  FacebookFragment(){

    }
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

//        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        mcallBackManager=CallbackManager.Factory.create();
        //LoginManager.getInstance().logOut();// remove this line

    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstaceState){
        return inflater.inflate(R.layout.facebook_fragment,container,false);

    }
    public void onViewCreated (View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        LoginButton loginButton=(LoginButton) view.findViewById(R.id.login_button);
        List<String> permissions = new ArrayList<String>();
        permissions.add("email");
        permissions.add("user_friends");

        loginButton.setReadPermissions(permissions);
        loginButton.setFragment(this);
        loginButton.registerCallback(mcallBackManager,mCallBack);

    }
    public  void onActivityResult(int requestCode,int resultCode,Intent data){


        super.onActivityResult(requestCode,resultCode,data);
        mcallBackManager.onActivityResult(requestCode,resultCode,data);
    }



}
