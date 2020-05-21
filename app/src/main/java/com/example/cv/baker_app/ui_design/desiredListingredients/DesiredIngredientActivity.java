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

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.example.cv.baker_app.AppExecutors;
import com.example.cv.baker_app.R;
import com.example.cv.baker_app.data.RecipesDatabase;
import com.example.cv.baker_app.data.DesiredIngredientsListEntry;
import com.example.cv.baker_app.databinding.ActivityDesiredIngredientBinding;
import com.example.cv.baker_app.databinding.ActivityDetailBinding;
import com.example.cv.baker_app.utlis.InjectorUtils;

import java.util.List;

/**
 * This activity will display the shopping list that a user clicks to add to the shopping list.
 */
public class DesiredIngredientActivity extends AppCompatActivity {

    /** Member variable for the RecipeDatabase */
    private RecipesDatabase mDb;

    /** Member variable for ShoppingAdapter */
    private DesiredIngredientAdapter mDesiredIngredientAdapter;

    /** This field is used for data binding */
    private ActivityDesiredIngredientBinding mdesiredBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdesiredBinding = DataBindingUtil.setContentView(this, R.layout.activity_desired_ingredient);

        initAdapter();

        mDb = RecipesDatabase.getInstance(getApplicationContext());

        setupViewModel();

        setupItemTouchHelper();

        showUpButton();
    }

    /**
     */
    private void initAdapter() {
        mdesiredBinding.rvShopping.setLayoutManager(new LinearLayoutManager(this));
        mdesiredBinding.rvShopping.setHasFixedSize(true);
        mDesiredIngredientAdapter = new DesiredIngredientAdapter(this);
        mdesiredBinding.rvShopping.setAdapter(mDesiredIngredientAdapter);
    }

    /**
     */
    private void setupViewModel() {
        DesiredIngredientViewModelFactory factory = InjectorUtils.provideListViewModelFactory(this);
        DesiredIngredientViewModel desiredIngredientViewModel = ViewModelProviders.of(this, factory).get(DesiredIngredientViewModel.class);

        desiredIngredientViewModel.getList().observe(this, new Observer<List<DesiredIngredientsListEntry>>() {
            @Override
            public void onChanged(@Nullable List<DesiredIngredientsListEntry> shoppingListEntries) {
                mDesiredIngredientAdapter.setShoppingList(shoppingListEntries);


                if (shoppingListEntries == null || shoppingListEntries.size() == 0) {
                    showEmptyView();
                } else {
                    showShoppingView();
                }
            }
        });
    }

    private void setupItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int adapterPosition = viewHolder.getAdapterPosition();
                        List<DesiredIngredientsListEntry> shoppingListEntries =
                                mDesiredIngredientAdapter.getDesiredListEntries();
                        mDb.recipeDao().deleteIngredient(shoppingListEntries.get(adapterPosition));
                    }
                });
            }
        }).attachToRecyclerView(mdesiredBinding.rvShopping);
    }

    /**
     */
    private void showUpButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     */
    private void showShoppingView() {
        mdesiredBinding.tvEmptyShopping.setVisibility(View.GONE);
        mdesiredBinding.rvShopping.setVisibility(View.VISIBLE);
    }

    /**
     */
    private void showEmptyView() {
        mdesiredBinding.rvShopping.setVisibility(View.GONE);
        mdesiredBinding.tvEmptyShopping.setVisibility(View.VISIBLE);
    }
}
