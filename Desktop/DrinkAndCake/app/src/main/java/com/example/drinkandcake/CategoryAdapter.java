package com.example.drinkandcake;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkandcake.model.Product;
import com.example.drinkandcake.my_interface.IClickItemProductListener;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Product> mListFood;
    private IClickItemProductListener iClickItemProductListener;

    public CategoryAdapter(List<Product> mListFood,IClickItemProductListener iClickItemProductListener) {
        this.mListFood = mListFood;
        this.iClickItemProductListener = iClickItemProductListener;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Product product = mListFood.get(position);
        if (product == null){
            return;
        }
        holder.textViewPrice.setText(product.getPrice()+"");
        holder.textViewName.setText(product.getName());
        holder.imageView.setImageResource(product.getImage());
        final int[] i = {Integer.parseInt(holder.quantity.getText().toString())};

        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    holder.quantity.setText(i[0]++ + "");
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i[0]>0){
                    holder.quantity.setText(i[0]-- +"");
                }
            }
        });
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemProductListener.onClickBuy(product);
            }
        });
        holder.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemProductListener.onClickCart(product,Integer.parseInt(holder.quantity.getText().toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListFood != null){
            return mListFood.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewName,textViewPrice,quantity;
        private CardView cardView;
        private Button cong,tru,buy,addCart;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            buy = itemView.findViewById(R.id.BuyNow);
            addCart = itemView.findViewById(R.id.AddtoCart);
            cong = itemView.findViewById(R.id.cong);
            tru = itemView.findViewById(R.id.tru);
            cardView = itemView.findViewById(R.id.layout_cate);
            imageView = itemView.findViewById(R.id.img_food);
            textViewName = itemView.findViewById(R.id.tv_name_food);
            textViewPrice = itemView.findViewById(R.id.tv_price_food);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
