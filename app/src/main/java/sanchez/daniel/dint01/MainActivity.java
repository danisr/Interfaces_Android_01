package sanchez.daniel.dint01;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ImageView imageView;
    private boolean witchImage;
    private LinearLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.mainImageView);
        backgroundLayout = (LinearLayout) findViewById(R.id.background_layout);
        witchImage = false;
    }

    /**
     * Actions for each button
     */
    public void changeText(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final TextView mainTextView = (TextView) findViewById(R.id.mainTextView);
        final EditText edittext = new EditText(this);
        alert.setMessage(getString(R.string.insert_new_text));
        alert.setTitle(getString(R.string.new_text));
        alert.setView(edittext);
        alert.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mainTextView.setText(edittext.getText().toString());
            }
        });

        alert.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });
        alert.show();
    }

    public void closeAppAction(View view) {
        Toast.makeText(this, getString(R.string.bye_text), Toast.LENGTH_LONG).show();
        finish();
    }

    public void showProgressDialog(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.loading));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(true);
        progress.show();
    }

    public void changeImage(View view) {
        witchImage = !witchImage;
        Drawable imageDrawable = null;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageDrawable = getResources().getDrawable(witchImage ? R.drawable.utad2 : R.drawable.utad);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageDrawable = getResources().getDrawable(witchImage ? R.drawable.utad2 : R.drawable.utad, getTheme());
            }
        }
        imageView.setImageDrawable(imageDrawable);
    }

    public void changeBackground(View view) {
        int time = (int) (System.currentTimeMillis() % 3);
        switch (time) {
            case 0:
                backgroundLayout.setBackgroundColor(Color.GREEN);

                break;
            case 1:
                backgroundLayout.setBackgroundColor(Color.RED);

                break;
            case 2:
                backgroundLayout.setBackgroundColor(Color.BLUE);
                break;
        }
    }

    public void hideImage(View view) {
        if (imageView.getVisibility() == View.INVISIBLE) {
            imageView.setVisibility(View.VISIBLE);
            ((Button) view).setText(getString(R.string.hide_image));
        } else {
            imageView.setVisibility(View.INVISIBLE);
            ((Button) view).setText(getString(R.string.show_image));
        }
    }

    public void removeImage(View view) {
        if (imageView.getVisibility() == View.GONE) {
            imageView.setVisibility(View.VISIBLE);
            ((Button) view).setText(getString(R.string.remove_image));
        } else {
            imageView.setVisibility(View.GONE);
            ((Button) view).setText(getString(R.string.show_image));
        }
    }
}
