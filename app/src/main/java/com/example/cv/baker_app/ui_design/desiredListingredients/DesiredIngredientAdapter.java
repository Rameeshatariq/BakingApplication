/*
 *  Copyright 2018 Soojeong Shin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.cv.baker_app.ui_design.desiredListingredients;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.cv.baker_app.R;
import com.example.cv.baker_app.data.DesiredIngredientsListEntry;
import com.example.cv.baker_app.databinding.DesiredIngredientListItemBinding;

import java.util.List;

/**
 * {@link DesiredIngredientAdapter} exposes the shopping lists to a {@link RecyclerView}.
 */
public class DesiredIngredientAdapter extends RecyclerView.Adapter<DesiredIngredientAdapter.DesiredIngredientViewHolder> {

    /** Member variable for the list of {@link DesiredIngredientsListEntry}s */
    private List<DesiredIngredientsListEntry> mDesiredEntries;
    /** The context of the app */
    private Context mContext;

    /**
     * Constructor for ShoppingAdapter that accepts a list of ShoppingListEntry to display
     */
    public DesiredIngredientAdapter(Context context) {
        mContext = context;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @return A new ShoppingViewHolder that holds the ShoppingListItemBinding
     */
    @NonNull
    @Override
    public DesiredIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        DesiredIngredientListItemBinding desiredItemBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.desired_ingredient_list_item, parent, false);
        return new DesiredIngredientViewHolder(desiredItemBinding);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DesiredIngredientViewHolder holder, int position) {
        DesiredIngredientsListEntry desiredEntry = mDesiredEntries.get(position);
        holder.bind(desiredEntry);
    }

    /**
     * Returns the number of ShoppingEntries to display.
     */
    @Override
    public int getItemCount() {
        if (mDesiredEntries == null) return 0;
        return mDesiredEntries.size();
    }

    /**
     * When data changes, update the list of desired
     * and notifies the adapter to use the new values on it.
     */
    public void setShoppingList(List<DesiredIngredientsListEntry> desiredListEntries) {
        mDesiredEntries = desiredListEntries;
        notifyDataSetChanged();
    }

    /**
     * Returns the list of ShoppingListEntries.
     */
    public List<DesiredIngredientsListEntry> getDesiredListEntries() {
        return mDesiredEntries;
    }

    /**
     * Cache of the children views for the shopping list item.
     */
    public class DesiredIngredientViewHolder extends RecyclerView.ViewHolder {
        /** This field is used for data binding */
        private DesiredIngredientListItemBinding mDesiredItemBinding;

        /**
         * Constructor for ShoppingViewHolder.
         *
         * @param desiredItemBinding Used to access the layout's variables and views
         */
        public DesiredIngredientViewHolder(DesiredIngredientListItemBinding desiredItemBinding) {
            super(desiredItemBinding.getRoot());

            mDesiredItemBinding = desiredItemBinding;
        }

        /**
         * Displays the shopping list data on the TextView.
         */
        void bind(DesiredIngredientsListEntry desiredIngredientsListEntry) {
            mDesiredItemBinding.tvRecipeName.setText(desiredIngredientsListEntry.getRecipeName());
            mDesiredItemBinding.tvQuantity.setText(String.valueOf(desiredIngredientsListEntry.getQuantity()));
            mDesiredItemBinding.tvMeasure.setText(desiredIngredientsListEntry.getMeasure());
            mDesiredItemBinding.tvIngredient.setText(desiredIngredientsListEntry.getIngredient());
        }
    }
}
