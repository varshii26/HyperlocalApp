package com.example.hyperlocal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {

    private List<HorizontalProductModel> horizontalProductModelList;


    public HorizontalProductAdapter(List<HorizontalProductModel> horizontalProductScrollModelList) {
        this.horizontalProductModelList = horizontalProductScrollModelList;
    }

    public HorizontalProductAdapter() {

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        int resource = horizontalProductModelList.get(position).getProductImage();
        String title = horizontalProductModelList.get(position).getProductTitle();
        String description = horizontalProductModelList.get(position).getProductDescription();
        String price = horizontalProductModelList.get(position).getProductPrice();

        viewHolder.setProductImage(resource);
        viewHolder.setProductTitle(title);
        viewHolder.setProductDescription(description);
        viewHolder.setProductPrice(price);

    }

    @Override
    public int getItemCount() {
        return horizontalProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.horizontalproductImage);
            productTitle = itemView.findViewById(R.id.horizontalProductTitle);
            productDescription = itemView.findViewById(R.id.horizontalProductDescription);
            productPrice = itemView.findViewById(R.id.horizontalProductPrice);
        }

        private void setProductImage(int resource)
        {
            productImage.setImageResource(resource);
        }

        private void setProductTitle(String title)
        {
            productTitle.setText(title);
        }

        private void setProductDescription(String description){
            productDescription.setText(description);
        }

        private void setProductPrice(String price)
        {
            productPrice.setText(price);
        }
    }
}
