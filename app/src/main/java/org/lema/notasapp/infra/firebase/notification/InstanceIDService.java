package org.lema.notasapp.infra.firebase.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by leonardocordeiro on 07/04/17.
 */

public class InstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        Log.i("token", FirebaseInstanceId.getInstance().getToken());

    }
}
