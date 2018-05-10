package com.example.oramo.novo;
 
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * Created by oramo on 08/05/2018.
 */
public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.ViewHolder> {
 
    private List<Cardapio> mProdutosList; //mCardapioList
    private Context context; //context
    private List<Cardapio> produtos; //cardapios
 
 
    public MeuAdapter(List<Cardapio> l, Context c) {
        context = c;
        mProdutosList = l;
    }
 
    @Override
    public MeuAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_mercados, viewGroup, false);
 
        return new MeuAdapter.ViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MeuAdapter.ViewHolder holder, int position) {
 
        Cardapio item = mProdutosList.get(position); 
        
        GlideApp.with(context)
                .load(item.getUrlImagem)
                .into(holder.fotoProduto);
 
        holder.txtNomeProduto.setText(item.getNomePrato()); 
        holder.txtPrecoProduto.setText("R$" + item.getPreco());
        holder.linearLayoutProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 
            }
        });
 
 
    }
 
    @Override
    public int getItemCount() {
        return mProdutosList.size();
    }
 
    public static class ViewHolder extends RecyclerView.ViewHolder {
 
        protected TextView txtPrecoProduto;
        protected TextView txtNomeProduto;
        protected ImageView fotoProduto;
        protected LinearLayout linearLayoutProduto; 
 
        public ViewHolder(View itemView) {
            super(itemView);
 
            txtNomeProduto = (TextView) itemView.findViewById(R.id.txtNomeProduto);
            txtPrecoProduto = (TextView) itemView.findViewById(R.id.txtPrecoProduto);
            fotoProduto = (ImageView) itemView.findViewById(R.id.fotoProduto);
            linearLayoutProduto = (LinearLayout) itemView.findViewById(R.id.linearLayoutProduto);
 
        }
 
    }
}