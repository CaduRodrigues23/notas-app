package org.lema.notasapp.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lema.notasapp.R;
import org.lema.notasapp.domain.model.Post;
import org.lema.notasapp.ui.activity.PostActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by igor on 08/03/17.
 */

public class PostViewHolderSemImagem extends RecyclerView.ViewHolder {

    private Activity activity;
    private Integer layoutColor;

    final RelativeLayout layout;
    final TextView mensagem;
    final TextView autor;
    final TextView data;

    public PostViewHolderSemImagem(Activity activity, View itemView, Integer layoutColor) {
        super(itemView);

        this.activity = activity;
        this.layoutColor = layoutColor;
        mensagem = (TextView) itemView.findViewById(R.id.tv_feed_mensagem);
        autor = (TextView) itemView.findViewById(R.id.tv_feed_autor);
        data = (TextView) itemView.findViewById(R.id.tv_feed_data);
        layout = (RelativeLayout) itemView.findViewById(R.id.rl_sem_foto);

    }

    public void onBind(final Post post) {
        mensagem.setText(post.getTitulo());
        autor.setText(post.getAutor() != null ? post.getAutor().getNome() : "");

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
                data.setText("às " + dataPost.format(post.getData()));
            }
        } else {
            data.setText("");
        }

        mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("post", post);
                intent.putExtra("color", layoutColor);
                activity.startActivity(intent);
            }
        });

        layout.setBackgroundResource(layoutColor);
    }

}
