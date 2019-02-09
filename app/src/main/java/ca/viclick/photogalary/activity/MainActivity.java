package ca.viclick.photogalary.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.viclick.photogalary.R;


public class MainActivity extends AppCompatActivity {


    private EditText mKeyWord;
    private Button mSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKeyWord = findViewById(R.id.keyword);
        mSearch = findViewById(R.id.search);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPhotos();
            }
        });
    }

    private void searchPhotos() {
        String keyWord = mKeyWord.getText().toString();

        if (keyWord.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Search criteria is empty");
            builder.setTitle("Error");
            builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).show();

            return;
        }

        Intent intent = new Intent(this, PhotoGalleryActivity.class);
        intent.putExtra("KEY_WORD", keyWord);
        startActivity(intent);

    }


}
