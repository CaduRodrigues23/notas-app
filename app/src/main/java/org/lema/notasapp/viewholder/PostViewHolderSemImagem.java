package org.lema.notasapp.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.lema.notasapp.R;
import org.lema.notasapp.domain.model.Post;
import org.lema.notasapp.ui.activity.PostActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by igor on 08/03/17.
 */

public class PostViewHolderSemImagem extends RecyclerView.ViewHolder {

    private Activity activity;

    final TextView mensagem;
    final TextView autor;
    final TextView data;

    public PostViewHolderSemImagem(Activity activity, View itemView) {
        super(itemView);

        this.activity = activity;
        mensagem = (TextView) itemView.findViewById(R.id.tv_feed_mensagem);
        autor = (TextView) itemView.findViewById(R.id.tv_feed_dia_foto);
        data = (TextView) itemView.findViewById(R.id.tv_feed_dia);
    }

    public void onBind(final Post post) {
        mensagem.setText(post.getTitulo());
        autor.setText(post.getAutor() != null ? post.getAutor().getNome() : "");

        if (post.getDataPostagem() != null) {
            SimpleDateFormat dataPost = new SimpleDateFormat("dd/MM/yyyy");
            data.setText(dataPost.format(post.getDataPostagem()));
        } else {
            data.setText("");
        }
        mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("post", post);
                activity.startActivity(intent);
            }
        });
    }
}
