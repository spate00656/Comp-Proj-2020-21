package com.woa.RepMaxCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.woa.databinding.ActivityRepMaxCalculatorBinding;

import java.util.Objects;

public class RepMaxCalculatorActivity extends AppCompatActivity {

    private ActivityRepMaxCalculatorBinding binding;
    int countRep, totalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepMaxCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        binding.calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(binding.repsEt.getText()).toString().isEmpty()) {
                    Toast toast = Toast.makeText(RepMaxCalculatorActivity.this, "Enter Reps", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
                if (Objects.requireNonNull(binding.weightKgsEt.getText()).toString().isEmpty()) {
                    Toast toast = Toast.makeText(RepMaxCalculatorActivity.this, "Enter weight kgs", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                } else {
                    String repCount = binding.repsEt.getText().toString();
                    countRep = Integer.parseInt(repCount);
                    if (countRep <= 20) {
                        initialization();

                    } else {
                        Toast toast = Toast.makeText(RepMaxCalculatorActivity.this, "Please Enter Maximum 20 Reps", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }


                }
            }
        });


    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void initialization() {

        int Rep1 = 1;
        double Rep2 = 0.97;
        double Rep3 = 0.94;
        double Rep4 = 0.92;
        double Rep5 = 0.89;
        double Rep6 = 0.86;
        double Rep7 = 0.83;
        double Rep8 = 0.81;
        double Rep9 = 0.78;
        double Rep10 = 0.75;
        double Rep11 = 0.73;
        double Rep12 = 0.71;
        double Rep13 = 0.70;
        double Rep14 = 0.68;
        double Rep15 = 0.67;
        double Rep16 = 0.65;
        double Rep17 = 0.64;
        double Rep18 = 0.63;
        double Rep19 = 0.61;
        double Rep20 = 0.60;
        String weight = binding.weightKgsEt.getText().toString();
        totalWeight = Integer.parseInt(weight);

        if (countRep == 1) {
            binding.linearView.setVisibility(View.VISIBLE);
            float calculate = totalWeight / Rep1;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");
            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");




        } else if (countRep == 2) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep2;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");
            double cal2 = calculate * Rep2;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");




        } else if (countRep == 3) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep3;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");


        } else if (countRep == 4) {

            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep4;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");
            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");








        } else if (countRep == 5) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep5;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");





        } else if (countRep == 6) {


            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep6;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 7) {


            binding.linearView.setVisibility(View.VISIBLE);

            double calculate = totalWeight / Rep7;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");




        } else if (countRep == 8) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep8;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");





        } else if (countRep == 9) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep9;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 10) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep10;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 11) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep11;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 12) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep12;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 13) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep13;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 14) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep14;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 15) {
            binding.linearView.setVisibility(View.VISIBLE);

            double calculate = totalWeight / Rep15;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");

            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 16) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep16;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");





        } else if (countRep == 17) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep17;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else if (countRep == 18) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep18;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");





        } else if (countRep == 19) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep19;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");





        } else if (countRep == 20) {
            binding.linearView.setVisibility(View.VISIBLE);
            double calculate = totalWeight / Rep20;
            binding.c1.setText(String.format("%.2f", calculate) + "KG");


            double cal2 = calculate * Rep2;
            double cal3 = calculate * Rep3;
            double cal4 = calculate * Rep4;
            double cal5 = calculate * Rep5;
            double cal6 = calculate * Rep6;
            double cal7 = calculate * Rep7;
            double cal8 = calculate * Rep8;
            double cal9 = calculate * Rep9;
            double cal10 = calculate * Rep10;
            double cal11 = calculate * Rep11;
            double cal12 = calculate * Rep12;
            double cal13 = calculate * Rep13;
            double cal14 = calculate * Rep14;
            double cal15 = calculate * Rep15;
            double cal16 = calculate * Rep16;
            double cal17 = calculate * Rep17;
            double cal18 = calculate * Rep18;
            double cal19 = calculate * Rep19;
            double cal20 = calculate * Rep20;
            binding.c2.setText(String.format("%.2f", cal2) + "KG");
            binding.c3.setText(String.format("%.2f", cal3) + "KG");
            binding.c4.setText(String.format("%.2f", cal4) + "KG");
            binding.c5.setText(String.format("%.2f", cal5) + "KG");
            binding.c6.setText(String.format("%.2f", cal6) + "KG");
            binding.c7.setText(String.format("%.2f", cal7) + "KG");
            binding.c8.setText(String.format("%.2f", cal8) + "KG");
            binding.c9.setText(String.format("%.2f", cal9) + "KG");
            binding.c10.setText(String.format("%.2f", cal10) + "KG");
            binding.c11.setText(String.format("%.2f", cal11) + "KG");
            binding.c12.setText(String.format("%.2f", cal12) + "KG");
            binding.c13.setText(String.format("%.2f", cal13) + "KG");
            binding.c14.setText(String.format("%.2f", cal14) + "KG");
            binding.c15.setText(String.format("%.2f", cal15) + "KG");
            binding.c16.setText(String.format("%.2f", cal16) + "KG");
            binding.c17.setText(String.format("%.2f", cal17) + "KG");
            binding.c18.setText(String.format("%.2f", cal18) + "KG");
            binding.c19.setText(String.format("%.2f", cal19) + "KG");
            binding.c20.setText(String.format("%.2f", cal20) + "KG");






        } else {
         binding.linearView.setVisibility(View.GONE);
        }

    }

}