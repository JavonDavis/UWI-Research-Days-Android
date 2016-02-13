package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uwics.uwidiscover.R;

/**
 * Created by Howard on 1/23/2016.
 */
public class FacultyGridAdapter extends BaseAdapter {

    private Context context;
    private String[] deptOptions;

    public FacultyGridAdapter(Context context, String[] deptOptions) {
        this.context = context;
        this.deptOptions = deptOptions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertView == null) {
            gridView = inflater.inflate(R.layout.gridview_item, null);

            TextView textView = (TextView) gridView.findViewById(R.id.card_title);
            textView.setText(deptOptions[position]);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.card_image);

            String travelOption = deptOptions[position];
            switch (travelOption) {
                case "Dept. Of Chemistry":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Computing":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Geography and Geology":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Life Sciences":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Mathematics":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Physics":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. Of Engineering":
                    imageView.setImageResource(R.mipmap.ic_faculty_scitech_img);
                    break;
                case "Dept. of Economics":
                    imageView.setImageResource(R.mipmap.ic_faculty_sosci_img);
                    break;
                case "Dept. Of Government":
                    imageView.setImageResource(R.mipmap.ic_faculty_sosci_img);
                    break;
                case "Dept. Of Sociology, Psychology and Social Work":
                    imageView.setImageResource(R.mipmap.ic_faculty_sosci_img);
                    break;
                case "Caribbean Institute of Media and Communication":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "History and Archaeology":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "Institute of Caribbean Studies":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "Language, Linguistics and Philosophy":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "Library and Information Studies":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "Literatures in English":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "Modern Languages and Literatures":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                case "School of Education":
                    imageView.setImageResource(R.mipmap.ic_faculty_humed_img);
                    break;
                default:
                    break;
            }
        } else {
            gridView = convertView;
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return deptOptions.length;
    }

    @Override
    public Object getItem(int position) {
        return deptOptions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
