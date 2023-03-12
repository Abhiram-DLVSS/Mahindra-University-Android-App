// Copyright (c) Microsoft Corporation.
// All rights reserved.
//
// This code is licensed under the MIT License.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files(the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions :
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package mu.MSAuth;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.MU.R;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.SilentAuthenticationCallback;
import com.microsoft.identity.client.exception.MsalException;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import mu.DatabaseHelper;
import mu.timetable.TimeTable;

public class SingleAccountModeFragment extends Fragment {
    private static final String TAG = SingleAccountModeFragment.class.getSimpleName();

    /* UI & Debugging Variables */
    Button signInButton;
    String scopeTextView = "user.read";
    String graphResourceTextView;

    LottieAnimationView unlock, rejected, checkmark;
    /* Azure AD Variables */
    private ISingleAccountPublicClientApplication mSingleAccountApp;
    private IAccount mAccount;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_single_account_mode, container, false);
        initializeUI(view);

        // Creates a PublicClientApplication object with res/raw/auth_config_single_account.json
        PublicClientApplication.createSingleAccountPublicClientApplication(getContext(),
                R.raw.auth_config_single_account,
                new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {
                        /**
                         * This test app assumes that the app is only going to support one account.
                         * This requires "account_mode" : "SINGLE" in the config json file.
                         **/
                        mSingleAccountApp = application;
                        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
                        Cursor data = mDatabaseHelper.getData();


                        if (!data.moveToNext()) {
                            signout();
                        }
                        loadAccount();
                    }

                    @Override
                    public void onError(MsalException exception) {
                        displayError(exception);
                    }
                });


        return view;
    }

    /**
     * Initializes UI variables and callbacks.
     */
    private void initializeUI(@NonNull final View view) {
        signInButton = view.findViewById(R.id.Login);
        checkmark = view.findViewById(R.id.checkmark);
        rejected = view.findViewById(R.id.rejected);
        unlock = view.findViewById(R.id.unlock);
        checkmark.setVisibility(View.INVISIBLE);
        final String defaultGraphResourceUrl = MSGraphRequestWrapper.MS_GRAPH_ROOT_ENDPOINT + "v1.0/me";
        graphResourceTextView = "" + defaultGraphResourceUrl;

        signInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getActiveNetworkInfo() != null) {

                    if (mSingleAccountApp == null) {
                        return;
                    }
                    signInButton.setVisibility(View.INVISIBLE);
                    unlock.setVisibility(View.VISIBLE);
                    unlock.playAnimation();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlock.pauseAnimation();
                            mSingleAccountApp.signIn(getActivity(), null, getScopes(), getAuthInteractiveCallback());
                        }
                    }, 1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlock.resumeAnimation();
                        }
                    }, 5000);
                } else {
                    noInternetSnackBar(view);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * The account may have been removed from the device (if broker is in use).
         *
         * In shared device mode, the account might be signed in/out by other apps while this app is not in focus.
         * Therefore, we want to update the account state by invoking loadAccount() here.
         */
        loadAccount();
    }

    /**
     * Extracts a scope array from a text field,
     * i.e. from "User.Read User.ReadWrite" to ["user.read", "user.readwrite"]
     */
    private String[] getScopes() {
        return scopeTextView.toLowerCase().split(" ");
    }

    /**
     * Load the currently signed-in account, if there's any.
     */
    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                // You can use the account data to update your UI or your app database.
                mAccount = activeAccount;
//                updateUI();
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {
                    // Perform a cleanup task as the signed-in account changed.
                    showToastOnSignOut();
                }
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
            }
        });
    }

    /**
     * Callback used in for silent acquireToken calls.
     */
    private SilentAuthenticationCallback getAuthSilentCallback() {
        return new SilentAuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                unlock.playAnimation();
                callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */

                Toast.makeText(getContext(), "Failed out", Toast.LENGTH_SHORT).show();
                displayError(exception);
            }
        };
    }

    /**
     * Callback used for interactive request.
     * If succeeds we use the access token to call the Microsoft Graph.
     * Does not check cache.
     */
    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {

                mAccount = authenticationResult.getAccount();
                callGraphAPI(authenticationResult);
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString());
                displayError(exception);
            }

            @Override
            public void onCancel() {
                /* User canceled the authentication */
                rejected();
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    /**
     * Make an HTTP request to obtain MSGraph data
     */
    private void callGraphAPI(final IAuthenticationResult authenticationResult) {
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                graphResourceTextView,
                authenticationResult.getAccessToken(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /* Successfully called graph, process data and send to UI */
                        Log.d(TAG, "Response: " + response.toString());
                        DatabaseHelper mDatabaseHelper;
                        mDatabaseHelper = new DatabaseHelper(getContext());
                        try {
                            String mail = response.getString("mail");
                            int index = mail.indexOf("@");
                            mDatabaseHelper.addData("19XJ1A0" + mail.substring(index - 3, index),mail.substring(0,index - 5));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        signOutButton.performClick();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent homepage = new Intent(getContext(), TimeTable.class);
                                homepage.putExtra("Uniqid","Splash");
                                startActivity(homepage);
                                getActivity().finish();
                            }
                        }, 2100);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.toString());
                        displayError(error);
                    }
                });
    }

    public void signout() {
        mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
            @Override
            public void onSignOut() {
                mAccount = null;
                showToastOnSignOut();
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                displayError(exception);
            }
        });

    }

    /**
     * Display the error message
     */
    private void displayError(@NonNull final Exception exception) {
    }

    private void showToastOnSignOut() {
        Toast.makeText(getContext(), "Signed out", Toast.LENGTH_SHORT).show();
    }

    private void rejected() {
        signInButton.setVisibility(View.INVISIBLE);
        rejected.setVisibility(View.VISIBLE);
        rejected.playAnimation();
        unlock.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rejected.setVisibility(View.INVISIBLE);
                signInButton.setVisibility(View.VISIBLE);
            }
        }, 2500);
        signInButton.setEnabled(true);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("8.8.8.8");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }

    public void noInternetSnackBar(View view) {
        ConstraintLayout constraintLayout = view.findViewById(R.id.loginview);
        Snackbar snackbar = Snackbar
                .make(constraintLayout, "Check your Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signInButton.performClick();
                    }
                });
        snackbar.setActionTextColor(getResources().getColor(R.color.mu));
        snackbar.show();
        signInButton.setEnabled(true);
    }
}
