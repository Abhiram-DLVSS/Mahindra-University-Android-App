package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Intents extends AppCompatActivity {

    public void statusbar(){
        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));
    }
    public void openFaculty() {
        Intent intent=new Intent(this,FacultyMenu.class);
        startActivity(intent);
    }
    //Below both are for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        switch(id){
            case R.id.logout_in_menu: {
                Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
            break;
        }
        return true;
    }

    public void openTimeTable() {
        Intent intent=new Intent(this, TimeTable.class);
        startActivity(intent);
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openForms()
    {
        Intent intent=new Intent(this, Forms.class);
        startActivity(intent);
    }
    public void openEvent()
    {
        ProgressDialog nDialog;
        nDialog = new ProgressDialog(Intents.this);
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        Intent intent=new Intent(this, Event.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void moveToMainActivity() {
        Intent intent=new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}