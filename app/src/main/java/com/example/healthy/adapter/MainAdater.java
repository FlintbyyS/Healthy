package com.example.healthy.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthy.BD.Constants;
import com.example.healthy.BD.DBManager;
import com.example.healthy.Notes.Note;
import com.example.healthy.NotesActivity;
import com.example.healthy.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainAdater  extends RecyclerView.Adapter<MainAdater.MainViewHolder> {
    private Context context;
private List<Note> notelist;

    public MainAdater(Context context) {
        this.context = context;
        notelist = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_list, parent, false);
        return new MainViewHolder(view, context, notelist);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
holder.setData(notelist.get(position));
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }
public void ubdateAdapter(List<Note> newList){
        notelist.clear();
        notelist.addAll(newList);
        notifyDataSetChanged();

}



    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MainViewHolder(@NonNull View itemView, Context context,  List<Note> notelist) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitel);
            noteDesc = itemView.findViewById(R.id.noteDesc);
            itemView.setOnClickListener(this);
            this.context = context;
            this.notelist = notelist;
        }
        private  List<Note> notelist;
        private Context context;
        private TextView noteTitle;
        private TextView noteDesc;
        public void setData(Note note){
            String Title = note.getDiscription();
            if(Title.length() > 40) {
                Title = Title.substring(0, 35) + "...";
            }
            noteTitle.setText(note.getTitle());
            String Disc = note.getDiscription();
            if(Disc.length() > 40) {
                Disc = Disc.substring(0, 35) + "...";
            }
            noteDesc.setText(Disc);

        }



        @Override
        public void onClick(View view) {
Intent intent = new Intent(context, NotesActivity.class);
intent.putExtra(Constants.ListKey, notelist.get(getAdapterPosition()));
context.startActivity(intent);

        }
    }
    public void DeleteItem(int position, DBManager dbManager){
        dbManager.Delete(notelist.get(position).getId());
        notelist.remove(position);
        notifyItemRangeChanged(0, notelist.size());
        notifyItemRemoved(position);
    }
}
