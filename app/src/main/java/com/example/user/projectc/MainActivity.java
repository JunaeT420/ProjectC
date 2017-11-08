package com.example.user.projectc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void button_alert(View view){
        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText userHouse = (EditText) alertLayout.findViewById(R.id.user_house);
        final EditText userRoad = (EditText) alertLayout.findViewById(R.id.user_road);
        final EditText userArea = (EditText) alertLayout.findViewById(R.id.user_ares);
        final EditText userCity = (EditText) alertLayout.findViewById(R.id.user_city);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Insert address");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cencle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();

            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String house = userHouse.getText().toString();
                String road = userRoad.getText().toString();
                String area = userArea.getText().toString();
                String city = userCity.getText().toString();


                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("house",house);
                intent.putExtra("road",road);
                intent.putExtra("area",area);
                intent.putExtra("city",city);
                startActivity(intent);


            }
        });

        AlertDialog dialog=alert.create();
        dialog.show();
    }

}





































