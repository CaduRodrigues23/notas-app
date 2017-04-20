package org.lema.notasapp.infra.retrofit.callback.chain;

import android.util.Log;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.lema.notasapp.infra.RetrofitUtils;
import org.lema.notasapp.infra.error.APIError;
import org.lema.notasapp.infra.event.APIErrorEvent;
import org.lema.notasapp.infra.exception.GenericConnectionErrorException;
import org.lema.notasapp.infra.retrofit.callback.OAuthCallback;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by leonardocordeiro on 29/11/16.
 */
public class APIErrorResponseChain implements ResponseChain {
    private ResponseChain next;
    private OAuthCallback callback;

    public APIErrorResponseChain(OAuthCallback callback) {
        this.callback = callback;
    }

    @Override
    public void handler(Call call, Response response) {
        ResponseBody responseBody = response.errorBody();

        try {
            Converter<ResponseBody, APIError> converter = RetrofitUtils.getInstance().responseBodyConverter(APIError.class, new Annotation[0]);
            APIError error = converter.convert(responseBody);
            EventBus.getDefault().post(new APIErrorEvent(error));
        } catch (IOException e) {
            Log.e("erro", this.getClass().getName() + " - " + e.getMessage());
            callback.onFailure(call, new GenericConnectionErrorException());
        }
    }

    @Override
    public void setNext(ResponseChain next) {
        this.next = next;
    }
}
