package sanjay.voicetoteluguapp;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class VoiceToTeluguActivity extends AppCompatActivity {
EditText txt_Voice;
ImageButton btn_Voice;
Button btn_SendWA;
static final int RESULT_SPEECH =1;
ArrayList<String> text;
String watext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_to_telugu);

            txt_Voice = (EditText) findViewById(R.id.txt_voice);
            btn_Voice = (ImageButton) findViewById(R.id.btn_voice);
            btn_SendWA = (Button) findViewById(R.id.btn_sendwa);

   btn_Voice.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent voiceintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) ;
           voiceintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
           voiceintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"te-IN");
           startActivityForResult(voiceintent,RESULT_SPEECH);

       }
   });

    btn_SendWA.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent shareintent = new Intent(Intent.ACTION_SEND);
            watext = txt_Voice.getText().toString();
            shareintent.putExtra(Intent.EXTRA_TEXT,watext);
            shareintent.setType("text/plain");
            shareintent.setPackage("com.whatsapp");
            startActivity(shareintent);

        }
    });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
     super.onActivityResult(requestCode,resultCode,data);
     switch(requestCode){
         case RESULT_SPEECH :{
           text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
           if(txt_Voice.findFocus()==txt_Voice){
           txt_Voice.append(text.get(0)+" ");
           }
         }
         break;
     }

    }
}
