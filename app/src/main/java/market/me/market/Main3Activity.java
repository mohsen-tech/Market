package market.me.market;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Main3Activity extends AppCompatActivity {

    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    private EditText tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    private Button sabt;
    EditText name;
    EditText phone;
    EditText email;
    EditText nameshop;
    EditText place;
    EditText telephone;
    EditText tavalod;
    int id_To_Update = 0;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setCurrentDateOnView();
        addListenerOnButton();


        name = (EditText) findViewById(R.id.namefamily);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        nameshop = (EditText) findViewById(R.id.nameshop);
        place = (EditText) findViewById(R.id.adreas);
        telephone = (EditText) findViewById(R.id.telephone);
        tavalod = (EditText) findViewById(R.id.tavalod);

        sabt = (Button) findViewById(R.id.sabt);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                String namesho = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAMESHOP));
                String plac = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PLACE));
                String tele = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_TELEPHONE));
                String taval = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_TAVALOD));

                if (!rs.isClosed()) {
                    rs.close();
                }

                sabt.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) nam);
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);

                email.setText((CharSequence) emai);
                email.setFocusable(false);
                email.setClickable(false);

                nameshop.setText((CharSequence) namesho);
                nameshop.setFocusable(false);
                nameshop.setClickable(false);

                place.setText((CharSequence) plac);
                place.setFocusable(false);
                place.setClickable(false);

                telephone.setText((CharSequence) tele);
                telephone.setFocusable(false);
                telephone.setClickable(false);

                tavalod.setText((CharSequence) taval);
                tavalod.setFocusable(false);
                tavalod.setClickable(false);
            }
        }


    }

    public void setCurrentDateOnView() {

        tvDisplayDate = (EditText) findViewById(R.id.tavalod);
        dpResult = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvDisplayDate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        dpResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.button4);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            tvDisplayDate.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

            dpResult.init(year, month, day, null);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {

                getMenuInflater().inflate(R.menu.display_contact, menu);

            } else {

                getMenuInflater().inflate(R.menu.main_menu, menu);

            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:

                sabt.setVisibility(View.VISIBLE);

                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                nameshop.setEnabled(true);
                nameshop.setFocusableInTouchMode(true);
                nameshop.setClickable(true);

                place.setEnabled(true);
                place.setFocusableInTouchMode(true);
                place.setClickable(true);

                telephone.setEnabled(true);
                telephone.setFocusableInTouchMode(true);
                telephone.setClickable(true);

                tavalod.setEnabled(true);
                tavalod.setFocusableInTouchMode(true);
                tavalod.setClickable(true);


                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateContact(id_To_Update, name.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),
                        nameshop.getText().toString(), place.getText().toString(),
                        telephone.getText().toString(), tavalod.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertContact(name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), nameshop.getText().toString(),
                        place.getText().toString(), telephone.getText().toString(), tavalod.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}