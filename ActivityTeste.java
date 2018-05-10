package com.example.oramo.novo;
 
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
 
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
 
import java.util.ArrayList;
import java.util.List;
 
public class ActivityTeste extends AppCompatActivity {
 
    private RecyclerView mRecyclerViewProdutos;
 
    private MeuAdapter adaptador;
 
    private List<Cardapio> extrabom;
 
    private DatabaseReference referenciaFirebase;
 
    private Cardapio todosProdutosJuntos;
 
    private GridLayoutManager mLayoutManagerTodosProdutos;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_teste);
 
        mRecyclerViewProdutos = (RecyclerView) findViewById(R.id.recycleViewProdutos);
 
        //
 
 
        //
 
        carregarTodosProdutos();
    }
 
    private void carregarTodosProdutos() {
 
        mRecyclerViewProdutos.setHasFixedSize(true);
         mLayoutManagerTodosProdutos = new GridLayoutManager(this, 3); 
        mRecyclerViewProdutos.setLayoutManager(mLayoutManagerTodosProdutos);
        extrabom = new ArrayList<>();
        adaptador = new MeuAdapter(extrabom, this);        
 
        referenciaFirebase = FirebaseDatabase.getInstance().getReference();
 
        referenciaFirebase.child("produtos").orderByChild("nomePrato").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { 
 
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
 
                    todosProdutosJuntos = postSnapshot.getValue(Cardapio.class); 
                    extrabom.add(todosProdutosJuntos); 
                } 
                adaptador.notifyDataSetChanged();
            }
 
            @Override public void onCancelled(DatabaseError databaseError) {}
        });       
 
        mRecyclerViewProdutos.setAdapter(adaptador);
 
    }
}