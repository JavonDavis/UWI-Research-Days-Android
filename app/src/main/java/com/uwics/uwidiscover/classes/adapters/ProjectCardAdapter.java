package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.ProjectActivity;
import com.uwics.uwidiscover.classes.models.Project;

import java.util.ArrayList;

/**
 * Created by Howard on 1/24/2016.
 */
public class ProjectCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Project> projectArrayList;
    String facultyThemeOp;

    public ProjectCardAdapter(Context context, ArrayList<Project> projectArrayList, String facultyThemeOp) {
        this.context = context;
        this.projectArrayList = projectArrayList;
        this.facultyThemeOp = facultyThemeOp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_department_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProjectViewHolder mHolder = (ProjectViewHolder) holder;
        final Project project = projectArrayList.get(position);

        mHolder.projectTitle.setText(project.getTitle());
        mHolder.projectLocation.setText(project.getLocation().getName());
        mHolder.projectDescription.setText(project.getDescription());
        mHolder.projectCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle projectInfo = new Bundle();
                projectInfo.putString("projectTitle", project.getTitle());
                projectInfo.putString("projectDescription", project.getDescription());
                projectInfo.putString("projectTheme", facultyThemeOp);
                context.startActivity(new Intent(context, ProjectActivity.class).putExtras(projectInfo));
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectArrayList.size();
    }

    private class ProjectViewHolder extends RecyclerView.ViewHolder {

        private CardView projectCardView;
        private TextView projectTitle;
        private TextView projectLocation;
        private TextView projectDescription;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            projectCardView = (CardView) itemView.findViewById(R.id.cardView);
            projectTitle = (TextView) itemView.findViewById(R.id.project_title);
            projectLocation = (TextView) itemView.findViewById(R.id.project_location);
            projectDescription = (TextView) itemView.findViewById(R.id.project_description);
        }
    }
}
