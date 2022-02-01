package com.example.hyperlocal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Horizontal product Layout

    private TextView horizontallayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;

    //////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_home2, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Grocery"));
        categoryModelList.add(new CategoryModel("link","Stationery"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Mobile and Tablets"));
        categoryModelList.add(new CategoryModel("link","Food"));
        categoryModelList.add(new CategoryModel("link","Sports"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        horizontallayoutTitle = view.findViewById(R.id.horizontalLayoutTitle);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.viewAllButton);
        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);

        List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.image3,"Redmi Note 8 Pro","MediaTek Helio G90T","Rs. 12999/-"));

        HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductAdapter);
        horizontalProductAdapter.notifyDataSetChanged();
        return view;
    }
}