package market.me.market;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int CAMERA_REQUEST = 2;
    ImageView image;
    String[] strings = {"super", "market", "hyper"};
    public String masol;
    public String qeymat;
    public String tedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button photoButton = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView1);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        EditText a = (EditText) findViewById(R.id.editText);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        a.setTypeface(typeface);

        EditText b = (EditText) findViewById(R.id.editText2);

        b.setTypeface(typeface);

        EditText c = (EditText) findViewById(R.id.editText3);

        c.setTypeface(typeface);


        masol = a.getText().toString();

        qeymat = b.getText().toString();

        tedad = c.getText().toString();


        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setTypeface(typeface);

        Button Intntt = (Button) findViewById(R.id.button2);
        Intntt.setOnClickListener(new View.OnClickListener()

                                  {

                                      @Override
                                      public void onClick(View view) {

                                          Intent a = new Intent(MainActivity.this, Main2Activity.class);
                                          startActivity(a);
                                      }
                                  }

        );

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, strings);
        adapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);


        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                     {

                                         @Override
                                         public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                                             Toast.makeText(MainActivity.this, "Entekhab shoma: " + strings[position], Toast.LENGTH_SHORT).show();
                                         }

                                         @Override
                                         public void onNothingSelected(AdapterView<?> arg0) {

                                         }
                                     }

        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);

        }
    }
}