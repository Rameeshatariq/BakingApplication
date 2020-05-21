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

package com.example.cv.baker_app.ui_design.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cv.baker_app.data.RecipesRepository;
import com.example.cv.baker_app.model.Recipe;

import java.util.List;

/**
 * {@link ViewModel} for MainActivity
 */
public class MainActivityViewModel extends ViewModel {

    private final RecipesRepository mRepository;
    private LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(RecipesRepository repository) {
        mRepository = repository;
        mRecipes = mRepository.getRecipeListFromNetwork();
    }

    /**
     * Returns LiveData of the list of Recipes
     */
    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    /**
     * Sets a new value for the list of recipes
     */
    public void setRecipes() {
        mRecipes = mRepository.getRecipeListFromNetwork();
    }
}
