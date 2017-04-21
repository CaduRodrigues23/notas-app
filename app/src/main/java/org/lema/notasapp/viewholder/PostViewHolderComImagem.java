package org.lema.notasapp.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lema.notasapp.R;
import org.lema.notasapp.domain.model.Post;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by igor on 08/03/17.
 */

public class PostViewHolderComImagem extends RecyclerView.ViewHolder {

    private Activity activity;

    final TextView mensagem;
    final TextView autor;
    final ImageView imagem;
    final TextView data;

    public PostViewHolderComImagem(Activity activity, View itemView) {
        super(itemView);

        this.activity = activity;
        mensagem = (TextView) itemView.findViewById(R.id.tv_feed_mensagem);
        autor = (TextView) itemView.findViewById(R.id.tv_feed_autor_foto);
        imagem = (ImageView) itemView.findViewById(R.id.iv_feed_imagem);


        data = (TextView) itemView.findViewById(R.id.tv_feed_data_foto);

    }

    public void onBind(final Post post) {
        mensagem.setText(String.valueOf(post.getTexto()));
        autor.setText(String.valueOf(post.getAutor().getNome()));
        Picasso.with(activity)
                .load(post.getLinkParaFoto())
                .into(imagem);

        if (post.getData() != null) {
            Calendar calAtual = Calendar.getInstance();
            Calendar calPost = Calendar.getInstance();
            calAtual.set(Calendar.HOUR_OF_DAY, 0);
            calAtual.set(Calendar.SECOND, 0);
            calPost.setTime(post.getData());

            if (calPost.before(calAtual)) {
                SimpleDateFormat dataPost = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(dataPost.format(post.getData()));
            } else {
                SimpleDateFormat dataPost = new SimpleDateFormat("HH:mm");
                data.setText("às " +  dataPost.format(post.getData()));
            }

        } else {
            data.setText("");
        }
    }
}
