package cm.demo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cm.demo.R;
import cm.demo.model.CMInfoModel;
import cm.demo.util.CMLog;
import cm.demo.view.base.BaseFragment;
import cm.demo.view.base.CMFragmentManager.CMFragmentType;
import cm.demo.view.commoncontrol.CMImageLoader;
import cm.demo.view.commoncontrol.CMListAdapter;
import cm.demo.view.main.VideoPlayActivity;

public class SquareFragment extends BaseFragment {

    private ImageView squareImage;
    private TextView squareText;
    private ListView squareListView;

    private List<CMInfoModel> square_list_data;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.square_layout, container,
                false);
        return newsLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        squareImage = (ImageView) getActivity().findViewById(R.id.square_image);
        squareText = (TextView) getActivity().findViewById(R.id.square_text);

        squareImage.setImageResource(R.drawable.square_unselected);
        squareText.setTextColor(getResources().getColor(R.color.unselect));

        squareImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTabSelection(CMFragmentType.CMFRAGMENT_SQUARE);
            }
        });

        initSquareListData();

        squareListView = (ListView) getActivity()
                .findViewById(R.id.square_list);
        squareListView.setAdapter(new SquareListAdapter());
        squareListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), VideoPlayActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (squareImage == null)
            return;
        if (squareText == null)
            return;
        squareImage.setImageResource(hidden ? R.drawable.square_unselected
                : R.drawable.square_selected);
        squareText.setTextColor(getResources().getColor(
                hidden ? R.color.unselect : R.color.select));

        super.onHiddenChanged(hidden);
    }

    protected void updateContent() {
        CMLog.LogD("updateContent SquareFragment");
        squareListView.invalidate();
    }

    private void initSquareListData() {
        CMLog.LogD("initSquareListData");
        square_list_data = new ArrayList<CMInfoModel>();
        CMInfoModel model;
        for (int i = 0; i < 10; i++) {
            model = new CMInfoModel();
            model.setTitle("TestTitle" + i);
            model.setCreator("Tester" + i);
            model.setDuration(i + ":00");
            model.setThumb_image_uri("");
            model.setVideo_uri("");
            square_list_data.add(model);
        }
    }

    final class ViewHolder {
        TextView title;
        TextView artist;
        TextView duration;
        ImageView thumb_image;
    }

    class SquareListAdapter extends CMListAdapter {

        LayoutInflater inflater;
        CMInfoModel item;
        CMImageLoader imageLoader;

        public SquareListAdapter() {
            inflater = (LayoutInflater) getActivity().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            imageLoader = new CMImageLoader(getActivity()
                    .getApplicationContext());
        }

        @Override
        public int getCount() {
            return square_list_data.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_row, null);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.artist = (TextView) convertView
                        .findViewById(R.id.artist);
                holder.duration = (TextView) convertView
                        .findViewById(R.id.duration);
                holder.thumb_image = (ImageView) convertView
                        .findViewById(R.id.list_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            item = square_list_data.get(position);

            holder.title.setText(item.getTitle());
            holder.artist.setText(item.getCreator());
            holder.duration.setText(item.getDuration().toString());
            imageLoader.DisplayImage(item.getThumb_image_uri(),
                    holder.thumb_image);
            return convertView;
        }

    }
}