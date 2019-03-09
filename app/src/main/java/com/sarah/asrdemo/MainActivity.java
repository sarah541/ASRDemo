package com.sarah.asrdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  private static final int TRANSCRIBE_REQUEST_CODE = 2;
  @BindView(R.id.textView) TextView textView;
  private Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    checkAudioPermission();
    textView.setMovementMethod(new ScrollingMovementMethod());
    speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(this, "Audio permissions are required", Toast.LENGTH_LONG).show();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == TRANSCRIBE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
      textView.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
    } else {
      Toast.makeText(this, "Unable to transcribe!!!", Toast.LENGTH_SHORT).show();
    }
  }

  @OnClick(R.id.english)
  void transcribeEnglish() {
    startTranscribing(R.string.english, "en-IN");
  }

  @OnClick(R.id.hindi)
  void transcribeHindi() {
    startTranscribing(R.string.hindi, "hi-IN");
  }

  @OnClick(R.id.bengali)
  void transcribeBengali() {
    startTranscribing(R.string.bengali, "bn-IN");
  }

  @OnClick(R.id.gujarati)
  void transcribeGujarati() {
    startTranscribing(R.string.gujarati, "gu-IN");
  }

  @OnClick(R.id.kannada)
  void transcribeKannada() {
    startTranscribing(R.string.kannada, "kn-IN");
  }

  @OnClick(R.id.malayalam)
  void transcribeMalayalam() {
    startTranscribing(R.string.malayalam, "ml-IN");
  }

  @OnClick(R.id.marathi)
  void transcribeMarathi() {
    startTranscribing(R.string.marathi, "mr-IN");
  }

  @OnClick(R.id.tamil)
  void transcribeTamil() {
    startTranscribing(R.string.tamil, "ta-IN");
  }

  @OnClick(R.id.telugu)
  void transcribeTelugu() {
    startTranscribing(R.string.telugu, "te-IN");
  }

  @OnClick(R.id.urdu)
  void transcribeUrdu() {
    startTranscribing(R.string.urdu, "ur-IN");
  }

  private void startTranscribing(@StringRes int stringResource, String languageCode) {
    speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(stringResource));
    speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode);
    startActivityForResult(speechIntent, TRANSCRIBE_REQUEST_CODE);
  }

  private void checkAudioPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(
          this,
          new String[] { Manifest.permission.RECORD_AUDIO },
          1
      );
    }
  }
}
