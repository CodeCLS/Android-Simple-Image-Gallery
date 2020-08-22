package com.androidcodeman.simpleimagegallery.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcodeman.simpleimagegallery.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

/**
 * Author CodeBoy722
 *
 * An adapter for populating RecyclerView with items representing folders that contain images
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class pictureFolderAdapter extends RecyclerView.Adapter<pictureFolderAdapter.FolderHolder>{

    private ArrayList<imageFolder> folders;
    private Context folderContext;
    private itemClickListener listenToClick;
    String[] titles;

    String[] sizes = {"181", "10828" , "66","39","497","5","112","3","1","1","1","1"};

    Drawable[] drawables;








    /**
     *
     * @param folders An ArrayList of String that represents paths to folders on the external storage that contain pictures
     * @param folderContext The Activity or fragment Context
     * @param listen interFace for communication between adapter and fragment or activity
     */
    public pictureFolderAdapter(ArrayList<imageFolder> folders, Context folderContext, itemClickListener listen) {
        this.folders = folders;
        this.folderContext = folderContext;
        this.listenToClick = listen;
        this.titles = new String[]{folderContext.getString(R.string.Camera_string), folderContext.getString(R.string.Whatsapp_string),
                folderContext.getString(R.string.Screenshots_string), folderContext.getString(R.string.Telegram_string),
                folderContext.getString(R.string.Whatsapp_string), folderContext.getString(R.string.Download_string),
                folderContext.getString(R.string.Whatsapp_string),folderContext.getString(R.string.Reddit_string),
                folderContext.getString(R.string.O_string), folderContext.getString(R.string.OpenCamera_string),
                folderContext.getString(R.string.Whatsapp_string), folderContext.getString(R.string.Wallpaper_string)


        };
        this.drawables = new Drawable[]{folderContext.getDrawable(R.drawable.keyboard), folderContext.getDrawable(R.drawable.dancer)
                , folderContext.getDrawable(R.drawable.blocc), folderContext.getDrawable(R.drawable.phone),
                folderContext.getDrawable(R.drawable.dancing), folderContext.getDrawable(R.drawable.orange),
                folderContext.getDrawable(R.drawable.blurryy), folderContext.getDrawable(R.drawable.roschhaschana),
                folderContext.getDrawable(R.drawable.bloccmedia), folderContext.getDrawable(R.drawable.telegram),
                folderContext.getDrawable(R.drawable.noidea), folderContext.getDrawable(R.drawable.black)
        };
    }



    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cell = inflater.inflate(R.layout.picture_folder_item, parent, false);
        return new FolderHolder(cell);

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
        final imageFolder folder = folders.get(position);





        Glide.with(folderContext)
                .load(drawables[position])
                .apply(new RequestOptions().centerCrop())
                .into(holder.folderPic);




        holder.folderSize.setText(sizes[position] + " "+ folderContext.getString(R.string.Media_string));
        holder.folderName.setText(titles[position]);





        holder.folderPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenToClick.onPicClicked(folder.getPath(),folder.getFolderName());
            }
        });

    }





    @Override
    public int getItemCount() {
        return 12;
    }


    public class FolderHolder extends RecyclerView.ViewHolder{
        ImageView folderPic;
        TextView folderName;
        TextView folderSize;

        CardView folderCard;

        public FolderHolder(@NonNull View itemView) {
            super(itemView);
            folderPic = itemView.findViewById(R.id.folderPic);
            folderName = itemView.findViewById(R.id.folderName);
            folderSize=itemView.findViewById(R.id.folderSize);
            folderCard = itemView.findViewById(R.id.folderCard);
        }
    }

}
