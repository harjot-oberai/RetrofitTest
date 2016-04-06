package com.example.harjot.retrofittest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harjot.retrofittest.imageLoader.ImageLoader;
import com.example.harjot.retrofittest.models.Item;

import java.util.List;

/**
 * Created by Harjot on 06-Apr-16.
 */
public class UserAdapter extends BaseAdapter {

    private List<Item> users;
    private Context context;
    private ImageLoader imageLoader;

    public UserAdapter(Context ctx, List<Item> items) {
        super();
        this.context = ctx;
        this.users = items;
        imageLoader = new ImageLoader(ctx);
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, parent, false);
        TextView tv_username = (TextView) v.findViewById(R.id.username);
        TextView tv_url = (TextView) v.findViewById(R.id.url);
        ImageView img = (ImageView) v.findViewById(R.id.avatar);
        Item user = users.get(position);
        tv_username.setText(user.getLogin());
        tv_url.setText(user.getId() + "<>");
        try {
            imageLoader.DisplayImage(user.getAvatarUrl(),img);
        } catch (Exception e) {
            Log.e("AdapterError", e.getMessage());
        }
        Log.d("Adapter", user.getLogin());
        return v;
    }
}
