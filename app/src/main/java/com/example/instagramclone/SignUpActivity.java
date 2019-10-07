package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {

    private EditText brand;
    private EditText model;
    private EditText price;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        brand=findViewById(R.id.brand);
        model=findViewById(R.id.model);
        price=findViewById(R.id.price);
        saveButton=findViewById(R.id.saveButton);


    }


    public void saveCar(View v){
        final ParseObject car=new ParseObject("Bil");

        car.put("Brand",brand.getText().toString());
        car.put("Model",model.getText().toString());
        car.put("Price",price.getText().toString());
        car.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                   // Toast.makeText(SignUpActivity.this, car.get("Brand") + " is saved to database", Toast.LENGTH_SHORT).show();
                    FancyToast.makeText(SignUpActivity.this,car.get("Brand") + " is saved to database", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                }else{
                    FancyToast.makeText(SignUpActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                }
            }
        });
    }



    public void helloWorldTap(View view){

    final ParseObject myCar= new ParseObject("Car");
    myCar.put("Merke","WV");
    myCar.put("Model","E-Golf");
    myCar.put("Price",345000);
    myCar.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e == null ){
                Toast.makeText(SignUpActivity.this, myCar.get("Merke")+" is saved successfully", Toast.LENGTH_SHORT).show();
            }
        }
    });

//        ParseObject boxer=new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null ){
//                    Toast.makeText(SignUpActivity.this,"boxer object is saved successfully",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



    }
}
