package com.example.emmchierchie.gethubapp.View.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import com.example.emmchierchie.gethubapp.R;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewRepos extends RecyclerView.Adapter {
    private List<Repository> reposList;
    private ReposListener reposListener;

    public AdapterRecyclerViewRepos(ReposListener reposListener) {
        this.reposList = new ArrayList<>();
        this.reposListener = reposListener;
    }

    // inflo la celda
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View cell = layoutInflater.inflate(R.layout.cell,parent,false);
        ViewHolder viewHolder = new ViewHolder(cell);
        return viewHolder;
    }

    // le cargo a la celda la info del viewholder según la posición del elemento en la lista
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Repository repo = reposList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.loadRepo(repo);
    }

    // recorro el tamaño de la lista
    @Override
    public int getItemCount() {
        return reposList.size();
    }

    // limpio la última lista, agrego la lista nueva y notofico los datos
    public void updateUserList(List<Repository> list) {
        reposList.clear();
        if(list != null){
            reposList.addAll(list);
        }
        notifyDataSetChanged();
    }

    // creo la celda y le seteo el evento al clickearla
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewStargazer;

        public ViewHolder(final View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStargazer = itemView.findViewById(R.id.textViewStargazer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reposListener.showDetail(reposList.get(getAdapterPosition()));
                }
            });
        }

        // método del seteo
        public void loadRepo(Repository repository) {
            textViewName.setText(repository.getName());
            textViewStargazer.setText(repository.getStargazers_count().toString());
        }
    }

    // comunico con la activity detail para poder mostrar los datos
    public interface ReposListener {
        void showDetail(Repository repository);
    }
}
