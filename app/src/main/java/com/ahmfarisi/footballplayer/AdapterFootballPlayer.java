package com.ahmfarisi.footballplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>{
    private Context ctx;
    private ArrayList arrID,arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(Context ctx, ArrayList arrID,ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub) {
        this.ctx = ctx;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
        this.arrID=arrID;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_player, parent, false);
        return new ViewHolderPlayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvKlub.setText(arrKlub.get(position).toString());
        holder.tvid.setText(arrID.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderPlayer extends RecyclerView.ViewHolder {
        private TextView tvid,tvNama, tvNomor, tvKlub;

        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvKlub = itemView.findViewById(R.id.tv_klub);
            tvid = itemView.findViewById(R.id.tv_ID);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("ADA APA");
                    pesan.setMessage("PILIHAN ANDA"+tvNama.getText().toString()+" MAU NGAPAIN");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent varIntent =new Intent(ctx,UbahActivity.class);
                            varIntent.putExtra("varID",tvid.getText().toString());
                            varIntent.putExtra("varnama",tvNama.getText().toString());
                            varIntent.putExtra("varnomor",tvNomor.getText().toString());
                            varIntent.putExtra("varklub",tvKlub.getText().toString());
                            ctx.startActivity(varIntent);
                        }
                    });

                    pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper mydb= new MyDatabaseHelper((ctx));
                            long eksekusi=mydb.hapusplayer(tvid.getText().toString());
                            if(eksekusi==-1){
                                Toast.makeText(ctx, "GAGAL DIHAPUS", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "BERHASIL MENGHAPUS DATA", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity)ctx).onResume();
                            }
                        }
                    });

                    pesan.show();
            return false;
                }
            });

        }
    }



}
