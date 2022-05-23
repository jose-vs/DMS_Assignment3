package com.example.blogmobileapp_app.components;


import static com.example.blogmobileapp_app.data.Variables.API_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blogmobileapp_app.R;
import com.example.blogmobileapp_app.api.GetBlogApiService;
import com.example.blogmobileapp_app.data.model.BlogRequest;

public class BlogViewFragment extends ListFragment {

    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blog_view_frag, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        new GetBlogApiService(this).execute(API_URL + "blogs/");
    }

}

//public class BlogViewFragment extends Fragment {
//    private static final String TAG = "BlogViewFragment";
//    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
//
//    protected RecyclerView recyclerView;
//    protected BlogViewAdapter adapter;
//
//    protected BlogRequest[] data;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Initialize dataset, this data would usually come from a local content provider or
//        // remote server.
//        initDataset();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.blog_view_frag, container, false);
//        rootView.setTag(TAG);
//
//        // BEGIN_INCLUDE(initializeRecyclerView)
//        recyclerView = (RecyclerView) rootView.findViewById(R.id.blogContainerView);
//
//        adapter = new BlogViewAdapter(data);
//        // Set CustomAdapter as the adapter for RecyclerView.
//        recyclerView.setAdapter(adapter);
//        // END_INCLUDE(initializeRecyclerView)
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.scrollToPosition(0);
//
//        return rootView;
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save currently selected layout manager.
//        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, 1);
//        super.onSaveInstanceState(savedInstanceState);
//    }
//
//    /**
//     * Generates Strings for RecyclerView's adapter. This data would usually come
//     * from a local content provider or remote server.
//     */
//    private void initDataset() {
//        GetBlogApiService task = new GetBlogApiService();
//        task.execute(API_URL + "blogs/");
//
//    }
//
//}
