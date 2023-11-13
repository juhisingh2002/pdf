package com.mohit.pdf;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import com.pspdfkit.configuration.activity.PdfActivityConfiguration;
import com.pspdfkit.ui.PdfActivity;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_PDF_FILE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call openFile function to open the file picker
        openFile(null); // Pass null or a specific URI if you want to open the picker at a specific location
    }

    private void openFile(Uri pickerInitialUri) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");

        // Optionally, specify a URI for the file that should appear in the
        // system file picker when it loads.
        if (pickerInitialUri != null) {
            intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
        }

        startActivityForResult(intent, PICK_PDF_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData); // Always call the super method first

        if (requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK && resultData != null) {
            // The result data contains a URI for the document or directory
            // the user selected.
            Uri uri = resultData.getData();
            if (uri != null) {
                final PdfActivityConfiguration config = new PdfActivityConfiguration.Builder(MainActivity.this).build();
                PdfActivity.showDocument(this, uri, config);
            }
        }
    }
}
