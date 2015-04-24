package com.herokuapp.ezhao.datejar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.herokuapp.ezhao.datejar.fragments.MainFragment;


public class MainActivity extends ActionBarActivity implements MainFragment.ShowIdeaListener {
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        ft.replace(R.id.flFragment, mainFragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showIdea(final Idea idea) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(idea.ideaText)
               .setTitle(R.string.show_idea_dialog_title)
               .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       idea.delete();
                       dialog.dismiss();
                   }
               })
               .setNegativeButton(R.string.next_time, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               })
               .create().show();
    }
}
