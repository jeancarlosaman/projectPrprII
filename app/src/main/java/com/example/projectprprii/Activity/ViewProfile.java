package com.example.projectprprii.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.projectprprii.Activity.WishLists.ViewWishLists;
import com.example.projectprprii.Entities.User;
import com.example.projectprprii.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView emailTextView;
    private TextView numGifts;
    private TextView numEvents;
    private Button goBackButton;
    private Button viewWishlistsButton;

    private String apiUrl = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/{id}/gifts/reserved";
    private String apiUrl2 = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/{id}/wishlists";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        // Initialize views
        userNameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.mailTextView);
        goBackButton = findViewById(R.id.viewProfileButton);

        numGifts = findViewById(R.id.numGiftsTv);
        numEvents = findViewById(R.id.numEventsTv);
        viewWishlistsButton = findViewById(R.id.viewWishListsButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfile.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        User user = User.getAuthenticatedUser();
        userNameTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
        getNumGifts();
        getNumEvents();

        viewWishlistsButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(ViewProfile.this, ViewWishLists.class);
            intent1.putExtra("id", User.getAuthenticatedUser().getId());
            startActivity(intent1);
        });
    }

    private void getNumGifts() {
        System.out.println(apiUrl.replace("{id}", Integer.toString(User.getAuthenticatedUser().getId())));

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, apiUrl.replace("{id}", Integer.toString(User.getAuthenticatedUser().getId())), null,
                response -> {
                    System.out.println(response);
                    numGifts.setText(Integer.toString(response.length()));
                },
                error -> {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + User.getAuthenticatedUser().getToken());
                return headers;
            }
        };
        VolleySingletone.getInstance(this).addToRequestQueue(request);
    }

    private void getNumEvents() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, apiUrl2.replace("{id}", Integer.toString(User.getAuthenticatedUser().getId())), null,
                response -> {
                    System.out.println(response);
                    numEvents.setText(Integer.toString(response.length()));
                },
                error -> {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + User.getAuthenticatedUser().getToken());
                return headers;
            }
        };
        VolleySingletone.getInstance(this).addToRequestQueue(request);
    }
}
