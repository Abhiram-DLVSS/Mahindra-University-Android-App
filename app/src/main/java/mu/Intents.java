package mu;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;

import com.MU.R;

import mu.Faculty.FacultyMenu;
import mu.Forms.Forms;
import mu.timetable.TimeTable;
import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class Intents extends AppCompatActivity {

    public FloatingActionButton timetable_button, faculty, fee, event, grades, forms, euclid;
    public FloatingActionsMenu floatingmenu;
    public ConstraintLayout tt;
    public View background;
    public NestedScrollView fab_scroll;

    public void floatinginit() {
        //Initializations of FAB menu
        floatingmenu = findViewById(R.id.fm_menu);
        fee = findViewById(R.id.fm_fees);
        event = findViewById(R.id.fm_events);
        faculty = findViewById(R.id.fm_faculty);
        euclid = findViewById(R.id.fm_euclid);
        timetable_button = findViewById(R.id.fm_timetable);
        tt = findViewById(R.id.tt_fm);
        grades = findViewById(R.id.fm_grades);
        background = findViewById(R.id.background_dimmer);
        fab_scroll = findViewById(R.id.fab_menu_scroll);
        forms = findViewById(R.id.fm_forms);

        //When clicked anywhere on the FAB menu, collapse it
        floatingmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingmenu.collapse();
            }
        });
        //When clicked on the black background behind the FAB menu
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingmenu.collapse();
            }
        });
        //When
        floatingmenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                timetable_button.setVisibility(View.VISIBLE);
                fee.setVisibility(View.VISIBLE);
                faculty.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                grades.setVisibility(View.VISIBLE);
                forms.setVisibility(View.VISIBLE);
                background.setVisibility(View.VISIBLE);
                euclid.setVisibility(View.VISIBLE);
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_multip, null));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        fab_scroll.scrollTo(0, fab_scroll.getBottom());
                    }
                }, 50);
            }

            @Override
            public void onMenuCollapsed() {
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hamburger_icon_svg, null));
                timetable_button.setVisibility(View.GONE);
                fee.setVisibility(View.GONE);
                faculty.setVisibility(View.GONE);
                forms.setVisibility(View.GONE);
                event.setVisibility(View.GONE);
                euclid.setVisibility(View.GONE);
                grades.setVisibility(View.GONE);
                background.setVisibility(View.GONE);
            }
        });

        //Floating Menu Button Clicks
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvent();
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFaculty();
            }
        });
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForms();
            }
        });
        euclid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEuclid();
            }
        });
        euclid.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://euclid-mu.in/");
                clipboard(copyUri);
                toast();
                return true;
            }
        });
        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
            }
        });
        fee.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
                clipboard(copyUri);
                toast();
                return true;
            }
        });
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://muerp.mahindrauniversity.edu.in/");
            }
        });
        grades.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://muerp.mahindrauniversity.edu.in/");
                clipboard(copyUri);
                toast();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (floatingmenu.isExpanded())
            floatingmenu.collapse();
        else
            this.finish();
    }

    public void openFaculty() {
        Intent intent = new Intent(this, FacultyMenu.class);
        startActivity(intent);
        floatingmenu.collapse();
    }

    public void openForms() {
        Intent intent = new Intent(this, Forms.class);
        startActivity(intent);
        floatingmenu.collapse();
    }

    public void statusbar() {
        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));
    }

    //Below both are for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void openTimeTable() {
        Intent intent = new Intent(this, TimeTable.class);
        startActivity(intent);
        floatingmenu.collapse();
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
        floatingmenu.collapse();
    }

    public void openEvent() {
        ProgressDialog nDialog;
        nDialog = new ProgressDialog(Intents.this);
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        floatingmenu.collapse();
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
        nDialog.dismiss();
    }

    public void openEuclid() {
        ProgressDialog nDialog;
        nDialog = new ProgressDialog(Intents.this);
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        floatingmenu.collapse();
        Intent intent = new Intent(this, Euclid.class);
        startActivity(intent);
        nDialog.dismiss();
    }

    public void vibrate() {
        // Get instance of Vibrator from current Context
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 400 milliseconds
        vib.vibrate(40);
    }

    public void clipboard(Uri uri) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newUri(getContentResolver(), "URI", uri);
        clipboard.setPrimaryClip(clip);
    }

    public void toast() {
        Toast toast = Toast.makeText(getApplicationContext(), "Link Copied to Clipboard", Toast.LENGTH_SHORT);
        toast.show();
        vibrate();
    }
}