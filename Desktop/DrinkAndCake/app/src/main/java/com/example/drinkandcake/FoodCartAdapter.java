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

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.FoodViewHolder>{

    private List<Product> mListFood;
    private IClickItemProductListener iClickItemProductListener;

    public FoodCartAdapter(List<Product> mListFood, IClickItemProductListener iClickItemProductListener) {
        this.mListFood = mListFood;
        this.iClickItemProductListener = iClickItemProductListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productcart,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Product product = mListFood.get(position);
        if (product == null){
            return;
        }
        holder.textViewPrice.setText(product.getPrice()+"");
        holder.textViewName.setText(product.getName());
        //holder.imageView.setImageResource(Integer.parseInt(product.getImage()));
        holder.quantity.setText((product.getQuantity()+""));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemProductListener.onClickItemProduct(product);
            }
        });
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
    }

    @Override
    public int getItemCount() {
        if(mListFood != null){
            return mListFood.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName,textViewPrice,quantity;
        private CardView cardView;
        private Button cong,tru;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            cong = itemView.findViewById(R.id.cong);
            tru = itemView.findViewById(R.id.tru);
            quantity = itemView.findViewById(R.id.quantity);
            cardView = itemView.findViewById(R.id.layout_Productcart);
            imageView = itemView.findViewById(R.id.img_food);
            textViewName = itemView.findViewById(R.id.tv_name_food);
            textViewPrice = itemView.findViewById(R.id.tv_price_food);
        }
    }
}
