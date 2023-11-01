package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String input="" ;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btn0.setOnClickListener(this::onClick);
        binding.btn1.setOnClickListener(this::onClick);
        binding.btn2.setOnClickListener(this::onClick);
        binding.btn3.setOnClickListener(this::onClick);
        binding.btn4.setOnClickListener(this::onClick);
        binding.btn5.setOnClickListener(this::onClick);
        binding.btn6.setOnClickListener(this::onClick);
        binding.btn7.setOnClickListener(this::onClick);
        binding.btn8.setOnClickListener(this::onClick);
        binding.btn9.setOnClickListener(this::onClick);

        binding.btnFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handelFraction( binding.btnFraction);
            }
        });

        //    delete
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.txtIn.getText().toString().isEmpty()){
                    String oldText = binding.txtIn.getText().toString();
                     input = oldText.substring(0,oldText.length()-1);
                    binding.txtIn.setText(input);
                }
            }
        });
        binding.btnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!binding.txtIn.getText().toString().isEmpty()){
                    input="";
                    binding.txtIn.setText("");
                    binding.txtOut.setText("");
                }
                return false;
            }
        });

       //  + add
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorOnScreen(binding.btnPlus);



            }
        });
        // - minus
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorOnScreen(binding.btnMinus);

            }
        });
        // * multiple
        binding.btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorOnScreen(binding.btnMult);
            }
        });
        // \ div
        binding.btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorOnScreen(binding.btnDiv);
            }
        });
        // = equal
        binding.btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(binding.txtIn.toString().contains("\\+|\\*|/|-"))
                  calc();

            }
        });



    }// end of activity

    private void calc(){
        double sul ;
                if (input.contains("-")) {
                    String[] calc = input.split("-");
                    sul = Double.parseDouble(calc[0]) - Double.parseDouble(calc[1]);
                    binding.txtOut.setText(deleteDecimal(Double.toString(sul)));
                    input = deleteDecimal(Double.toString(sul));
                }
                if (input.contains("*")) {
                    String[] calc = input.split("\\*");
                    sul = Double.parseDouble(calc[0]) * Double.parseDouble(calc[1]);
                    binding.txtOut.setText(deleteDecimal(Double.toString(sul)));
                    input = deleteDecimal(Double.toString(sul));
                }
                if (input.contains("+")) {
                    String[] calc = input.split("\\+");
                    sul = Double.parseDouble(calc[0]) + Double.parseDouble(calc[1]);

                    binding.txtOut.setText(deleteDecimal(Double.toString(sul)));
                    input = deleteDecimal(Double.toString(sul));
                }
                if (input.contains("/")) {



                    String[] calc = input.split("\\/");
                    sul = Double.parseDouble(calc[0]) / Double.parseDouble(calc[1]);
                    if (Integer.parseInt(calc[1]) == 0) {
                        Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_SHORT).show();
                    } else {
                        binding.txtOut.setText(deleteDecimal(Double.toString(sul)));
                        input = deleteDecimal(Double.toString(sul));
                    }

                }

    }
    private String deleteDecimal(String num){
        String []n = num.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                num = n[0];
            }
        }
        return num;
    }
    private void operatorOnScreen(MaterialButton btn){
        String num = binding.txtIn.getText().toString();
        if(!num.isEmpty()){
            char ch = num.charAt(num.length()-1);
            if(ch != '+' && ch != '-' && ch != '*'&& ch!='/' ){
                input+=btn.getText().toString();
                binding.txtIn.setText(input);
            }
        }
    }
    private void handelFraction(MaterialButton btn){
        String num = binding.txtIn.getText().toString();


        if(!num.isEmpty()){
            char ch = num.charAt(num.length()-1);
            if(ch != '.'  ){
             //   binding.txtIn.append(  btn.getText().toString());
                input+=btn.getText().toString();
                binding.txtIn.setText(input);
            }
        }else {
//                binding.txtIn.append('0' + btn.getText().toString());
            input+=btn.getText().toString();
            binding.txtIn.setText('0'+input);
            }

    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        input+=btn.getText().toString();
        binding.txtIn.setText(input);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}