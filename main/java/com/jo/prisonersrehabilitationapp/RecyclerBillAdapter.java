package com.jo.prisonersrehabilitationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerBillAdapter extends RecyclerView.Adapter<RecyclerBillAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    //ImageLoader imageLoader;

    public RecyclerBillAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview1, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter dataAdapterOBJ =  dataAdapters.get(position);

       // imageLoader = ImageAdapter.getInstance(context).getImageLoader();

      /*  imageLoader.get(dataAdapterOBJ.getImageUrl(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );*/

       // Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);

        Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());
        Viewholder.GType.setText(dataAdapterOBJ.getImageType());
        Viewholder.Gsize.setText(dataAdapterOBJ.getImageSize());
        Viewholder.Gpack.setText(dataAdapterOBJ.getImagePack());
        Viewholder.Gprice.setText(dataAdapterOBJ.getImagePrice());
        Viewholder.ids.setText(dataAdapterOBJ.getIMGID());
        Viewholder.gname.setText(dataAdapterOBJ.getImagename());


    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleTextView, GType,Gsize,Gpack,Gprice,ids,gname;


        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView1) ;
            GType = (TextView) itemView.findViewById(R.id.Imagetype1) ;
            Gsize = (TextView) itemView.findViewById(R.id.Imagesize1) ;
            Gpack = (TextView) itemView.findViewById(R.id.Imagepack1) ;
            Gprice = (TextView) itemView.findViewById(R.id.Imageprice1) ;
            ids=(TextView) itemView.findViewById(R.id.IID1);
            gname=(TextView) itemView.findViewById(R.id.imagename);



            //VollyImageView = (NetworkImageView) itemView.findViewById(R.id.VolleyImageView) ;

        }
    }
}
