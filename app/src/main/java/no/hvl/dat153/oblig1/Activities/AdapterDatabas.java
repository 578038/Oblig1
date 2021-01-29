package no.hvl.dat153.oblig1.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import no.hvl.dat153.oblig1.R;

public class AdapterDatabas extends RecyclerView.Adapter<AdapterDatabas.DatabaseViewHolder> {
    private ArrayList<Person> mPersonList;

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public TextView mTextView1;


        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textViewDatabase);
            mTextView1 = itemView.findViewById(R.id.textViewDatabase);

        }
    }

    public AdapterDatabas(ArrayList<Person> personList){
        mPersonList = personList;
    }


    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
       DatabaseViewHolder dvh = new DatabaseViewHolder(v);
       return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
    Person currentPerson = mPersonList.get(position);

    holder.mImageView.setImageBitmap(currentPerson.getImg());
    holder.mTextView1.setText(currentPerson.getName());
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
}
