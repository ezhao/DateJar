package com.herokuapp.ezhao.datejar.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.herokuapp.ezhao.datejar.Idea;
import com.herokuapp.ezhao.datejar.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainFragment extends Fragment {
    @InjectView(R.id.etNewIdea) EditText etNewIdea;
    Activity listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        etNewIdea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // save idea
                    Idea idea = new Idea(v.getText().toString());
                    idea.save();

                    // reset state of text field and keyboard
                    v.setText("");
                    InputMethodManager imm =(InputMethodManager) listener.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });
        return view;
    }

    @OnClick(R.id.btnPullIdea)
    public void onPullIdea(View view) {
        Idea idea = Idea.getRandom();
        Log.i("EMILY", idea.ideaText);
    }
}
