package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText brand;
    private EditText model;
    private EditText price;
    private Button saveButton;
    private TextView txtData;
    private Button allData;
    private String allBil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        allData=findViewById(R.id.allData);
        brand=findViewById(R.id.brand);
        model=findViewById(R.id.model);
        price=findViewById(R.id.price);
        saveButton=findViewById(R.id.saveButton);
        txtData=findViewById(R.id.txtData);
        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("Bil");
//                parseQuery.getInBackground("aWRAG6aIAj", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if(object !=null && e == null){
//                            txtData.setText(object.get("Brand")+" "+object.get("Model")+" "+object.get("Price"));
//                        }
//                    }
//                });

                ParseQuery<ParseObject> query=ParseQuery.getQuery("Bil");
                query.getInBackground("l5bIPIq2Me", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e == null){
                            txtData.setText(object.get("Brand")+" "+object.get("Model")+" "+object.get("Price"));
                        }
                    }
                });
            }
        });

        allData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> queryAll=ParseQuery.getQuery("Bil");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        allBil="";
                        if( e ==null){
                            if(objects.size()>0){
                                for(ParseObject p:objects) {

                                    allBil+=p.get("Brand")+" "+p.get("Model")+" "+p.get("Price")+"\n";



                                }
                                txtData.setText(allBil);
                                FancyToast.makeText(SignUpActivity.this,allBil.toString(), FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();


                            }else {
                                FancyToast.makeText(SignUpActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            }
                        }
                    }
                });
            }
        });


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
