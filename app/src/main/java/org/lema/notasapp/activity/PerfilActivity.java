package org.lema.notasapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.lema.notasapp.R;
import org.lema.notasapp.dao.PerfilDao;
import org.lema.notasapp.task.FotoUploadTask;

import java.io.ByteArrayOutputStream;


/**
 * Created by leonardocordeiro on 22/01/16.
 */
public class PerfilActivity extends AppCompatActivity {

    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.perfil_activity);

    }

}
