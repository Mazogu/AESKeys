package com.example.micha.roomwordsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        edit = findViewById(R.id.editword);
        final Button button = findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(edit.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                }
                else{
                    String word = edit.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,word);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
    }
}
